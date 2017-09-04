package br.com.mm.infraestrutura.repositorio;

import br.com.mm.infraestrutura.excecao.RegistroNaoEncontradoExcecao;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Repositorio {

    private List<Entidade> registros = new ArrayList<>();

    public Entidade adicionar(final Entidade entidade) {
        registros.add(entidade);
        return new Entidade(entidade.getId().toString(), entidade.getElementos());
    }

    public List<Entidade> buscarTodos() throws RegistroNaoEncontradoExcecao {
        if(registros.isEmpty())
            throw new RegistroNaoEncontradoExcecao();

        return registros;
    }

    public Entidade buscar(final String id) throws RegistroNaoEncontradoExcecao {
        return registros.stream()
                .filter(registro -> id.equals(registro.getId()))
                .findFirst()
                .orElseThrow(RegistroNaoEncontradoExcecao::new);
    }

    public void remover(final String id) throws RegistroNaoEncontradoExcecao {
       registros.remove(buscar(id));
    }
}
