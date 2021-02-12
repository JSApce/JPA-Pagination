package com.jsapce.jpapagination.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.jsapce.jpapagination.model.Cliente;

public interface ClienteService {

	List<Cliente> pesquisaCliente(Specification<Cliente> spec);

}
