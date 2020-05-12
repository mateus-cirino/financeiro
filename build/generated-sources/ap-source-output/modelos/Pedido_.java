package modelos;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, Cliente> cliente;
	public static volatile SingularAttribute<Pedido, String> observacao;
	public static volatile SingularAttribute<Pedido, Vendedor> vendedor;
	public static volatile SingularAttribute<Pedido, String> data;
	public static volatile SingularAttribute<Pedido, Integer> id;

	public static final String CLIENTE = "cliente";
	public static final String OBSERVACAO = "observacao";
	public static final String VENDEDOR = "vendedor";
	public static final String DATA = "data";
	public static final String ID = "id";

}

