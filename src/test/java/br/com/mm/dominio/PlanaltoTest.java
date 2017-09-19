package br.com.mm.dominio;

import br.com.mm.dominio.enumeradores.Comandos;
import br.com.mm.dominio.enumeradores.Direcao;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PlanaltoTest {

    @Test
    public void deveMovimentarSondas() {

        Set<Sonda> conjutoSondas = new HashSet<>();

        Sonda sonda = new Sonda(
                new Posicao(1, 2, Direcao.N),
                new Comandos[]{
                        Comandos.L, Comandos.M,
                        Comandos.L, Comandos.M,
                        Comandos.L, Comandos.M,
                        Comandos.L, Comandos.M,
                        Comandos.M
                });

        conjutoSondas.add(sonda);

        sonda = new Sonda(
                new Posicao(3, 3, Direcao.E),
                new Comandos[]{
                        Comandos.M, Comandos.M, Comandos.R,
                        Comandos.M, Comandos.M, Comandos.R,
                        Comandos.M, Comandos.R, Comandos.R,
                        Comandos.M
                });

        conjutoSondas.add(sonda);

        Planalto planalto = new Planalto(new Limite(5, 5), conjutoSondas);

        conjutoSondas = planalto.implantar();

        List<Sonda> sondaResultado = new ArrayList<>();
        sondaResultado.addAll(conjutoSondas);

        assertEquals(1, sondaResultado.get(0).getPosicao().getX());
        assertEquals(3, sondaResultado.get(0).getPosicao().getY());
        assertEquals(Direcao.N, sondaResultado.get(0).getPosicao().getDirecao());

        assertEquals(1, sondaResultado.get(0).getPosicao().getX());
        assertEquals(3, sondaResultado.get(0).getPosicao().getY());
        assertEquals(Direcao.N, sondaResultado.get(0).getPosicao().getDirecao());
    }

}