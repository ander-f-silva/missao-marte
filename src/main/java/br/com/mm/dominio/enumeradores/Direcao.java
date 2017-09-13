package br.com.mm.dominio.enumeradores;

public enum Direcao {

    N {
        @Override
        public Direcao moverParaEsquerda() {
            return  W;
        }

        @Override
        public Direcao moverParaDireita() {
            return E;
        }

        @Override
        public long moverEixoX(long eixo) {
            return eixo;
        }

        @Override
        public long moverEixoY(long eixo) {
            return ++eixo;
        }
    },

    S {
        @Override
        public Direcao moverParaEsquerda() {
            return E;
        }

        @Override
        public Direcao moverParaDireita() {
            return W;
        }

        @Override
        public long moverEixoX(long eixo) {
            return eixo;
        }

        @Override
        public long moverEixoY(long eixo) {
            return --eixo;
        }

    },

    E {
        @Override
        public Direcao moverParaEsquerda() {
            return N;
        }

        @Override
        public Direcao moverParaDireita() {
            return S;
        }

        @Override
        public long moverEixoX(long eixo) {
            return ++eixo;
        }

        @Override
        public long moverEixoY(long eixo) {
            return eixo;
        }
    },

    W {
        @Override
        public Direcao moverParaEsquerda() {
            return S;
        }

        @Override
        public Direcao moverParaDireita() {
            return N;
        }

        @Override
        public long moverEixoX(long eixo) {
            return --eixo;
        }

        @Override
        public long moverEixoY(long eixo) {
            return eixo;
        }
    };

   public abstract Direcao moverParaEsquerda();

   public abstract Direcao moverParaDireita();

   public abstract long moverEixoX(long eixo);

   public abstract long moverEixoY(long eixo);

}
