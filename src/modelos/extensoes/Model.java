/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.extensoes;

import banco.Eloquent;
import java.util.List;

/**
 *
 * @author mateu
 */
public abstract class Model {

    public void create() {
        Eloquent.persistir(this);
    }

    public void update() {
        Eloquent.atualizar(this);
    }

    public void delete() {
        Eloquent.deletar(this);
    }

    public Object buscar(int id) {
        return Eloquent.buscar(this.getClass(), id);
    }

    public List<Object> buscarTodos() {
        return Eloquent.buscarTodos(this.getClass());
    }

    public List<Object> where(String where) {
        return Eloquent.where(where, this.getClass());
    }

}
