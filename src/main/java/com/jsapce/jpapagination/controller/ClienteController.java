package com.jsapce.jpapagination.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Joiner;
import com.jsapce.jpapagination.model.Cliente;
import com.jsapce.jpapagination.service.ClienteService;
import com.jsapce.jpapagination.service.UserSpecificationsBuilder;
import com.jsapce.jpapagination.util.SearchOperation;

@RestController
public class ClienteController {

	private final ClienteService service;

	@Autowired
	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@GetMapping("/spec")
	public List<Cliente> findAllBySpecification(@RequestParam(value = "search") String search) {

		UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
		String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
		String operationSetPredicateFlagExper = ",";
		Pattern pattern = Pattern.compile("(\\w+?)(\\p{Punct}?)(" + operationSetExper
				+ ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?)(" + operationSetPredicateFlagExper + ")(\\p{Punct}?);");
		Matcher matcher = pattern.matcher(search + ";");

		while (matcher.find()) {
			builder.with(matcher.group(8), matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5),
					matcher.group(4), matcher.group(6));
		}

		Specification<Cliente> spec = builder.build();
		return service.pesquisaCliente(spec);
	}

}
