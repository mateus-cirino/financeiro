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
public class TesteSemCommit {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setCpf("2139238091");
        cliente.setNome("Mateus Cirino");
        cliente.create();
        
        cliente.setCpf("1000000000000");
        //cliente.update();
     
    }
}
