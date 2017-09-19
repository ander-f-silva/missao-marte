package br.com.mm.dominio;


import br.com.mm.dominio.excecao.LimiteUltrapassadoExcecao;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.beans.ConstructorProperties;

@Embeddable
public class Limite {

    @Column(name = "EIXO_X")
    private long x;
    @Column(name = "EIXO_Y")
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
