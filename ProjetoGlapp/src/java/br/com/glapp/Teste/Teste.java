package br.com.glapp.Teste;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Controle.JPA.GenericoJpaController;
import br.com.glapp.Modelo.Estabelecimento;
import br.com.glapp.Modelo.Produto;
import br.com.glapp.Modelo.TipoProduto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        /*String host = "ec2-54-197-241-24.compute-1.amazonaws.com";
         String port = "5432";
         String databaseName = "d93cql5fif9br2";
         String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName + "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
         Map<String, String> properties = new HashMap<String, String>();
         properties.put("javax.persistence.jdbc.url", jdbcUrl);
         properties.put("javax.persistence.jdbc.user", "dkbmhkzdjvycal");
         properties.put("javax.persistence.jdbc.password", "zokyJoulGQ9VdbINU7RGJWu2HC");
         properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
         properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
         EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("default", properties);
         GenericoJpaController jpa = new GenericoJpaController(emf1);
         TipoProduto tp = new TipoProduto();
         tp.setDescricao("SHOW");
         try {
         TipoProduto tpp = (TipoProduto) jpa.pesquisaObjeto(TipoProduto.class, 10L);
         tpp.setDescricao("Editado");
         jpa.editar(tpp);
         //jpa.criar(tp);
         System.out.println("");
         } catch (DAOException ex) {
         Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
         }*/
        //   emf = Persistence.createEntityManagerFactory("TestePostgreePU");
        System.out.println("");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoGlappPU");
        GenericoJpaController jpa = new GenericoJpaController(emf);
        Produto prod;
        List<Produto> list;
        try {
            jpa.findNamedQuery("Estabelecimento.findBy.All");
            
            Estabelecimento estab = (Estabelecimento) jpa.findNamedQueryOB("Estabelecimento.findBy.idEstabelecimento", "idEstabelecimento", 1L);
            System.out.println("" + jpa.findNamedQuery("Estabelecimento.findBy.proximidade", "latUsuario", estab.getLatitude(), "longUsuario", estab.getLongitude()));
            //jpa.deletar(3L, TipoProduto.class);
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
