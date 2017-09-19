package br.com.mm.dominio;


import br.com.mm.dominio.excecao.LimiteUltrapassadoExcecao;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Limite implements Serializable {

    private static final long serialVersionUID = -6209776425059063164L;

    @Column(name = "EIXO_X")
    private long x;
    @Column(name = "EIXO_Y")
    private long y;

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
