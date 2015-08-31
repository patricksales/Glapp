package br.com.glapp.Teste;

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
