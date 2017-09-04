package br.com.mm.aplicacao.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Rotas {

    public static final String URL_PLANALTOS = "/missao-marte/api/v1/planaltos";
    public static final String URL_PLANALTOS_ID = "/missao-marte/api/v1/planaltos/{id}";

    private ManipuladorPlanalto manipuladorPlanalto;

    public Rotas(ManipuladorPlanalto manipuladorPlanalto) {
        this.manipuladorPlanalto = manipuladorPlanalto;
    }

    @Bean
    public RouterFunction<?> carregarEventos() {
        return route(
                POST(URL_PLANALTOS).
                        and(accept(MediaType.APPLICATION_JSON_UTF8)),
                manipuladorPlanalto::implantarSondas)
        .andRoute(
                GET(URL_PLANALTOS).
                        and(accept(MediaType.APPLICATION_JSON_UTF8)),
                manipuladorPlanalto::recuperaTodasInformacoesDasSondas)
        .andRoute(
                GET(URL_PLANALTOS_ID).
                        and(accept(MediaType.APPLICATION_JSON_UTF8)),
                manipuladorPlanalto::recuperarInformacoesDasSondas)
        .andRoute(
                DELETE(URL_PLANALTOS_ID).
                        and(accept(MediaType.APPLICATION_JSON_UTF8)),
                manipuladorPlanalto::removerInformacoesDasSondas)
        .andRoute(
                PUT(URL_PLANALTOS_ID).
                        and(accept(MediaType.APPLICATION_JSON_UTF8)),
                manipuladorPlanalto::atualizarInformacoesDasSondas);
    }

}
