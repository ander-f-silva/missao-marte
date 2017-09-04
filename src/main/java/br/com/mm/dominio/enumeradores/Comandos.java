package br.com.mm.dominio.enumeradores;


import br.com.mm.dominio.Posicao;
import br.com.mm.dominio.interfaces.Acao;

public enum Comandos {

    M(p -> p.andarUmaMalha()),
    L(p -> p.moverParaEsquerda()),
    R(p -> p.moverParaDireita());

    private Acao acao;

    Comandos(Acao acao) {
        this.acao = acao;
    }

    public void executar(Posicao posicao) {
        acao.executar(posicao);
    }

}
