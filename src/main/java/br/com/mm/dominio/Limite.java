package br.com.mm.dominio;


import br.com.mm.dominio.excecao.LimiteUltrapassadoExcecao;

import java.beans.ConstructorProperties;

public class Limite {

    private long x;
    private long y;

    @ConstructorProperties({"x", "y"})
    public Limite(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public void validarEixos(long x, long y) {
        if (-this.x > x || this.x < x)
            throw new LimiteUltrapassadoExcecao("O valor do eixo X " + x + " ultrapassa o limite superior");

        if (-this.y > y || this.y < y)
            throw new LimiteUltrapassadoExcecao("O valor do eixo X " + x + " ultrapassa o limite superior");
    }
}
