package br.com.glapp.Recursos;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Modelo.Produto;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
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
@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
public class RecursoProduto extends ConfiguracaoDaAplicacao {

    @POST
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
        try {
            jpa.deletar(id, Produto.class);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{id}")
    public Produto getProdutoById(@PathParam("id") Long id) {
        try {
            System.out.println("ENTROU AQUI");
            Produto prod = (Produto) jpa.findNamedQueryOB("Produto.findBy.idProduto", "idProduto", id);
            System.out.println("EAN : " + prod.getCodigoEAN());
            return prod;
        } catch (DAOException ex) {
            Logger.getLogger(RecursoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/procura")
    public Produto getProdutoByOutros(@QueryParam("campo") String campo, @QueryParam("valor") String valor) {
        try {
            return (Produto) filtro.retornaProduto(campo, valor);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/all")
    public List<Produto> getProdutoByAll() {
        try {
            List<Produto> listTP = (List<Produto>) filtro.retornaProduto("all", "all");
            System.out.println("TAMANHO: " + listTP.size());
            return listTP;
        } catch (DAOException ex) {
            System.out.println("ERROOOOO: " + ex.getMessage());
            Logger.getLogger(RecursoTipoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
