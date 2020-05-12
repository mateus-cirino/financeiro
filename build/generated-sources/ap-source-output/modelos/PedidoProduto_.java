package modelos;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PedidoProduto.class)
public abstract class PedidoProduto_ {

	public static volatile SingularAttribute<PedidoProduto, Produto> produto;
	public static volatile SingularAttribute<PedidoProduto, Double> valorTotal;
	public static volatile SingularAttribute<PedidoProduto, Pedido> pedido;
	public static volatile SingularAttribute<PedidoProduto, Integer> id;
	public static volatile SingularAttribute<PedidoProduto, Double> quantidade;
	public static volatile SingularAttribute<PedidoProduto, Double> valorUni;

	public static final String PRODUTO = "produto";
	public static final String VALOR_TOTAL = "valorTotal";
	public static final String PEDIDO = "pedido";
	public static final String ID = "id";
	public static final String QUANTIDADE = "quantidade";
	public static final String VALOR_UNI = "valorUni";

}

