package br.com.glapp.Recursos;

import br.com.glapp.Controle.JPA.GenericoJpaController;
import br.com.glapp.Funcoes.Filtro;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Patrick
 */
@ApplicationPath("recursos")
public class ConfiguracaoDaAplicacao extends Application {

    //@PersistenceContext(unitName = "ProjetoGlappPU")
    public final Filtro filtro;
    public final GenericoJpaController jpa;
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoGlappPU");

    public ConfiguracaoDaAplicacao() {
        jpa = new GenericoJpaController(emf);
        filtro = new Filtro(jpa);
    }

}
