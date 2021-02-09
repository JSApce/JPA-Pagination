package com.jsapce.jpapagination.service;

import org.springframework.data.domain.Page;

import com.jsapce.jpapagination.model.Cliente;

public interface ClienteService {
	
	Page<Cliente> findClienteByParams();

}
