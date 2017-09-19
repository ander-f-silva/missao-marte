package br.com.mm.aplicacao.api.dto.requisicao;

import java.beans.ConstructorProperties;

public class LimiteRequisicao {

    private long x;
    private long y;

    @ConstructorProperties({"x", "y"})
    public LimiteRequisicao(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}
