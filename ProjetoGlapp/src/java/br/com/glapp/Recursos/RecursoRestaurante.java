package br.com.glapp.Recursos;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Modelo.Restaurante;
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
@Path("/restaurante")
@Produces({MediaType.APPLICATION_JSON + ";charset=utf-8"})
public class RecursoRestaurante extends ConfiguracaoDaAplicacao {

    @POST
    @Consumes({"application/xml", "application/json"})
    public void criar(Restaurante restaurante) {
        try {
            jpa.criar(restaurante);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public void editar(Restaurante restaurante) {
        try {
            jpa.editar(restaurante);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            jpa.deletar(id, Restaurante.class);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Path("{id}")
    public List<Restaurante> getRestauranteById(@PathParam("id") Long id) {
        try {
            return filtro.retornaRestaurante("idRestaurante", id);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/procura")
    public List<Restaurante> getRestauranteByOutros(@QueryParam("campo") String campo, @QueryParam("valor") String valor) {
        try {
            return filtro.retornaRestaurante(campo, valor);
        } catch (DAOException ex) {
            Logger.getLogger(RecursoRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("/all")
    public List<Restaurante> getRestauranteByAll() {
        try {
            return (List<Restaurante>) filtro.retornaRestaurante("all", "all");
        } catch (DAOException ex) {
            Logger.getLogger(RecursoRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
