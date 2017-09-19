package br.com.mm.aplicacao.api;

import br.com.mm.InicializadorMissaoMarte;
import br.com.mm.dominio.Posicao;
import br.com.mm.dominio.Sonda;
import br.com.mm.dominio.enumeradores.Comandos;
import br.com.mm.dominio.enumeradores.Direcao;
import br.com.mm.infraestrutura.repositorio.PlanaltoRepositorio;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

@SpringBootTest(classes = InicializadorMissaoMarte.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class RotasIT extends AbstractTestNGSpringContextTests {

    public static final String URL_PLANALTOS = "/missao-marte/api/v1/planaltos";
    public static final String URL_PLANALTOS_ID = "/missao-marte/api/v1/planaltos/{id}";

    private static WebTestClient cliente;

    private static String idPrimiroRegistro = UUID.randomUUID().toString();
    private static String idSegundoRegistro = UUID.randomUUID().toString();
    private static String idPlanaltoNaoCadastrado = UUID.randomUUID().toString();

    @BeforeClass
    public static void iniciar() throws Exception {

//        PlanaltoRepositorio planaltoRepositorio = new PlanaltoRepositorio();
//
//        Sonda[] sondas = new Sonda[2];
//        sondas[0] = new Sonda(new Posicao(3, 3, Direcao.E), new Comandos[] {Comandos.M,Comandos.M,Comandos.R,Comandos.M,Comandos.M,Comandos.R,Comandos.M,Comandos.R,Comandos.R,Comandos.M});
//        sondas[1] = new Sonda(new Posicao(3, 3, Direcao.E), new Comandos[] {Comandos.M,Comandos.M,Comandos.R,Comandos.M,Comandos.M,Comandos.R,Comandos.M,Comandos.R,Comandos.R,Comandos.M});
//
//        planaltoRepositorio.adicionar(new Entidade(idPrimiroRegistro, sondas));
//        planaltoRepositorio.adicionar(new Entidade(idSegundoRegistro, sondas));
//
//        cliente = WebTestClient
//                .bindToRouterFunction(new Rotas(new ManipuladorPlanalto(planaltoRepositorio)).carregarEventos())
//                .build();
    }

    @Test(priority = 1)
    public void deveListarTodasImplantacoes() {

        cliente.get()
                .uri(URL_PLANALTOS)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.[0].id").exists()
                .jsonPath("$.[0].sondas.[0].x").isEqualTo(3)
                .jsonPath("$.[0].sondas.[0].y").isEqualTo(3)
                .jsonPath("$.[0].sondas.[0].direcao").isEqualTo("E")
                .jsonPath("$.[0].sondas.[1].x").isEqualTo(3)
                .jsonPath("$.[0].sondas.[1].y").isEqualTo(3)
                .jsonPath("$.[0].sondas.[1].direcao").isEqualTo("E")
                .jsonPath("$.[1].id").exists()
                .jsonPath("$.[1].sondas.[0].x").isEqualTo(3)
                .jsonPath("$.[1].sondas.[0].y").isEqualTo(3)
                .jsonPath("$.[1].sondas.[0].direcao").isEqualTo("E")
                .jsonPath("$.[1].sondas.[1].x").isEqualTo(3)
                .jsonPath("$.[1].sondas.[1].y").isEqualTo(3)
                .jsonPath("$.[1].sondas.[1].direcao").isEqualTo("E");
    }

    @Test(priority = 2)
    public void deveImplatarAsSondas() {

        final String request = "{\n" +
                "\t\"superior\": {\n" +
                "\t\t\"x\": 6,\n" +
                "\t\t\"y\": 6\n" +
                "\t},\n" +
                "\t\"sondas\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"posicao\":{\n" +
                "\t\t\t\t\"x\": 1, \n" +
                "\t\t\t\t\"y\": 5,\n" +
                "\t\t\t\t\"direcao\": \"N\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"comandos\":[\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"M\"]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"posicao\":{\n" +
                "\t\t\t\t\"x\": 3, \n" +
                "\t\t\t\t\"y\": 3,\n" +
                "\t\t\t\t\"direcao\": \"E\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"comandos\":[\"M\",\"M\",\"R\",\"M\",\"M\",\"R\",\"M\",\"R\",\"R\",\"M\"]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        cliente.post()
                .uri(URL_PLANALTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(request))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").exists()
                .jsonPath("$.sondas.[0].x").isEqualTo(1)
                .jsonPath("$.sondas.[0].y").isEqualTo(6)
                .jsonPath("$.sondas.[0].direcao").isEqualTo("N")
                .jsonPath("$.sondas.[1].x").isEqualTo(5)
                .jsonPath("$.sondas.[1].y").isEqualTo(1)
                .jsonPath("$.sondas.[1].direcao").isEqualTo("E");
    }

    @Test(priority = 3)
    public void deveFalharQuandoUltrapassaLimiteSuperior() {

        final String request = "{\n" +
                "\t\"superior\": {\n" +
                "\t\t\"x\": 2,\n" +
                "\t\t\"y\": 3\n" +
                "\t},\n" +
                "\t\"sondas\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"posicao\":{\n" +
                "\t\t\t\t\"x\": 1, \n" +
                "\t\t\t\t\"y\": 5,\n" +
                "\t\t\t\t\"direcao\": \"N\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"comandos\":[\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"M\"]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"posicao\":{\n" +
                "\t\t\t\t\"x\": 3, \n" +
                "\t\t\t\t\"y\": 3,\n" +
                "\t\t\t\t\"direcao\": \"E\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"comandos\":[\"M\",\"M\",\"R\",\"M\",\"M\",\"R\",\"M\",\"R\",\"R\",\"M\"]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        cliente.post()
                .uri(URL_PLANALTOS)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(request))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.erro").exists();
    }

    @Test(priority = 4)
    public void deveInfomarQuePlanaltoNaoExiste() {

        cliente.get()
                .uri(URL_PLANALTOS_ID, idPlanaltoNaoCadastrado)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test(priority = 5)
    public void deveRetornarPlanatado() {

        cliente.get()
                .uri(URL_PLANALTOS_ID, idPrimiroRegistro)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.sondas.[0].x").isEqualTo(3)
                .jsonPath("$.sondas.[0].y").isEqualTo(3)
                .jsonPath("$.sondas.[0].direcao").isEqualTo("E")
                .jsonPath("$.sondas.[1].x").isEqualTo(3)
                .jsonPath("$.sondas.[1].y").isEqualTo(3)
                .jsonPath("$.sondas.[1].direcao").isEqualTo("E");
    }

    @Test(priority = 6)
    public void deveInfomarQuePlanaltoNaoExisteNaTentativaDeRemover() {

        cliente.delete()
                .uri(URL_PLANALTOS_ID, idPlanaltoNaoCadastrado)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test(priority = 7)
    public void deveRemoverPlanatado() {

        cliente.delete()
                .uri(URL_PLANALTOS_ID, idPrimiroRegistro)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test(priority = 8)
    public void deveAtualizarAsInformacoesDasSondasImplantadas() {

        final String request = "{\n" +
                "\t\"superior\": {\n" +
                "\t\t\"x\": 6,\n" +
                "\t\t\"y\": 6\n" +
                "\t},\n" +
                "\t\"sondas\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"posicao\":{\n" +
                "\t\t\t\t\"x\": 1, \n" +
                "\t\t\t\t\"y\": 5,\n" +
                "\t\t\t\t\"direcao\": \"N\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"comandos\":[\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"M\"]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"posicao\":{\n" +
                "\t\t\t\t\"x\": 3, \n" +
                "\t\t\t\t\"y\": 3,\n" +
                "\t\t\t\t\"direcao\": \"E\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"comandos\":[\"M\",\"M\",\"R\",\"M\",\"M\",\"R\",\"M\",\"R\",\"R\",\"M\"]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        cliente.put()
                .uri(URL_PLANALTOS_ID, idSegundoRegistro)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(request))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(idSegundoRegistro)
                .jsonPath("$.sondas.[0].x").isEqualTo(1)
                .jsonPath("$.sondas.[0].y").isEqualTo(6)
                .jsonPath("$.sondas.[0].direcao").isEqualTo("N")
                .jsonPath("$.sondas.[1].x").isEqualTo(5)
                .jsonPath("$.sondas.[1].y").isEqualTo(1)
                .jsonPath("$.sondas.[1].direcao").isEqualTo("E");
    }

    @Test(priority = 9)
    public void deveInfomarQuePlanaltoNaoExisteNaTentativaDeAtulizar() {

        final String request = "{\n" +
                "\t\"superior\": {\n" +
                "\t\t\"x\": 6,\n" +
                "\t\t\"y\": 6\n" +
                "\t},\n" +
                "\t\"sondas\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"posicao\":{\n" +
                "\t\t\t\t\"x\": 1, \n" +
                "\t\t\t\t\"y\": 5,\n" +
                "\t\t\t\t\"direcao\": \"N\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"comandos\":[\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"M\"]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"posicao\":{\n" +
                "\t\t\t\t\"x\": 3, \n" +
                "\t\t\t\t\"y\": 3,\n" +
                "\t\t\t\t\"direcao\": \"E\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"comandos\":[\"M\",\"M\",\"R\",\"M\",\"M\",\"R\",\"M\",\"R\",\"R\",\"M\"]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        cliente.put()
                .uri(URL_PLANALTOS_ID, idPlanaltoNaoCadastrado)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(request))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test(priority = 10)
    public void deveFalharQuandoUltrapassaLimiteSuperiorAoAlterarOPlanalto() {

        final String request = "{\n" +
                "\t\"superior\": {\n" +
                "\t\t\"x\": 2,\n" +
                "\t\t\"y\": 3\n" +
                "\t},\n" +
                "\t\"sondas\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"posicao\":{\n" +
                "\t\t\t\t\"x\": 1, \n" +
                "\t\t\t\t\"y\": 5,\n" +
                "\t\t\t\t\"direcao\": \"N\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"comandos\":[\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"L\",\"M\",\"M\"]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"posicao\":{\n" +
                "\t\t\t\t\"x\": 3, \n" +
                "\t\t\t\t\"y\": 3,\n" +
                "\t\t\t\t\"direcao\": \"E\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"comandos\":[\"M\",\"M\",\"R\",\"M\",\"M\",\"R\",\"M\",\"R\",\"R\",\"M\"]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        cliente.put()
                .uri(URL_PLANALTOS_ID, idSegundoRegistro)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(request))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.erro").exists();
    }
}
