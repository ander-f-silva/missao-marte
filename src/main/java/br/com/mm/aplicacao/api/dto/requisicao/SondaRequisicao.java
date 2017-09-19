package br.com.mm.aplicacao.api.dto.requisicao;


import br.com.mm.dominio.enumeradores.Comandos;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.beans.ConstructorProperties;

@JsonPropertyOrder({ "x", "y", "direcao"})
public class SondaRequisicao {

    private PosicaoRequisicao posicao;
    private Comandos[] comandos;

    @ConstructorProperties({"posicao", "comandos"})
    public SondaRequisicao(PosicaoRequisicao posicao, Comandos[] comandos) {
        this.posicao = posicao;
        this.comandos = comandos;
    }

    public PosicaoRequisicao getPosicao(){
        return posicao;
    }

    public Comandos[] getComandos() {
        return comandos;
    }
}
