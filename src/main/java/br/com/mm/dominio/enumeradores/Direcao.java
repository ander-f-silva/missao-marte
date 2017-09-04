package br.com.mm.dominio.enumeradores;

public enum  Direcao {

    N, S, E, W;

    public Direcao moverParaEsquerda() {
        switch (this) {
            case N:
                return W;
            case W:
                return S;
            case S:
                return E;
            default:
                return N;
        }
    }

    public Direcao moverParaDireita() {
        switch (this) {
            case N:
                return E;
            case E:
                return S;
            case S:
                return W;
            default:
                return N;
        }
    }
}
