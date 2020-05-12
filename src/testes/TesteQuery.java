/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import modelos.Cliente;

/**
 *
 * @author mateu
 */
public class TesteQuery {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setCpf("2139238091");
        cliente.setNome("Mateus Cirino");
        cliente.create();

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Saulo");
        cliente2.setCpf("1");
        cliente2.create();
        
        cliente2.setCpf("2");
        //cliente.setCpf("1000000000000");
        //cliente.update();
        cliente.buscarTodos();
        System.out.println(cliente.where("nome LIKE '%Mateus%'"));
        System.out.println(cliente.where("cpf LIKE '1'"));
        
    }
}
