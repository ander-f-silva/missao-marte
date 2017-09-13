package br.com.mm.dominio.interfaces;

import br.com.mm.dominio.Sonda;

@FunctionalInterface
public interface Acao {

    void executar(Sonda sonda);

}
