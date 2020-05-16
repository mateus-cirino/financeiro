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

    public void create() {
        DB.persistir(this);
    }

    public void update() {
        DB.atualizar(this);
    }

    public void delete() {
        DB.deletar(this);
    }

    public Object buscar(int id) {
        return DB.buscar(this.getClass(), id);
    }
    
    public List<Object> buscarTodos() {
        return DB.buscarTodos(this.getClass());
    }
    
    public List<Object> where(String where) {
        return DB.where(where, this.getClass());
    }
}
