package controles;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import modelos.Vendedor;

public class VendedorControle {

    public static Collection<Map<String, String>> listarVendedores() {
        List<Vendedor> vendedores = new LinkedList<>();
        Vendedor vendedor = new Vendedor();
        vendedor.buscarTodos().forEach((v) -> {
            vendedores.add((Vendedor) v);
        });
        return vendedores.stream()
                .map(Vendedor::toMap)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
