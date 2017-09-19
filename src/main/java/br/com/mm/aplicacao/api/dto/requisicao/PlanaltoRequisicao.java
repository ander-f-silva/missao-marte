package br.com.mm.aplicacao.api.dto.requisicao;

import br.com.mm.dominio.Planalto;
import org.modelmapper.ModelMapper;

import java.beans.ConstructorProperties;
import java.util.Set;

public class PlanaltoRequisicao {

    private LimiteRequisicao superior;
    private Set<SondaRequisicao> sondas;

    @ConstructorProperties({"superior", "sondas"})
    public PlanaltoRequisicao(LimiteRequisicao superior, Set<SondaRequisicao> sondas) {
        this.superior = superior;
        this.sondas = sondas;
    }

    public LimiteRequisicao getSuperior() {
        return superior;
    }

    public Set<SondaRequisicao> getSondas() {
        return sondas;
    }

    public Planalto converter() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Planalto.class);
    }

}
