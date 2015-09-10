package br.com.glapp.Recursos;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Controle.JPA.GenericoJpaController;
import br.com.glapp.Funcoes.Filtro;
import br.com.glapp.Modelo.Estabelecimento;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
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
 * @author Patrick Sales
 */
@Path("/estabelecimento")
@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
public class RecursoEstabelecimento {

    @PersistenceContext(unitName = "ProjetoGlappPU")
    Filtro filtro;
    private final GenericoJpaController jpa;
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoGlappPU");

    public RecursoEstabelecimento() {
        jpa = new GenericoJpaController(emf);
        filtro = new Filtro(jpa);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void criar(Estabelecimento estabelecimento) {
        try {
            jpa.criar(estabelecimento);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoEstabelecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void editar(Estabelecimento estabelecimento) {
        try {
            jpa.editar(estabelecimento);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoEstabelecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        ///
    }

    @GET
    @Path("{id}")
    public Estabelecimento getEstabelecimentoById(@PathParam("id") Long id) {
        try {
            Estabelecimento rest = (Estabelecimento) filtro.retornaEstabelecimento("idEstabelecimento", id);
            return rest;
        } catch (DAOException ex) {
            Logger.getLogger(RecursoEstabelecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/procura")
    public Estabelecimento getEstabelecimentoByOutros(@QueryParam("campo") String campo, @QueryParam("valor") String valor) {
        try {
            return (Estabelecimento) filtro.retornaEstabelecimento(campo, valor);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoEstabelecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/all")
    public List<Estabelecimento> getEstabelecimentoByAll() {
        try {
            return (List<Estabelecimento>) filtro.retornaEstabelecimento("all", "all");
        } catch (DAOException ex) {
            Logger.getLogger(RecursoEstabelecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
