package com.jsapce.jpapagination.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_CLIENTE")
@Data
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	private Integer codigoCliente;

	@OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY)
	private ClienteDetalhe detalhe;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "CLIENTE_ENDERECO", joinColumns = {
			@JoinColumn(name="CLIENTE_ID")
	}, inverseJoinColumns = {@JoinColumn(name="ENDERECO_ID")})
	private List<Endereco> enderecos;
	

}
