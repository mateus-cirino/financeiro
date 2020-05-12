package controles;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import modelos.Cliente;

public class ClienteControle {

    public static Collection<Map<String, String>> listarClientes() {
        List<Cliente> clientes = new LinkedList<>();
        Cliente cliente = new Cliente();
        cliente.buscarTodos().forEach((c) -> {
            clientes.add((Cliente) c );
        });
        return clientes.stream()
                .map(Cliente::toMap)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
