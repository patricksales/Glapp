package br.com.glapp.Teste;

import br.com.glapp.Controle.JPA.Exception.DAOException;
import br.com.glapp.Controle.JPA.GenericoJpaController;
import br.com.glapp.Modelo.Produto;
import br.com.glapp.Modelo.TipoProduto;
import com.google.gson.Gson;
import java.io.BufferedReader;
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
        try {
            jpa.findNamedQuery("TipoProduto.findBy.All");
            jpa.findNamedQueryOB("Produto.findBy.estabelecimento.nome", "nome", "%" + "carre" + "%");
        } catch (DAOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Gson gson = new Gson();
            /*            BufferedReader br = new BufferedReader(
             new FileReader("c:\\file.json"));
             */
            //convert the json string back to object
            TipoProduto obj = gson.fromJson("{\"descricao\":\"Farinha\",\"idTipoProduto\":2}", TipoProduto.class);

            System.out.println(obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("");
    }

}
