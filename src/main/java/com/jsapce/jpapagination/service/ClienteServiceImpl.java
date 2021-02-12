package com.jsapce.jpapagination.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsapce.jpapagination.dto.ClienteDto;
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
	public Page<Cliente> pesquisaCliente(ClienteDto dto, Pageable pageable) {

		return repository.findAll(dto.toSpec(), pageable);
	}

}
