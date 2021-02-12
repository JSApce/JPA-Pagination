package com.jsapce.jpapagination.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jsapce.jpapagination.model.Cliente;
import com.jsapce.jpapagination.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository repository;

	@Autowired
	public ClienteServiceImpl(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Cliente> pesquisaCliente(Specification<Cliente> spec) {

		return repository.findAll(spec);
	}

}
