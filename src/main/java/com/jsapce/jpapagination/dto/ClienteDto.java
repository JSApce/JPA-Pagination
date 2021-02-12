package com.jsapce.jpapagination.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.jsapce.jpapagination.model.Cliente;

import lombok.Data;

@Data
public class ClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;

	public Specification<Cliente> toSpec() {
		return (root, query, builder) -> {
			List<Predicate> predicados = new ArrayList<>();

			if (StringUtils.hasText(nome)) {
				Path<String> campoNome = root.<String>get("nome");
				Predicate predicadoNome = builder.like(campoNome, "%" + nome + "%");
				predicados.add(predicadoNome);
			}
			return builder.and(predicados.toArray(new Predicate[0]));
		};
	}

}
