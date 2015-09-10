package br.com.glapp.Modelo;

import java.io.Serializable;
import javax.inject.Singleton;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Patrick
 */
@Entity
@DiscriminatorValue(value = "Restaurante")
@Singleton
@NamedQueries({
    @NamedQuery(name = "Restaurante.findBy.All", query = "SELECT R FROM Restaurante AS R"),
    @NamedQuery(name = "Restaurante.findBy.idRestaurante", query = "SELECT R FROM Restaurante AS R WHERE R.idEmpresa = :idRestaurante"),
    @NamedQuery(name = "Restaurante.findBy.nome", query = "SELECT R FROM Restaurante AS R WHERE R.nome LIKE :nome"),
    @NamedQuery(name = "Restaurante.findBy.cidade", query = "SELECT R FROM Restaurante AS R WHERE R.cidade LIKE :cidade"),
    @NamedQuery(name = "Restaurante.findBy.endereco", query = "SELECT R FROM Restaurante AS R WHERE  R.endereco LIKE :endereco")})
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
