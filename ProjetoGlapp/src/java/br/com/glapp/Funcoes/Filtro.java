package br.com.glapp.Funcoes;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Controle.JPA.GenericoJpaController;
import br.com.glapp.Modelo.Produto;
import br.com.glapp.Modelo.TipoProduto;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class Filtro {

    private GenericoJpaController jpa;

    public Filtro(GenericoJpaController jpa) {
        this.jpa = jpa;
    }

    public Object retornaTipoProduto(String campo, Object valor) throws DAOException {
        if (campo.equals("descricao")) {
            return (TipoProduto) jpa.findNamedQueryOB("TipoProduto.findBy.descricao", campo, "%" + valor + "%");
        } else if (campo.equals("all")) {
            return (List<TipoProduto>) jpa.findNamedQuery("TipoProduto.findBy.All");
        }
        return null;
    }

    public Object retornaProduto(String campo, Object valor) throws DAOException {
        if (campo.equals("nome")) {
            return (Produto) jpa.findNamedQueryOB("Produto.findBy.nome", campo, "%" + valor + "%");
        } else if (campo.equals("estabelecimento")) {
            Produto prod = (Produto) jpa.findNamedQueryOB("Produto.findBy.estabelecimento.nome", campo, "%" + valor + "%");
            return prod;
        } else if (campo.equals("all")) {
            return (List<Produto>) jpa.findNamedQuery("Produto.findBy.All");
        }
        return null;
    }
}
