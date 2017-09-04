package br.com.mm.aplicacao.api;


import br.com.mm.infraestrutura.repositorio.Entidade;

import java.util.TreeMap;

public class PlanaltoResposta {

    private TreeMap<String, Object> resposta = new TreeMap<>();

    private Entidade entidade;

    public PlanaltoResposta(Entidade entidade) {
        this.entidade = entidade;
    }

    public TreeMap<String, Object> transformar() {
        resposta.put("id", entidade.getId());
        resposta.put("sondas", entidade.getElementos());
        return resposta;
    }
}
