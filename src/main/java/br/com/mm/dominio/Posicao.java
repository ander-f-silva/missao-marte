package br.com.mm.dominio;

import br.com.mm.dominio.enumeradores.Direcao;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.io.Serializable;

@Embeddable
public class Posicao implements Serializable {

    private static final long serialVersionUID = -1374432770703859791L;

    @Column(name = "EIXO_X")
    private long x;
    @Column(name = "EIXO_Y")
    private long y;
    @Enumerated(EnumType.STRING)
    private Direcao direcao;

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
