package com.jsapce.jpapagination.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TB_ENDERECO")
@Data
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String logradouro;

	private String bairro;

	private LocalDate dataCadastro;

	@PrePersist
	private void onpre() {
		this.dataCadastro = LocalDate.now();
	}

}
