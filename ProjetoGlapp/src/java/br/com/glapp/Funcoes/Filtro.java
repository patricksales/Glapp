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

    public List<TipoProduto> retornaTipoProduto(String campo, Object valor) throws DAOException {
        if (campo.equals("descricao")) {
            return jpa.findNamedQuery("TipoProduto.findBy.descricao", campo, "%" + valor + "%");
        } else if (campo.equals("all")) {
            return jpa.findNamedQuery("TipoProduto.findBy.All");
        } else if (campo.equals("idTipoProduto")) {
            return jpa.findNamedQuery("TipoProduto.findBy.idTipoProduto", "idTipoProduto", valor);
        } else {
            return null;
        }
    }

    public List<Produto> retornaProduto(String campo, Object valor) throws DAOException {
        if (campo.equals("nome")) {
            return jpa.findNamedQuery("Produto.findBy.nome", campo, "%" + valor + "%");
        } else if (campo.equals("codigoEAN")) {
            return jpa.findNamedQuery("Produto.findBy.codigoEAN", campo, "%" + valor + "%");
        } else if (campo.equals("estabelecimento")) {
            return jpa.findNamedQuery("Produto.findBy.estabelecimento.nome", campo, "%" + valor + "%");
        } else if (campo.equals("all")) {
            return jpa.findNamedQuery("Produto.findBy.All");
        } else {
            return null;
        }
    }

    public List<Restaurante> retornaRestaurante(String campo, Object valor) throws DAOException {
        if (campo.equals("idRestaurante")) {
            return jpa.findNamedQuery("Restaurante.findBy.idRestaurante", campo, valor);
        } else if (campo.equals("nome")) {
            return jpa.findNamedQuery("Restaurante.findBy.nome", campo, "%" + valor + "%");
        } else if (campo.equals("cidade")) {
            return jpa.findNamedQuery("Restaurante.findBy.cidade", campo, "%" + valor + "%");
        } else if (campo.equals("endereco")) {
            return jpa.findNamedQuery("Restaurante.findBy.endereco", campo, "%" + valor + "%");
        } else if (campo.equals("all")) {
            return (List<Restaurante>) jpa.findNamedQuery("Restaurante.findBy.All");
        } else {
            return null;
        }
    }

    public List<Estabelecimento> retornaEstabelecimento(String campo, Object valor) throws DAOException {
        if (campo.equals("idEstabelecimento")) {
            return jpa.findNamedQuery("Estabelecimento.findBy.idEstabelecimento", campo, valor);
        } else if (campo.equals("nome")) {
            return jpa.findNamedQuery("Estabelecimento.findBy.nome", campo, "%" + valor + "%");
        } else if (campo.equals("cidade")) {
            return jpa.findNamedQuery("Estabelecimento.findBy.cidade", campo, "%" + valor + "%");
        } else if (campo.equals("endereco")) {
            return jpa.findNamedQuery("Estabelecimento.findBy.endereco", campo, "%" + valor + "%");
        } else if (campo.equals("all")) {
            return jpa.findNamedQuery("Estabelecimento.findBy.All");
        } else if (campo.equals("unidade")) {
            return jpa.findNamedQuery("Estabelecimento.findBy.unidade", campo, "%" + valor + "%");
        } else {
            return null;
        }
    }

}
