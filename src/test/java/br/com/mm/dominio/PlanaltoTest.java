package br.com.mm.dominio;

import br.com.mm.dominio.enumeradores.Comandos;
import br.com.mm.dominio.enumeradores.Direcao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlanaltoTest {

    @Test
    public void deveMovimentarSondas() {

        Sonda[] sondas = new Sonda[2];

        sondas[0] = new Sonda(
                new Posicao(1, 2, Direcao.N),
                new Comandos[]{
                        Comandos.L, Comandos.M,
                        Comandos.L, Comandos.M,
                        Comandos.L, Comandos.M,
                        Comandos.L, Comandos.M,
                        Comandos.M
                });

        sondas[1] = new Sonda(
                new Posicao(3, 3, Direcao.E),
                new Comandos[]{
                        Comandos.M, Comandos.M, Comandos.R,
                        Comandos.M, Comandos.M, Comandos.R,
                        Comandos.M, Comandos.R, Comandos.R,
                        Comandos.M
                });

        Planalto planalto = new Planalto(new Limite(5, 5), sondas);

        Sonda[] sondaResultado = planalto.implantar();

        assertEquals(1, sondaResultado[0].getX());
        assertEquals(3, sondaResultado[0].getY());
        assertEquals(Direcao.N, sondaResultado[0].getDirecao());

        assertEquals(1, sondaResultado[0].getX());
        assertEquals(3, sondaResultado[0].getY());
        assertEquals(Direcao.N, sondaResultado[0].getDirecao());
    }

}