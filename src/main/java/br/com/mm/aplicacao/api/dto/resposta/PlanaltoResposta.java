package br.com.mm.aplicacao.api.dto.resposta;


import br.com.mm.dominio.Planalto;
import br.com.mm.dominio.Sonda;
import br.com.mm.dominio.enumeradores.Direcao;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlanaltoResposta {

    private UUID id;
    private Set<SondaResposta> sondas;

    public PlanaltoResposta() {
        this.sondas = new HashSet<>();
    }

    public void converter(final Planalto planalto, final Set<Sonda> sondas) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(planalto, this);

        for(Sonda sonda : sondas) {
            long x = sonda.getPosicao().getX();
            long y = sonda.getPosicao().getY();
            Direcao direcao = sonda.getPosicao().getDirecao();

            this.sondas.add(new SondaResposta(x, y, direcao));
        }
    }
}
