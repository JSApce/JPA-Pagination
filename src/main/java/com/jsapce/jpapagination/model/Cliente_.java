package com.jsapce.jpapagination.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Cliente.class)
public class Cliente_ {

	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SingularAttribute<Cliente, String> nome;
	
	public static final String ID = "id";
	public static final String NOME = "nome";
	
}
