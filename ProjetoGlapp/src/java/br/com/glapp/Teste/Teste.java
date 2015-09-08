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
        System.out.println("");
    }

}
