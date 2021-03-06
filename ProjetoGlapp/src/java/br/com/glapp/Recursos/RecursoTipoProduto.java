package br.com.glapp.Recursos;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Modelo.TipoProduto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Patrick
 */
@Path("/tipoproduto")
@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
public class RecursoTipoProduto extends ConfiguracaoDaAplicacao {

    @POST
    @Consumes({"application/xml", "application/json"})
    public void criar(TipoProduto tipoProduto) {
        try {
            //System.out.println("ERRO: " + tipoProduto.toString());
            jpa.criar(tipoProduto);
        } catch (DAOException ex) {
            System.out.println("ERRO: " + ex.getMessage());
            Logger.getLogger(RecursoTipoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void editar(TipoProduto tipoProduto) {
        try {
            jpa.editar(tipoProduto);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoTipoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            jpa.deletar(id, TipoProduto.class);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{id}")
    public List<TipoProduto> getTipoProdutoById(@PathParam("id") Long id) {
        try {
            return filtro.retornaTipoProduto("idTipoProduto", id);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoTipoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/procura")
    public List<TipoProduto> getTipoProdutoByOutros(@QueryParam("campo") String campo, @QueryParam("valor") String valor) {
        try {
            return filtro.retornaTipoProduto(campo, valor);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoTipoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/all")
    public List<TipoProduto> getTipoProdutoByAll() {
        try {
            return filtro.retornaTipoProduto("all", "all");
        } catch (DAOException ex) {
            Logger.getLogger(RecursoTipoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
