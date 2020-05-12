package controles;

import controles.fachadas.PedidoFachada;
import java.util.Collection;
import java.util.LinkedList;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Cliente;

import modelos.Pedido;
import modelos.Produto;
import modelos.Vendedor;

public class PedidoControle {

    public static void salvarPedido(Map<String, String> pedidoMap, Collection<Map<String, String>> produtosMap) {
        Pedido pedido = new Pedido();

        pedido.setData(new Date());
        pedido.setObservacao(pedidoMap.get("observacao"));

        Cliente cliente = new Cliente();

        pedido.setCliente((Cliente) cliente.buscar(Integer.parseInt(pedidoMap.get("cliente"))));

        Vendedor vendedor = new Vendedor();

        pedido.setVendedor((Vendedor) vendedor.buscar(Integer.parseInt(pedidoMap.get("vendedor"))));

        Collection<Produto> produtos = new LinkedList<>();

        produtosMap.forEach(p -> {
            Produto produto = new Produto();
            produto = (Produto) produto.buscar(Integer.parseInt(p.get("id")));
            produto.setQuantidade(Double.parseDouble(p.get("saldo")));

            produtos.add(produto);
        });

        PedidoFachada.salvarPedido(pedido, produtos);
    }

    public static void removerPedido(String codigo) {
        Pedido pedido = new Pedido();
        pedido = (Pedido) pedido.buscar(Integer.parseInt(codigo));

        //PedidoFachada.excluirPedido(pedido);
    }

    public static Collection<Map<String, String>> listarPedidos() {
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
