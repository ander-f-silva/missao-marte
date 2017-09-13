package br.com.mm.dominio;


import br.com.mm.dominio.enumeradores.Comandos;
import br.com.mm.dominio.enumeradores.Direcao;
import br.com.mm.dominio.excecao.LimiteUltrapassadoExcecao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SondaTest {

    @Test
    public void deveMoverParaDireita() {
        Sonda sonda = new Sonda(new Posicao(3, 3, Direcao.E), new Comandos[] {Comandos.M,Comandos.M,Comandos.R,Comandos.M,Comandos.M,Comandos.R,Comandos.M,Comandos.R,Comandos.R,Comandos.M});

        sonda.explorar(new Limite(5, 5));

        assertEquals(5l,  sonda.getPosicao().getX());
        assertEquals(1l,  sonda.getPosicao().getY());
        assertEquals(Direcao.E,  sonda.getPosicao().getDirecao());
    }

    @Test
    public void deveMoverParaEsquerda() {
        Sonda sonda = new Sonda(new Posicao(0, 0, Direcao.N), new Comandos[] {Comandos.M,Comandos.M,Comandos.L});

        sonda.explorar(new Limite(5, 5));

        assertEquals(0l,  sonda.getPosicao().getX());
        assertEquals(2l,  sonda.getPosicao().getY());
        assertEquals(Direcao.W,  sonda.getPosicao().getDirecao());
    }

    @Test(expected = LimiteUltrapassadoExcecao.class)
    public void deveLancarExcecaoLimiteEixoYUltrapassado() {
        Sonda sonda = new Sonda(new Posicao(3, 3, Direcao.N), new Comandos[] {Comandos.M,Comandos.M,Comandos.M});

        sonda.explorar(new Limite(5, 2));
    }

    @Test(expected = LimiteUltrapassadoExcecao.class)
    public void deveLancarExcecaoLimiteEixoXUltrapassado() {
        Sonda sonda = new Sonda(new Posicao(2, 4, Direcao.N), new Comandos[] {Comandos.R,Comandos.M,Comandos.M,Comandos.M});

        sonda.explorar(new Limite(4, 2));
    }

}