/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author mateu
 */
public class Eloquent {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("financeiroPU");

    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public static void persistir(Object objeto) {
        entityManager.getTransaction().begin();

        entityManager.persist(objeto);
        entityManager.detach(objeto);

        entityManager.getTransaction().commit();
    }

    public static void atualizar(Object objeto) {
        entityManager.getTransaction().begin();

        entityManager.detach(entityManager.merge(objeto));

        entityManager.getTransaction().commit();
    }

    public static void deletar(Object objeto) {
        entityManager.getTransaction().begin();

        entityManager.remove(entityManager.merge(objeto));

        entityManager.getTransaction().commit();
    }


    public static Object buscar(Class classe, int id) {
        entityManager.getTransaction().begin();

        Object objeto = entityManager.find(classe, id);

        entityManager.getTransaction().commit();

        return objeto;
    }


    public static List<Object> buscarTodos(Class classe) {
        Query query = entityManager.createQuery("FROM " + classe.getName(), classe);
        return query.getResultList();
    }


    public static List<Object> where(String where, Class classe) {
        Query query = entityManager.createQuery("FROM " + classe.getSimpleName() + " WHERE " + where);
        return query.getResultList();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
    
}
