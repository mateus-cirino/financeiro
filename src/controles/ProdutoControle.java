package controles;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import modelos.Produto;

public class ProdutoControle {

    public static Collection<Map<String, String>> listarProdutos() {
        List<Produto> produtos = new LinkedList<>();
        Produto produto = new Produto();
        produto.buscarTodos().forEach((p) -> {
            produtos.add((Produto) p);
        });
        return produtos.stream()
                .map(Produto::toMap)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
