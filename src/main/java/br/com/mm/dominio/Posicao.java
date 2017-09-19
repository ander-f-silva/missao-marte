package br.com.mm.dominio;

import br.com.mm.dominio.enumeradores.Direcao;

import javax.persistence.*;
import java.beans.ConstructorProperties;

@Embeddable
public class Posicao {

    @Column(name = "EIXO_X")
    private long x;
    @Column(name = "EIXO_X")
    private long y;
    @Enumerated(EnumType.STRING)
    private Direcao direcao;

    @ConstructorProperties({"x", "y", "direcao"})
    public Posicao(long x, long y, Direcao direcao) {
        this.x = x;
        this.y = y;
        this.direcao = direcao;
    }

    public long getX() { return x; }

    public long getY() {
        return y;
    }

    public Direcao getDirecao() {
        return direcao;
    }
}
