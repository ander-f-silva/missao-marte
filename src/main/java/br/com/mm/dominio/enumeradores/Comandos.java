package br.com.mm.dominio.enumeradores;


import br.com.mm.dominio.Posicao;
import br.com.mm.dominio.Sonda;
import br.com.mm.dominio.interfaces.Acao;

public enum Comandos {

    M(s -> s.getPosicao().andarUmaMalha()),
    L(s -> s.getPosicao().moverParaEsquerda()),
    R(s -> s.getPosicao().moverParaDireita());

    private Acao acao;

    Comandos(Acao acao) {
        this.acao = acao;
    }

    public void executar(Sonda sonda) {
        acao.executar(sonda);
    }

}
