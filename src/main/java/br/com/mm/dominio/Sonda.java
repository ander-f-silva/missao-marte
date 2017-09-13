package br.com.mm.dominio;


import br.com.mm.dominio.enumeradores.Comandos;
import br.com.mm.dominio.enumeradores.Direcao;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javafx.geometry.Pos;

import java.beans.ConstructorProperties;

@JsonPropertyOrder({ "x", "y", "direcao"})
public class Sonda {

    private Posicao posicao;
    private Comandos[] comandos;

    @ConstructorProperties({"posicao", "comandos"})
    public Sonda(Posicao posicao, Comandos[] comandos) {
        this.posicao = posicao;
        this.comandos = comandos;
    }

    public void explorar(Limite superior) {
        for (Comandos comando : comandos) {
            comando.executar(this);
            superior.validarEixos(posicao.getX(), posicao.getY());
        }
    }

    public Posicao getPosicao(){
        return posicao;
    }

 }
