package br.com.mm.aplicacao.api;


import br.com.mm.dominio.Sonda;
import br.com.mm.dominio.enumeradores.Direcao;

public class SondaRespota{

    private long x;
    private long y;
    private Direcao direcao;

    public SondaRespota(Sonda sonda) {
        this.x = sonda.getPosicao().getX();
        this.y = sonda.getPosicao().getY();
        this.direcao = sonda.getPosicao().getDirecao();
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
