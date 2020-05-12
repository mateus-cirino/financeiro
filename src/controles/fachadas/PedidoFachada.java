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
            List<Object> pedidosProdutos = new LinkedList<>();
            pedidoProduto.where("pedido_id = " + pedido.getId());

            
            pedidosProdutos.forEach((pp) -> {
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

    public static void excluirPedido(Pedido pedido, Connection conexao) {
        /*try {
            VendedorComissaoDao vendedorComissaoDao = new VendedorComissaoDao(conexao);
            vendedorComissaoDao.selectAll()
                    .forEach(vc -> {
                        vendedorComissaoDao.delete(vc);
                    });

            PedidoProdutoDao pedidoProdutoDao = new PedidoProdutoDao(conexao);

            ProdutoMovimentoDao produtoMovimentoDao = new ProdutoMovimentoDao(conexao);

            ProdutoDao produtoDao = new ProdutoDao(conexao);

            pedidoProdutoDao.selectAll()
                    .forEach(pp -> {
                        if (pp.getIdPedido() == pedido.getId()) {
                            Produto produto = new Produto();
                            produto = produtoDao.select(pp.getIdProduto()).get();
                            produto.setSaldo(produto.getSaldo() + pp.getQuantidade());
                            produtoDao.update(produto);

                            ProdutoMovimento produtoMovimento = new ProdutoMovimento();
                            produtoMovimento.setIdProduto(produto.getId());
                            produtoMovimento.setQuantidade(pp.getQuantidade());
                            produtoMovimento.setData(new Date());
                            produtoMovimento.setDescricao(ProdutoMovimento.Operacao.E.getDescricao());
                            produtoMovimento.setTipo(ProdutoMovimento.Operacao.E);
                            produtoMovimentoDao.inserir(produtoMovimento);

                            pedidoProdutoDao.delete(pp);
                        }
                    });

            PedidoDao pedidoDao = new PedidoDao(conexao);
            pedidoDao.delete(pedido);

            FabricaConexaoTransacional.commitTransacao(conexao);
        } catch (Exception e) {
            FabricaConexaoTransacional.rollbackTransacao(conexao);
            FabricaConexaoTransacional.closeConnection(conexao);
            throw new RuntimeException("Não foi possível efetuar a operacao de "
                    + "excluir"
                    + "\nError: " + e.getMessage());
        }*/
    }
}
