package com.jsapce.jpapagination.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jsapce.jpapagination.controller.ClienteDetalhe;

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

	

}
