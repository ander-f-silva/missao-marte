package br.com.mm.dominio;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PLANALTO")
public class Planalto implements Serializable{

    private static final long serialVersionUID = -7186930661902705579L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Embedded
    private Limite superior;

    @OneToMany
    @JoinColumn(name = "SONDA_ID")
    private Set<Sonda> sondas;

    public Planalto(Limite superior, Set<Sonda> sondas) {
        this.superior = superior;
        this.sondas = sondas;
    }

    public Set<Sonda> implantar() {
        for (Sonda sonda : sondas)
            sonda.explorar(superior);

        return sondas;
    }

    public Set<Sonda> getSondas() {
        return sondas;
    }
}
