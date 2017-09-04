package br.com.mm.dominio.enumeradores;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirecaoTest {

    @Test
    public void deveMoverParaEsquerdaDoNorte() {
        assertEquals(Direcao.W, Direcao.N.moverParaEsquerda());
    }

    @Test
    public void deveMoverParaEsquerdaDoOeste() {
        assertEquals(Direcao.S, Direcao.W.moverParaEsquerda());
    }

    @Test
    public void deveMoverParaEsquerdaDoSul() {
        assertEquals(Direcao.E, Direcao.S.moverParaEsquerda());
    }

    @Test
    public void deveMoverParaEsquerdaDoLeste() {
        assertEquals(Direcao.N, Direcao.E.moverParaEsquerda());
    }

    @Test
    public void deveMoverParaDireitaDoNorte() {
        assertEquals(Direcao.E, Direcao.N.moverParaDireita());
    }

    @Test
    public void deveMoverParaDireitaDoSul() {
        assertEquals(Direcao.W, Direcao.S.moverParaDireita());
    }

    @Test
    public void deveMoverParaDireitaDoLest() {
        assertEquals(Direcao.S, Direcao.E.moverParaDireita());
    }

    @Test
    public void deveMoverParaDireitaDoOeste() {
        assertEquals(Direcao.N, Direcao.W.moverParaDireita());
    }

}