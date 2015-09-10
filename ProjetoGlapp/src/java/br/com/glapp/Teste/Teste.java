package br.com.glapp.Teste;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Controle.JPA.GenericoJpaController;
import br.com.glapp.Modelo.Estabelecimento;
import br.com.glapp.Modelo.Produto;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Patrick
 */
public class Teste {
    
    public static void main(String args[]) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoGlappPU");
        GenericoJpaController jpa = new GenericoJpaController(emf);
        Produto prod;
        List<Produto> list;
        try {
            Estabelecimento est = (Estabelecimento) jpa.findNamedQueryOB("Estabelecimento.findBy.idEstabelecimento", "idEstabelecimento", Long.valueOf("1"));
            prod = (Produto) jpa.findNamedQueryOB("Produto.findBy.nome", "nome", "%" + "sem" + "%");
            list = (List<Produto>) jpa.findNamedQuery("Produto.findBy.All");
        } catch (DAOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");
        System.exit(2);
    }
    
}
