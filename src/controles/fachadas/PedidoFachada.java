package controles.fachadas;

import banco.DB;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import modelos.Cliente;
import modelos.Pedido;
import modelos.PedidoProduto;
import modelos.Produto;
import modelos.ProdutoMovimento;
import modelos.Vendedor;
import modelos.VendedorComissao;

public class PedidoFachada {

    public static void salvarPedido(Pedido pedido, Collection<Produto> produtos, DB db) {
        try {
            db.persistir(pedido);

            produtos.forEach(p -> {
                db.atualizar(p); //ATUALIZANDO O SALDO

                ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                produtoMovimento.setProduto(p);
                produtoMovimento.setQuantidade(p.getQuantidade());
                produtoMovimento.setData(new Date());
                produtoMovimento.setDescricao(ProdutoMovimento.Operacao.S.getDescricao());
                produtoMovimento.setTipo(ProdutoMovimento.Operacao.S);
                db.persistir(produtoMovimento);

                PedidoProduto pedidoProduto = new PedidoProduto();
                pedidoProduto.setPedido(pedido);
                pedidoProduto.setProduto(p);
                pedidoProduto.setQuantidade(p.getQuantidade());
                pedidoProduto.setValorUni(p.getPreco());
                pedidoProduto.calcValorTotal();
                db.persistir(pedidoProduto);
            });

            Cliente cliente = pedido.getCliente();
            cliente.setUltimaCompra(new Date());
            db.atualizar(cliente);

            Vendedor vendedor = pedido.getVendedor();

            VendedorComissao vendedorComissao = new VendedorComissao();
            vendedorComissao.setPedido(pedido);
            vendedorComissao.setVendedor(vendedor);
            vendedorComissao.setPercentual(vendedor.getPercentualComissao());

            PedidoProduto pedidoProduto = new PedidoProduto();

            vendedorComissao.setValor(
                    vendedor.getPercentualComissao() * ((List<PedidoProduto>) (Object) db.where("pedido_id = " + pedido.getId(), pedidoProduto)).stream()
                    .mapToDouble(PedidoProduto::getValorTotal)
                    .sum()
            );

            db.persistir(vendedorComissao);

            db.fecharModoDeTransacao();
        } catch (Exception e) {
            db.erroNaTransacao();
            throw new RuntimeException("Não foi possível efetuar a operacao de "
                    + "salvar"
                    + "\nError: " + e.getMessage());
        }
    }
    
    public static void excluirPedido(Pedido pedido, DB db) {
        try {
            VendedorComissao vendedorComissao = new VendedorComissao();
            db.where("pedido_id = " + pedido.getId(), vendedorComissao)
                    .forEach(vc -> {
                        db.deletar(vc);
                    });

            PedidoProduto pedidoProduto = new PedidoProduto();

            db.where("pedido_id = " + pedido.getId(), pedidoProduto)
                    .forEach(pp -> {
                        Produto produto = new Produto();
                        produto = (Produto) produto.buscar(((PedidoProduto) pp).getProduto().getId());
                        produto.setSaldo(produto.getSaldo() + ((PedidoProduto) pp).getQuantidade());
                        db.atualizar(produto);
                        
                        ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                        produtoMovimento.setProduto(produto);
                        produtoMovimento.setQuantidade(((PedidoProduto) pp).getQuantidade());
                        produtoMovimento.setData(new Date());
                        produtoMovimento.setDescricao(ProdutoMovimento.Operacao.E.getDescricao());
                        produtoMovimento.setTipo(ProdutoMovimento.Operacao.E);
                        produtoMovimento.create();
                        db.deletar(pp);
                    });

            db.deletar(pedido);
            
            db.fecharModoDeTransacao();
        } catch (Exception e) {
            db.erroNaTransacao();
            throw new RuntimeException("Não foi possível efetuar a operacao de "
                    + "excluir"
                    + "\nError: " + e.getMessage());
        }
    }
    
}
