/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import controles.ClienteControle;
import modelos.Cliente;

/**
 *
 * @author mateu
 */
public class TesteCliente {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setCpf("21123123");
        cliente.create();
        
        cliente.setCpf("1111");
        cliente.update();
        
        cliente.delete();
    }
}
