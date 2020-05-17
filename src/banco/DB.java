/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author mateu
 */
public class DB {

    private EntityManager entityManager;

    public DB() {
        this.entityManager = Eloquent.getEntityManagerFactory().createEntityManager();
    }

    public void iniciarTransacao() {
        if(!this.entityManager.isOpen()) {
            this.entityManager = Eloquent.getEntityManagerFactory().createEntityManager();
        }
        this.entityManager.getTransaction().begin();
    }

    public void fecharModoDeTransacao() {
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    public void erroNaTransacao() {
        this.entityManager.getTransaction().rollback();
        this.entityManager.close();
    }

    public void persistir(Object objeto) {
        this.entityManager.persist(objeto);
        this.entityManager.detach(objeto);
    }

    public void atualizar(Object objeto) {
        this.entityManager.detach(entityManager.merge(objeto));
    }

    public void deletar(Object objeto) {
        this.entityManager.remove(entityManager.merge(objeto));
    }

    public Object buscar(Object objeto, int id) {
        return this.entityManager.find(objeto.getClass(), id);
    }

    public List<Object> buscarTodos(Object objeto) {
        Query query = this.entityManager.createQuery("FROM " + objeto.getClass().getName(), objeto.getClass());
        return query.getResultList();
    }

    public List<Object> where(String where, Object objeto) {
        Query query = this.entityManager.createQuery("FROM " + objeto.getClass().getSimpleName() + " WHERE " + where);
        return query.getResultList();
    }
}
