package com.questions.dam2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({ "/", "home", "" })
	public String home() {
		return "vistas/home";
	}

	@GetMapping("/acciones")
	public String acciones() {
		return "vistas/acciones";
	}

}
