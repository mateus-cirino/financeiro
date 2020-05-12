package modelos;

import modelos.extensoes.Model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProdutoMovimento extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public enum Operacao {
        E("Entrada"),
        S("Saída");

        private String descricao;

        Operacao(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
    private Operacao tipo;
    private String data;
    private String descricao;
    private Double quantidade;
    @ManyToOne
    private Produto produto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(Operacao tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public Operacao getTipo() {
        return tipo;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setData(Date data) {
        DateFormat formatoData = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.data = formatoData.format(data);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "ProdutoMovimento [data=" + data + ", descricao=" + descricao + ", id=" + id + ", idProduto=" + produto
                + ", quantidade=" + quantidade + ", tipo=" + tipo + "]";
    }

}
