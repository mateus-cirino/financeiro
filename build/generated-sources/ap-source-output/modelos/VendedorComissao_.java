package modelos;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(VendedorComissao.class)
public abstract class VendedorComissao_ {

	public static volatile SingularAttribute<VendedorComissao, Vendedor> vendedor;
	public static volatile SingularAttribute<VendedorComissao, Double> percentual;
	public static volatile SingularAttribute<VendedorComissao, Double> valor;
	public static volatile SingularAttribute<VendedorComissao, Pedido> pedido;
	public static volatile SingularAttribute<VendedorComissao, Integer> id;

	public static final String VENDEDOR = "vendedor";
	public static final String PERCENTUAL = "percentual";
	public static final String VALOR = "valor";
	public static final String PEDIDO = "pedido";
	public static final String ID = "id";

}

