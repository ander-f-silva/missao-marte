package br.com.mm.dominio;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PLANALTO")
public class Planalto {

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

    @ConstructorProperties({"superior", "sondas"})
    public Planalto(Limite superior, Set<Sonda> sondas) {
        this.superior = superior;
        this.sondas = sondas;
    }

    public Sonda[] implantar() {
        for (Sonda sonda : sondas)
            sonda.explorar(superior);

        return (Sonda[]) sondas.toArray();
    }
}
