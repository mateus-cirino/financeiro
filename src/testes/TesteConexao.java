/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelos.Cliente;

/**
 *
 * @author mateu
 */
public class TesteConexao {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("financeiroPU");
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Cliente cliente = new Cliente();
        cliente.setCpf("09209202");
        cliente.setNome("Mateus Cirino");
        
        entityManager.persist((Object)cliente);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
    }
}
