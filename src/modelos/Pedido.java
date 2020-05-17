package modelos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import modelos.extensoes.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pedido extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data;
    private String observacao;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Vendedor vendedor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(Date data) {
        DateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.data = formatoData.format(data);
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Map<String, String> toMap() {
        Cliente cliente = new Cliente();

        Vendedor vendedor = new Vendedor();

        Map<String, String> pedidoMap = new HashMap<>();

        pedidoMap.put("id", Integer.toString(this.id));
        pedidoMap.put("data", this.data);
        pedidoMap.put("observacao", this.observacao);
        pedidoMap.put("cliente", this.cliente.getNome());
        pedidoMap.put("vendedor", this.vendedor.getNome());

        return pedidoMap;
    }

    @Override
    public String toString() {
        return "Pedido [data=" + data + ", id=" + id + ", cliente=" + cliente.getNome() + ", vendedor=" + vendedor.getNome()
                + ", observacao=" + observacao + "]";
    }

}
