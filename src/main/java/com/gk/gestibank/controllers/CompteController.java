package com.gk.gestibank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gk.gestibank.repositories.CompteRepository;

@Controller
@RequestMapping("/compte/")
public class CompteController {

	private final CompteRepository compteRepository;
	
	@Autowired
	public CompteController(CompteRepository compteRepository)
	{
			this.compteRepository = compteRepository;
	}
	
	
}
