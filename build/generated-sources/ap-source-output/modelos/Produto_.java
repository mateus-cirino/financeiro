package modelos;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, Double> preco;
	public static volatile SingularAttribute<Produto, String> unidade;
	public static volatile SingularAttribute<Produto, Integer> id;
	public static volatile SingularAttribute<Produto, Double> saldo;
	public static volatile SingularAttribute<Produto, Double> quantidade;
	public static volatile SingularAttribute<Produto, String> descricao;

	public static final String PRECO = "preco";
	public static final String UNIDADE = "unidade";
	public static final String ID = "id";
	public static final String SALDO = "saldo";
	public static final String QUANTIDADE = "quantidade";
	public static final String DESCRICAO = "descricao";

}

