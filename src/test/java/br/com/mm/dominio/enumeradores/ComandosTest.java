package br.com.mm.dominio.enumeradores;

import br.com.mm.dominio.Posicao;
import br.com.mm.dominio.Sonda;
import org.junit.Test;

import static org.junit.Assert.*;


public class ComandosTest {

    @Test
    public void deveMoverSondaAtualizandoCoordenadasXY() {
        Sonda sonda = new Sonda(new Posicao(0, 0, Direcao.N), new Comandos []{Comandos.M});
        Comandos.M.executar(sonda);

        assertEquals(0, sonda.getPosicao().getX());
        assertEquals(1, sonda.getPosicao().getY());

        sonda = new Sonda(new Posicao(2, 0, Direcao.E), new Comandos []{Comandos.M});
        Comandos.M.executar(sonda);

        assertEquals(3, sonda.getPosicao().getX());
        assertEquals(0, sonda.getPosicao().getY());
    }

    @Test
    public void deveGirarASondaNoventaGrausAEsquerda() {
        Sonda sonda = new Sonda(new Posicao(0, 0, Direcao.N), new Comandos []{Comandos.M});

        Comandos.L.executar(sonda);

        assertEquals(Direcao.W, sonda.getPosicao().getDirecao());
    }

    @Test
    public void deveGirarASondaNoventaGrausADireita() {
        Sonda sonda = new Sonda(new Posicao(0, 0, Direcao.N), new Comandos []{Comandos.M});

        Comandos.R.executar(sonda);

        assertEquals(Direcao.E, sonda.getPosicao().getDirecao());
    }

}