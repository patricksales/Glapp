package br.com.glapp.Recursos;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Controle.JPA.GenericoJpaController;
import br.com.glapp.Funcoes.Filtro;
import br.com.glapp.Modelo.Produto;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrick
 */
@Path("/produto")
public class RecursoProduto {

    //@PersistenceContext(unitName = "ProjetoGlappPU")
    Filtro filtro;
    private GenericoJpaController jpa;

    public RecursoProduto() {
        jpa = new GenericoJpaController(Persistence.createEntityManagerFactory("ProjetoGlappPU"));
        filtro = new Filtro(jpa);
    }

    @POST
    //@Override
    @Consumes({"application/xml", "application/json"})
    public void criar(Produto produto) {
        try {
            //System.out.println("ERRO: " + tipoProduto.toString());
            jpa.criar(produto);
        } catch (DAOException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            Logger.getLogger(RecursoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    //@Override
    @Consumes({"application/xml", "application/json"})
    public void editar(Produto produto) {
        try {
            jpa.editar(produto);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        ///
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    //@Produces({MediaType.APPLICATION_JSON})
    public Produto getProdutoById(@PathParam("id") Long id) {
        try {
            Produto prod = (Produto) jpa.findNamedQueryOB("Produto.findBy.idProduto", "idProduto", id);
            return prod;
        } catch (DAOException ex) {
            Logger.getLogger(RecursoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/procura")
    @Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
    //@Produces({"application/xml", "application/json"})
    public Produto getProdutoByOutros(@QueryParam("campo") String campo, @QueryParam("valor") String valor) {
        try {
            System.out.println("PASSOU AQUI");
            return filtro.retornaProduto(campo, valor);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}