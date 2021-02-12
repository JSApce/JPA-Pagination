package com.jsapce.jpapagination.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jsapce.jpapagination.dto.ClienteDto;
import com.jsapce.jpapagination.model.Cliente;

public interface ClienteService {

	Page<Cliente> pesquisaCliente(ClienteDto dto, Pageable pageable);

}
