package com.jsapce.jpapagination.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsapce.jpapagination.dto.ClienteDto;
import com.jsapce.jpapagination.model.Cliente;
import com.jsapce.jpapagination.service.ClienteService;

@RestController
public class ClienteController {

	private final ClienteService service;

	@Autowired
	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@GetMapping("/pesquisa")
	public Page<Cliente> buscaClienteComPaginacao(ClienteDto dto, Pageable pageable) {
		return service.pesquisaCliente(dto, pageable);
	}

}
