/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import banco.DB;
import modelos.Cliente;
import modelos.Produto;

/**
 *
 * @author mateu
 */
public class TesteTransacao {
    public static void main(String[] args) {
        try {
            DB.inicirModoDeTransacao();
            
            Cliente cliente = new Cliente();
            cliente.setCpf("219381209382109");
            cliente.setNome("Mateus C");
            cliente.create();
            
            Produto produto = new Produto();
            produto.setDescricao("Banana");
            produto.setPreco(20.2);
            produto.setSaldo(100.0);
            produto.setQuantidade(10.0);
            produto.setUnidade("Kilo");
            produto.create();
            
            cliente.setCpf("-1");
            cliente.update();
            
            DB.fecharModoDeTransacao();
        } catch (Exception e) {
            DB.erroNaTransacao();
        }
        
    }
}
