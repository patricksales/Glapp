package br.com.glapp.Funcoes;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Controle.JPA.GenericoJpaController;
import br.com.glapp.Modelo.Estabelecimento;
import br.com.glapp.Modelo.Produto;
import br.com.glapp.Modelo.Restaurante;
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
        } else if (campo.equals("idTipoProduto")) {
            System.out.println("ENREO");
            return (TipoProduto) jpa.findNamedQueryOB("TipoProduto.findBy.idTipoProduto", "idTipoProduto", valor);
        } else {
            return null;
        }
    }

    public Object retornaProduto(String campo, Object valor) throws DAOException {
        if (campo.equals("nome")) {
            return (Produto) jpa.findNamedQueryOB("Produto.findBy.nome", campo, "%" + valor + "%");
        } else if (campo.equals("estabelecimento")) {
            Produto prod = (Produto) jpa.findNamedQueryOB("Produto.findBy.estabelecimento.nome", campo, "%" + valor + "%");
            return prod;
        } else if (campo.equals("all")) {
            return (List<Produto>) jpa.findNamedQuery("Produto.findBy.All");
        } else {
            return null;
        }
    }

    public Object retornaRestaurante(String campo, Object valor) throws DAOException {
        if (campo.equals("idRestaurante")) {
            return (Restaurante) jpa.findNamedQueryOB("Restaurante.findBy.idRestaurante", campo, valor);
        } else if (campo.equals("nome")) {
            return (Restaurante) jpa.findNamedQueryOB("Restaurante.findBy.nome", campo, "%" + valor + "%");
        } else if (campo.equals("cidade")) {
            return (Restaurante) jpa.findNamedQueryOB("Restaurante.findBy.cidade", campo, "%" + valor + "%");
        } else if (campo.equals("endereco")) {
            return (Restaurante) jpa.findNamedQueryOB("Restaurante.findBy.endereco", campo, "%" + valor + "%");
        } else if (campo.equals("all")) {
            return (List<Restaurante>) jpa.findNamedQuery("Restaurante.findBy.All");
        } else {
            return null;
        }
    }

    public Object retornaEstabelecimento(String campo, Object valor) throws DAOException {
        if (campo.equals("idEstabelecimento")) {
            return (Estabelecimento) jpa.findNamedQueryOB("Estabelecimento.findBy.idEstabelecimento", campo, valor);
        } else if (campo.equals("nome")) {
            return (Estabelecimento) jpa.findNamedQueryOB("Estabelecimento.findBy.nome", campo, "%" + valor + "%");
        } else if (campo.equals("cidade")) {
            return (Estabelecimento) jpa.findNamedQueryOB("Estabelecimento.findBy.cidade", campo, "%" + valor + "%");
        } else if (campo.equals("endereco")) {
            return (Estabelecimento) jpa.findNamedQueryOB("Estabelecimento.findBy.endereco", campo, "%" + valor + "%");
        } else if (campo.equals("all")) {
            return (List<Estabelecimento>) jpa.findNamedQuery("Estabelecimento.findBy.All");
        } else if (campo.equals("unidade")) {
            return (Estabelecimento) jpa.findNamedQueryOB("Estabelecimento.findBy.unidade", campo, "%" + valor + "%");
        } else {
            return null;
        }
    }

}
