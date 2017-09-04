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

        if ((this.x * -1) > x || this.x < x)
            throw new LimiteUltrapassadoExcecao("O valor do eixo X " + x + " ultrapassa o limite superior");

        if ((this.y * -1) > y || this.y < y)
            throw new LimiteUltrapassadoExcecao("O valor do eixo X " + x + " ultrapassa o limite superior");
    }
}
