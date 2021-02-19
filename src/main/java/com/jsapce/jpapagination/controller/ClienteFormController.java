package com.jsapce.jpapagination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteFormController {

	@GetMapping("/")
//	@ResponseBody
	public String index() {
		return "form";
	}

}
