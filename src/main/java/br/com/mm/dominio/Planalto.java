package br.com.mm.dominio;

import java.beans.ConstructorProperties;

public class Planalto {

    private Limite superior;

    private Sonda[] sondas;

    @ConstructorProperties({"superior", "sondas"})
    public Planalto(Limite superior, Sonda[] sondas) {
        this.superior = superior;
        this.sondas = sondas;
    }

    public Sonda[] implantar() {

        for (Sonda sonda : sondas)
            sonda.explorar(superior);

        return sondas;
    }

}
