package br.com.mm.dominio;

import br.com.mm.dominio.excecao.LimiteUltrapassadoExcecao;
import org.junit.Test;

public class LimiteTest {

    @Test(expected = LimiteUltrapassadoExcecao.class)
    public void deveUltrapassarLimiteSuperiorEixoX() {
       Limite limite = new Limite(2,5);
       limite.validarEixos(6, 2);
    }

    @Test(expected = LimiteUltrapassadoExcecao.class)
    public void deveUltrapassarLimiteSuperiorEixoY() {
        Limite limite = new Limite(6,6);
        limite.validarEixos(3, 7);
    }

    @Test(expected = LimiteUltrapassadoExcecao.class)
    public void deveUltrapassarLimiteInferiorEixoX() {
        Limite limite = new Limite(3,3);
        limite.validarEixos(-4, 2);
    }

    @Test(expected = LimiteUltrapassadoExcecao.class)
    public void deveUltrapassarLimiteInferiorEixoY() {
        Limite limite = new Limite(6, 4);
        limite.validarEixos(3, -7);
    }
}