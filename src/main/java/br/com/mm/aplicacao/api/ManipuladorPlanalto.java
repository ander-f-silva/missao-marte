package br.com.mm.aplicacao.api;

import br.com.mm.aplicacao.api.dto.requisicao.PlanaltoRequisicao;
import br.com.mm.aplicacao.api.dto.resposta.PlanaltoResposta;
import br.com.mm.dominio.Planalto;
import br.com.mm.dominio.Sonda;
import br.com.mm.dominio.excecao.LimiteUltrapassadoExcecao;
import br.com.mm.infraestrutura.excecao.RegistroNaoEncontradoExcecao;
import br.com.mm.infraestrutura.repositorio.PlanaltoRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Service
public class ManipuladorPlanalto {

    private static final String URL_PLANALTOS = "/missao-marte/api/v1/planaltos";
    private static final String CAMPO_ERRO = "erro";
    private static final String PARAMETRO_ID = "id";

    private PlanaltoRepositorio planaltoRepositorio;

    @Autowired
    public ManipuladorPlanalto(final PlanaltoRepositorio planaltoRepositorio) {
        this.planaltoRepositorio = planaltoRepositorio;
    }

    public Mono<ServerResponse> implantarSondas(ServerRequest request) {

        Mono<PlanaltoRequisicao> requisicao = request.bodyToMono(PlanaltoRequisicao.class);

        return requisicao.flatMap(
                r -> {
                    try {
                        Planalto planalto = r.converter();
                        Set<Sonda> sondas = planalto.implantar();

                        planaltoRepositorio.save(planalto);

                        PlanaltoResposta resposta = new PlanaltoResposta();
                        resposta.converter(planalto, sondas);

                        return ServerResponse
                                .created(URI.create(URL_PLANALTOS))
                                .body(fromObject(resposta));
                    } catch (LimiteUltrapassadoExcecao lex) {
                        Map<String, String> messages = new HashMap<>();
                        messages.put(CAMPO_ERRO, lex.getMessage());

                        return ServerResponse
                                .status(HttpStatus.CONFLICT)
                                .body(fromObject(messages));
                    }
                }
        );
    }

    public Mono<ServerResponse> recuperaTodasInformacoesDasSondas(ServerRequest request) {

        List<Planalto> planaltos = planaltoRepositorio.findAll();

        if (planaltos.isEmpty()) {
            return ServerResponse
                    .notFound()
                    .build();
        }

        return Flux.fromIterable(planaltos)
                .map(p -> {
                    PlanaltoResposta planaltoResposta = new PlanaltoResposta();
                    planaltoResposta.converter(p, p.getSondas());
                    return planaltoResposta;
                })
                .flatMap(planaltoResposta -> ServerResponse.ok().body(fromObject(planaltoResposta)))
                .single();
    }

    public Mono<ServerResponse> recuperarInformacoesDasSondas(ServerRequest request) {

        String id = request.pathVariable(PARAMETRO_ID);

        Optional<Planalto> optPlanalto = planaltoRepositorio.findById(UUID.fromString(id));

        if (!optPlanalto.isPresent()) {
            return ServerResponse
                    .notFound()
                    .build();
        }

        Mono<PlanaltoResposta> resposta = optPlanalto.map(
                planalto -> {
                    PlanaltoResposta planaltoResposta = new PlanaltoResposta();
                    planaltoResposta.converter(planalto, planalto.getSondas());
                    return Mono.just(planaltoResposta);
                })
                .get();

        return resposta.flatMap(r -> ServerResponse.ok().body(fromObject(r)));
    }

    public Mono<ServerResponse> removerInformacoesDasSondas(ServerRequest request) {

        String id = request.pathVariable(PARAMETRO_ID);

        UUID uuId = UUID.fromString(id);

        boolean resultado = planaltoRepositorio.existsById(uuId);

        if (resultado) {
            planaltoRepositorio.deleteById(uuId);

            return ServerResponse
                    .noContent()
                    .build();
        } else {
            return ServerResponse
                    .notFound()
                    .build();
        }
    }

    public Mono<ServerResponse> atualizarInformacoesDasSondas(ServerRequest request) {

        String id = request.pathVariable(PARAMETRO_ID);

        UUID uuId = UUID.fromString(id);

        Optional<Planalto> optPlanalto = planaltoRepositorio.findById(uuId);

        if (optPlanalto.isPresent()) {

            Planalto planalto = optPlanalto.get();

            Mono<PlanaltoRequisicao> requisicao = request.bodyToMono(PlanaltoRequisicao.class);

            return requisicao.flatMap(
                planaltoRequisicao -> {

                    try {
                        ModelMapper modelMapper = new ModelMapper();
                        modelMapper.map(planaltoRequisicao.converter(), planalto);

                        Set<Sonda> sondas = planalto.implantar();

                        planaltoRepositorio.save(planalto);

                        PlanaltoResposta resposta = new PlanaltoResposta();
                        resposta.converter(planalto, sondas);

                        return ServerResponse
                                .created(URI.create(URL_PLANALTOS))
                                .body(fromObject(resposta));

                    } catch (LimiteUltrapassadoExcecao lex) {
                        Map<String, String> messages = new HashMap<>();
                        messages.put(CAMPO_ERRO, lex.getMessage());

                        return ServerResponse
                                .status(HttpStatus.CONFLICT)
                                .body(fromObject(messages));
                    }
                }
            );
        } else {
            return ServerResponse
                    .notFound()
                    .build();
        }
    }
}
