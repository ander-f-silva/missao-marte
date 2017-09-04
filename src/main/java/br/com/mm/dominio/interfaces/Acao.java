package br.com.mm.dominio.interfaces;

import br.com.mm.dominio.Posicao;

@FunctionalInterface
public interface Acao {

    void executar(Posicao sonda);

}
