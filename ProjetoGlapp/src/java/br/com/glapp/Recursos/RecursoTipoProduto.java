package br.com.glapp.Recursos;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Controle.JPA.GenericoJpaController;
import br.com.glapp.Funcoes.Filtro;
import br.com.glapp.Modelo.TipoProduto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
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
public class RecursoTipoProduto {

    //@PersistenceContext(unitName = "ProjetoGlappPU")
    Filtro filtro;
    private GenericoJpaController jpa;

    public RecursoTipoProduto() {
        //EntityManagerFactory emf = ;
        jpa = new GenericoJpaController(Persistence.createEntityManagerFactory("ProjetoGlappPU"));
        filtro = new Filtro(jpa);
    }

    @POST
    //@Override
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
    //@Override
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
        ///
    }

    @GET
    @Path("{id}")
    //@Produces({"application/json", "application/xml"})
    public TipoProduto getTipoProdutoById(@PathParam("id") Long id) {
        try {
            System.out.println("PASSOU AQUI");
            TipoProduto tp = (TipoProduto) jpa.findNamedQueryOB("TipoProduto.findBy.idTipoProduto", "idTipoProduto", id);
            return tp;
        } catch (DAOException ex) {
            Logger.getLogger(RecursoTipoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/procura")
    //@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public TipoProduto getTipoProdutoByOutros(@QueryParam("campo") String campo, @QueryParam("valor") String valor) {
        try {
            return (TipoProduto) filtro.retornaTipoProduto(campo, valor);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoTipoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/all")
    //@Produces({MediaType.APPLICATION_JSON})
    //@Produces({"application/xml", "application/json"})
    //@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
    public List<TipoProduto> getTipoProdutoByAll() {
        try {
            List<TipoProduto> listTP = (List<TipoProduto>) filtro.retornaTipoProduto("all", "all");
            return listTP;
        } catch (DAOException ex) {
            Logger.getLogger(RecursoTipoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
