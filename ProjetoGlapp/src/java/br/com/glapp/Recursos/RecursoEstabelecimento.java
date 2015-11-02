package br.com.glapp.Recursos;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Modelo.Estabelecimento;
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
 * @author Patrick Sales
 */
@Path("/estabelecimento")
@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
public class RecursoEstabelecimento extends ConfiguracaoDaAplicacao {

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
        try {
            jpa.deletar(id, Estabelecimento.class);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{id}")
    public List<Estabelecimento> getEstabelecimentoById(@PathParam("id") Long id) {
        try {
            return filtro.retornaEstabelecimento("idEstabelecimento", id, null, null);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoEstabelecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/procura")
    public List<Estabelecimento> getEstabelecimentoByOutros(@QueryParam("campo") String campo, @QueryParam("valor") String valor) {
        try {
            return filtro.retornaEstabelecimento(campo, valor, null, null);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoEstabelecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/distancia")
    public List<Estabelecimento> getEstabelecimentoByOutros2(@QueryParam("campo") String campo, @QueryParam("valor") String valor, @QueryParam("campo2") String campo2, @QueryParam("valor2") String valor2) {
        try {
            return filtro.retornaEstabelecimento(campo, valor, campo2, valor2);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoEstabelecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/all")
    public List<Estabelecimento> getEstabelecimentoByAll() {
        try {
            return (List<Estabelecimento>) filtro.retornaEstabelecimento("all", "all", null, null);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoEstabelecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
