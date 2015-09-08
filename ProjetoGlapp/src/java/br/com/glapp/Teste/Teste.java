package br.com.glapp.Teste;

import br.com.glapp.Modelo.TipoProduto;
import com.google.gson.Gson;
import java.io.BufferedReader;
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
