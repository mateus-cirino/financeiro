/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.extensoes;

import banco.DB;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author mateu
 */
public abstract class Model {

    private EntityManager em;

    public Model() {

    }

    public Model(EntityManager em) {
        this.em = em;
    }

    public void create() {
        if (this.em != null) {
            DB.persistir(this, this.em);
        } else {
            DB.persistir(this);

        }
    }

    public void update() {
        if (this.em != null) {
            DB.atualizar(this, this.em);
        } else {
            DB.atualizar(this);
        }
    }

    public void delete() {
        if (this.em != null) {
            DB.deletar(this, this.em);
        } else {
            DB.deletar(this);
        }
    }

    public Object buscar(int id) {
        if (this.em != null) {
            return DB.buscar(this.getClass(), id, this.em);
        } else {
            return DB.buscar(this.getClass(), id);
        }
    }

    public List<Object> buscarTodos() {
        if (this.em != null) {
            return DB.buscarTodos(this.getClass(), this.em);
        } else {
            return DB.buscarTodos(this.getClass());
        }
    }

    public List<Object> where(String where) {
        if (this.em != null) {
        return DB.where(where, this.getClass(), this.em);
        } else {
        return DB.where(where, this.getClass());
        }
    }

    public EntityManager getEm() {
        return em;
    }
}
