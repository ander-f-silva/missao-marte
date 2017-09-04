package br.com.mm.aplicacao.api;

import br.com.mm.dominio.Planalto;
import br.com.mm.dominio.Sonda;
import br.com.mm.dominio.excecao.LimiteUltrapassadoExcecao;
import br.com.mm.infraestrutura.excecao.RegistroNaoEncontradoExcecao;
import br.com.mm.infraestrutura.repositorio.Entidade;
import br.com.mm.infraestrutura.repositorio.Repositorio;
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

    private Repositorio repositorio;

    @Autowired
    public ManipuladorPlanalto(final Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Mono<ServerResponse> implantarSondas(ServerRequest request) {

        Mono<Planalto> planalto = request.bodyToMono(Planalto.class);

        return planalto.flatMap(
                p -> {
                    try {
                        final String id = UUID.randomUUID().toString();
                        final Sonda[] sondas = p.implantar();

                        Entidade<String, Sonda[]> entidade = new Entidade(id, sondas);
                        entidade = repositorio.adicionar(entidade);

                        PlanaltoResposta resposta = new PlanaltoResposta(entidade);

                        return ServerResponse
                                .created(URI.create(URL_PLANALTOS))
                                .body(fromObject(resposta.transformar()));
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

        try {
            List<Entidade> entidades = repositorio.buscarTodos();

            Mono<List<TreeMap<String, Object>>> resposta = Flux.fromIterable(entidades)
                    .map(entidade -> new PlanaltoResposta(entidade))
                    .map(planaltoResposta -> planaltoResposta.transformar())
                    .collectList();

            return resposta.flatMap(registros -> ServerResponse.ok().body(fromObject(registros)));
        } catch (RegistroNaoEncontradoExcecao registroNaoEncontradoExcecao) {
            return ServerResponse
                    .notFound()
                    .build();
        }
    }

    public Mono<ServerResponse> recuperarInformacoesDasSondas(ServerRequest request) {

        try {
            String id = request.pathVariable(PARAMETRO_ID);

            Entidade entidade = repositorio.buscar(id);

            Mono<TreeMap<String, Object>> resposta = Mono.just(entidade)
                    .map(registro -> new PlanaltoResposta(registro))
                    .map(planaltoResposta -> planaltoResposta.transformar());

            return resposta.flatMap(registros -> ServerResponse.ok().body(fromObject(registros)));
        } catch (RegistroNaoEncontradoExcecao registroNaoEncontradoExcecao) {
            return ServerResponse
                    .notFound()
                    .build();
        }
    }

    public Mono<ServerResponse> removerInformacoesDasSondas(ServerRequest request) {

        String id = request.pathVariable(PARAMETRO_ID);

        try {
            repositorio.remover(id);
            return ServerResponse.noContent().build();
        } catch (RegistroNaoEncontradoExcecao registroNaoEncontradoExcecao) {
            return ServerResponse
                    .notFound()
                    .build();
        }
    }

    public Mono<ServerResponse> atualizarInformacoesDasSondas(ServerRequest request) {

        try {
            String id = request.pathVariable(PARAMETRO_ID);

            repositorio.remover(id);

            Mono<Planalto> planalto = request.bodyToMono(Planalto.class);

            return planalto.flatMap(
                    p -> {
                        try {
                            final Sonda[] sondas = p.implantar();

                            Entidade<String, Sonda[]> entidade = new Entidade(id, sondas);
                            entidade = repositorio.adicionar(entidade);

                            PlanaltoResposta resposta = new PlanaltoResposta(entidade);

                            return ServerResponse
                                    .ok()
                                    .body(fromObject(resposta.transformar()));
                        } catch (LimiteUltrapassadoExcecao lex) {
                            Map<String, String> messages = new HashMap<>();
                            messages.put(CAMPO_ERRO, lex.getMessage());

                            return ServerResponse
                                    .status(HttpStatus.CONFLICT)
                                    .body(fromObject(messages));
                        }
                    }
            );
        } catch (RegistroNaoEncontradoExcecao registroNaoEncontradoExcecao) {
            return ServerResponse
                    .notFound()
                .build();
        }
    }
}
