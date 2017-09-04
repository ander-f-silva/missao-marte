package br.com.mm.dominio.enumeradores;

import br.com.mm.dominio.Posicao;
import org.junit.Test;

import static org.junit.Assert.*;


public class ComandosTest {

    @Test
    public void deveMoverSondaAtualizandoCoordenadasXY() {
        Posicao posicao = new Posicao(0, 0, Direcao.N);
        Comandos.M.executar(posicao);

        assertEquals(0, posicao.getX());
        assertEquals(1, posicao.getY());

        posicao = new Posicao(2, 0, Direcao.E);
        Comandos.M.executar(posicao);

        assertEquals(3, posicao.getX());
        assertEquals(0, posicao.getY());
    }

    @Test
    public void deveGirarASondaNoventaGrausAEsquerda() {
        Posicao posicao = new Posicao(0, 0, Direcao.N);
        Comandos.L.executar(posicao);

        assertEquals(Direcao.W, posicao.getDirecao());
    }

    @Test
    public void deveGirarASondaNoventaGrausADireita() {
        Posicao posicao = new Posicao(0, 0, Direcao.N);
        Comandos.R.executar(posicao);

        assertEquals(Direcao.E, posicao.getDirecao());
    }

}