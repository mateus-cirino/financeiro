package modelos;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelos.ProdutoMovimento.Operacao;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProdutoMovimento.class)
public abstract class ProdutoMovimento_ {

	public static volatile SingularAttribute<ProdutoMovimento, Operacao> tipo;
	public static volatile SingularAttribute<ProdutoMovimento, String> data;
	public static volatile SingularAttribute<ProdutoMovimento, Produto> produto;
	public static volatile SingularAttribute<ProdutoMovimento, Integer> id;
	public static volatile SingularAttribute<ProdutoMovimento, Double> quantidade;
	public static volatile SingularAttribute<ProdutoMovimento, String> descricao;

	public static final String TIPO = "tipo";
	public static final String DATA = "data";
	public static final String PRODUTO = "produto";
	public static final String ID = "id";
	public static final String QUANTIDADE = "quantidade";
	public static final String DESCRICAO = "descricao";

}

