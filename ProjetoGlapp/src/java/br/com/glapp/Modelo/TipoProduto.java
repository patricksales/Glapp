package br.com.glapp.Modelo;

import java.io.Serializable;
import javax.inject.Singleton;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Patrick
 */
@Entity
@Singleton
@NamedQueries({
    @NamedQuery(name = "TipoProduto.findBy.idTipoProduto", query = "SELECT T FROM TipoProduto AS T WHERE T.idTipoProduto = :idTipoProduto"),
    @NamedQuery(name = "TipoProduto.findBy.descricao", query = "SELECT T FROM TipoProduto AS T WHERE T.descricao LIKE :descricao")})
public class TipoProduto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTipoProduto;
    private String descricao;

    public Long getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(Long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "TipoProduto{" + "idTipoProduto=" + idTipoProduto + ", descricao=" + descricao + '}';
    }

}
