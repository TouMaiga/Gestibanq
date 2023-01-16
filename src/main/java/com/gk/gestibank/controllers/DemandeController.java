package com.gk.gestibank.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gk.gestibank.entities.Agent;
import com.gk.gestibank.entities.Demande;
import com.gk.gestibank.repositories.AgentRepository;
import com.gk.gestibank.repositories.DemandeRepository;


@Controller
@RequestMapping("/demande/")
public class DemandeController {
	
	private final DemandeRepository demandeRepository;
	private final AgentRepository agentRepository;
	
	public DemandeController(DemandeRepository demandeRepository, AgentRepository agentRepository) {
		this.demandeRepository = demandeRepository;
		this.agentRepository = agentRepository;
	}


	@GetMapping("list")
	public String listMessages(Model model) {
		
		//List<Demande> demandes = demandeRepository.findAll();
		
		List<Demande> demandes = List.of(
				new Demande(1, "Etienne", "Corvé", "email@live.com", "creation de compte", "Je veux bien un nouveau compte", "16/01/2023", "nouveau"),
				new Demande(2, "Etienne", "Corvé","email@live.com", "creation de compte", "Je veux bien un nouveau compte", "16/01/2023", "nouveau"),
				new Demande(3, "Etienne", "Corvé","email@live.com", "creation de compte", "Je veux bien un nouveau compte", "16/01/2023", "affecté"),
				new Demande(4, "Etienne", "Corvé","email@live.com", "creation de compte", "Je veux bien un nouveau compte", "16/01/2023", "nouveau")
				);
		
		model.addAttribute("demandes", demandes);
		
		Iterable<Agent> agents = agentRepository.findAll();
		model.addAttribute("agents", agents);
				
		return "demande/listDemande";

	}

}
