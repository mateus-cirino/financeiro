package modelos;

import modelos.extensoes.Model;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PedidoProduto extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double quantidade;
    private Double valorUni;
    private Double valorTotal;
    @ManyToOne
    private Pedido pedido;
    @ManyToOne
    private Produto produto;

    public PedidoProduto() {

    }

    public PedidoProduto(EntityManager em) {
        super(em);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUni() {
        return valorUni;
    }

    public void setValorUni(Double valorUni) {
        this.valorUni = valorUni;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void calcValorTotal() {
        this.valorTotal = this.quantidade * this.valorUni;
    }

    @Override
    public String toString() {
        return "PedidoProduto [id=" + id + ", pedido=" + pedido + ", produto=" + produto + ", quantidade="
                + quantidade + ", valorTotal=" + valorTotal + ", valorUni=" + valorUni + "]";
    }

}
