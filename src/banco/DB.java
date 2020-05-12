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
public class DB {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("financeiroPU");

    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    private static boolean modoDeTransacao = false;

    public static void inicirModoDeTransacao() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        modoDeTransacao = true;
    }

    public static void fecharModoDeTransacao() {
        entityManager.getTransaction().commit();
        modoDeTransacao = false;
    }

    public static void erroNaTransacao() {
        entityManager.getTransaction().rollback();
        entityManager.close();
        modoDeTransacao = false;
    }

    public static void persistir(Object objeto) {
        if (!modoDeTransacao) {
            entityManager.getTransaction().begin();

            entityManager.persist(objeto);
            entityManager.detach(objeto);

            entityManager.getTransaction().commit();
        } else {
            entityManager.persist(objeto);
            entityManager.detach(objeto);
        }
    }

    public static void atualizar(Object objeto) {
        if (!modoDeTransacao) {
            entityManager.getTransaction().begin();

            entityManager.merge(objeto);
            entityManager.detach(objeto);

            entityManager.getTransaction().commit();
        } else {
            entityManager.merge(objeto);
            entityManager.detach(objeto);
        }
    }

    public static void deletar(Object objeto) {
        if (!modoDeTransacao) {
            entityManager.getTransaction().begin();

            entityManager.remove(objeto);

            entityManager.getTransaction().commit();
        } else {
            entityManager.remove(objeto);
        }
    }

    public static Object buscar(Class classe, int id) {
        Object objeto;
        if (!modoDeTransacao) {
            entityManager.getTransaction().begin();

            objeto = entityManager.find(classe, id);

            entityManager.getTransaction().commit();
        } else {
            objeto = entityManager.find(classe, id);
        }

        return objeto;
    }
    
    public static List<Object> buscarTodos(Class classe) {
        Query query = entityManager.createQuery("FROM " + classe.getName(), classe);
        return query.getResultList();
    }

    public static List where(String where, Class classe) {
       Query query = entityManager.createQuery("FROM " + classe.getSimpleName() + " WHERE " + where);
        return query.getResultList();
    }
}
