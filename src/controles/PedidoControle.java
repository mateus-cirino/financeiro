package controles;

import banco.DB;
import controles.fachadas.PedidoFachada;
import java.util.Collection;
import java.util.LinkedList;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import modelos.Cliente;

import modelos.Pedido;
import modelos.Produto;
import modelos.Vendedor;

public class PedidoControle {

    public static void salvarPedido(Map<String, String> pedidoMap, Collection<Map<String, String>> produtosMap) {
        EntityManager em = DB.inicirModoDeTransacao();

        Pedido pedido = new Pedido(em);

        pedido.setData(new Date());
        pedido.setObservacao(pedidoMap.get("observacao"));

        Cliente cliente = new Cliente(em);

        pedido.setCliente((Cliente) cliente.buscar(Integer.parseInt(pedidoMap.get("cliente"))));

        Vendedor vendedor = new Vendedor(em);

        pedido.setVendedor((Vendedor) vendedor.buscar(Integer.parseInt(pedidoMap.get("vendedor"))));

        Collection<Produto> produtos = new LinkedList<>();

        produtosMap.forEach(p -> {
            Produto produto = new Produto(vendedor.getEm());
            produto = (Produto) produto.buscar(Integer.parseInt(p.get("id")));
            produto.setQuantidade(Double.parseDouble(p.get("saldo")));

            produtos.add(produto);
        });

        PedidoFachada.salvarPedido(pedido, produtos, em);
    }

    public static void removerPedido(String codigo) {
        EntityManager em = DB.inicirModoDeTransacao();

        Pedido pedido = new Pedido(em);
        pedido = (Pedido) pedido.buscar(Integer.parseInt(codigo));

        PedidoFachada.excluirPedido(pedido, em);
    }

    public static Collection<Map<String, String>> listarPedidos() {
        //AREA DE TESTES
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

        List<Pedido> pedidos = new LinkedList<>();
        Pedido pedido = new Pedido();
        pedido.buscarTodos().forEach((p) -> {
            pedidos.add((Pedido) p);
        });
        return pedidos.stream()
                .map(Pedido::toMap)
                .collect(Collectors.toCollection(LinkedList::new));
    }

}
