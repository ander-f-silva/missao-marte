package br.com.mm.aplicacao.api.dto.requisicao;

import br.com.mm.dominio.enumeradores.Direcao;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.beans.ConstructorProperties;
import java.io.Serializable;

public class PosicaoRequisicao {

    private long x;
    private long y;
    private Direcao direcao;

    @ConstructorProperties({"x", "y", "direcao"})
    public PosicaoRequisicao(long x, long y, Direcao direcao) {
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
