package br.com.mm.dominio.excecao;

public class LimiteUltrapassadoExcecao extends RuntimeException {

    public LimiteUltrapassadoExcecao(String mensagem) {
        super(mensagem);
    }
}
