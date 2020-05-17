/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import modelos.Cliente;
import modelos.Produto;
import modelos.Vendedor;

/**
 *
 * @author mateu
 */
public class TesteInsert {
    public static void inserirDados() {
        //POPULANDO O BANCO PARA FAZER OS TESTES
        Cliente cliente = new Cliente();
        cliente.setCpf("23132");
        cliente.setNome("Mateus");
        cliente.create();

        Vendedor vendedor = new Vendedor();
        vendedor.setNome("Felipe");
        vendedor.setPercentualComissao(0.5);
        vendedor.create();

        Produto p1 = new Produto();
        p1.setDescricao("Banana");
        p1.setSaldo(1000.0);
        p1.setQuantidade(20.0);
        p1.setPreco(10.0);
        p1.setUnidade("KG");
        p1.create();

        Produto p2 = new Produto();
        p2.setDescricao("Feijao");
        p2.setSaldo(1000.0);
        p2.setQuantidade(220.0);
        p2.setPreco(210.0);
        p2.setUnidade("KG");
        p2.create();
        //FIM
    }
}
