package br.com.mm.aplicacao.api.dto.resposta;

import br.com.mm.dominio.enumeradores.Direcao;


public class SondaResposta {

    private long x;
    private long y;
    private Direcao direcao;

    public SondaResposta(long x, long y, Direcao direcao) {
        this.x = x;
        this.y = y;
        this.direcao = direcao;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public Direcao getDirecao() {
        return direcao;
    }
}
