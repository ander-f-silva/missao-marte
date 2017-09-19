package br.com.mm.dominio;


import br.com.mm.dominio.enumeradores.Comandos;
import br.com.mm.dominio.enumeradores.Direcao;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javafx.geometry.Pos;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.UUID;

@Entity
@Table(name = "TB_SONDA")
@JsonPropertyOrder({ "x", "y", "direcao"})
public class Sonda {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Embedded
    private Posicao posicao;
    @Transient
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

    public void moverEixos() {
        long x = posicao.getDirecao().moverEixoX(posicao.getX());
        long y = posicao.getDirecao().moverEixoY(posicao.getY());
        posicao =  new Posicao(x, y, posicao.getDirecao());
    }

    public void moverParaEsquerda() {
        posicao =  new Posicao(posicao.getX(), posicao.getY(), posicao.getDirecao().moverParaEsquerda());
    }

    public void moverParaDireita() {
        posicao =  new Posicao(posicao.getX(), posicao.getY(), posicao.getDirecao().moverParaDireita());
    }

    public Posicao getPosicao(){
        return posicao;
    }

 }
