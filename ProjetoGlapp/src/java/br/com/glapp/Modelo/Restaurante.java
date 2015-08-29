package br.com.glapp.Modelo;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Patrick
 */
@Entity
@DiscriminatorValue(value = "Restaurante")
public class Restaurante extends Empresa implements Serializable {

    private String cardapio;
    private String observacao;

    public String getCardapio() {
        return cardapio;
    }

    public void setCardapio(String cardapio) {
        this.cardapio = cardapio;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Restaurante(String cardapio, String observacao) {
        this.cardapio = cardapio;
        this.observacao = observacao;
    }

    public Restaurante() {
    }

}
