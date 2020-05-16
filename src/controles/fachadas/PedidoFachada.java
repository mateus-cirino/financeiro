package controles.fachadas;

import banco.DB;
import java.sql.Connection;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import modelos.Cliente;
import modelos.Pedido;
import modelos.PedidoProduto;
import modelos.Produto;
import modelos.ProdutoMovimento;
import modelos.Vendedor;
import modelos.VendedorComissao;

public class PedidoFachada {

    public static void salvarPedido(Pedido pedido, Collection<Produto> produtos) {
        try {
            DB.inicirModoDeTransacao();
            pedido.create();

            produtos.forEach(p -> {
                p.update(); //ATUALIZANDO O SALDO

                ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                produtoMovimento.setProduto(p);
                produtoMovimento.setQuantidade(p.getQuantidade());
                produtoMovimento.setData(new Date());
                produtoMovimento.setDescricao(ProdutoMovimento.Operacao.S.getDescricao());
                produtoMovimento.setTipo(ProdutoMovimento.Operacao.S);
                produtoMovimento.create();

                PedidoProduto pedidoProduto = new PedidoProduto();
                pedidoProduto.setPedido(pedido);
                pedidoProduto.setProduto(p);
                pedidoProduto.setQuantidade(p.getQuantidade());
                pedidoProduto.setValorUni(p.getPreco());
                pedidoProduto.calcValorTotal();
                pedidoProduto.create();
            });

            Cliente cliente = pedido.getCliente();
            cliente.setUltimaCompra(new Date());
            cliente.update();

            Vendedor vendedor = pedido.getVendedor();

            VendedorComissao vendedorComissao = new VendedorComissao();;
            vendedorComissao.setPedido(pedido);
            vendedorComissao.setVendedor(vendedor);
            vendedorComissao.setPercentual(vendedor.getPercentualComissao());

            PedidoProduto pedidoProduto = new PedidoProduto();
            List<PedidoProduto> pedidosProdutos = new LinkedList<>();

            //CONVERSAO
            pedidoProduto.where("pedido_id = " + pedido.getId()).forEach((pp) -> {
                pedidosProdutos.add((PedidoProduto) pp);
            });

            vendedorComissao.setValor(
                    vendedor.getPercentualComissao() * pedidosProdutos.stream()
                    .mapToDouble(PedidoProduto::getValorTotal)
                    .sum()
            );

            vendedorComissao.create();

            DB.fecharModoDeTransacao();
        } catch (Exception e) {
            DB.erroNaTransacao();
            throw new RuntimeException("Não foi possível efetuar a operacao de "
                    + "salvar"
                    + "\nError: " + e.getMessage());
        }
    }

    public static void excluirPedido(Pedido pedido) {
        try {
            DB.inicirModoDeTransacao();
            VendedorComissao vendedorComissao = new VendedorComissao();
            vendedorComissao.where("pedido_id = " + pedido.getId())
                    .forEach(vc -> {
                        ((VendedorComissao) vc).delete();
                    });

            PedidoProduto pedidoProduto = new PedidoProduto();


            pedidoProduto.where("pedido_id = " + pedido.getId())
                    .forEach(pp -> {
                        Produto produto = new Produto();
                        produto = (Produto) produto.buscar(((PedidoProduto)pp).getProduto().getId());
                        produto.setSaldo(produto.getSaldo() + ((PedidoProduto)pp).getQuantidade());
                        produto.update();

                        ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                        produtoMovimento.setProduto(produto);
                        produtoMovimento.setQuantidade(((PedidoProduto)pp).getQuantidade());
                        produtoMovimento.setData(new Date());
                        produtoMovimento.setDescricao(ProdutoMovimento.Operacao.E.getDescricao());
                        produtoMovimento.setTipo(ProdutoMovimento.Operacao.E);
                        produtoMovimento.create();
                        ((PedidoProduto) pp).delete();
                    });

            pedido.delete();

            DB.fecharModoDeTransacao();
        } catch (Exception e) {
            DB.erroNaTransacao();
            throw new RuntimeException("Não foi possível efetuar a operacao de "
                    + "excluir"
                    + "\nError: " + e.getMessage());
        }
    }
}
