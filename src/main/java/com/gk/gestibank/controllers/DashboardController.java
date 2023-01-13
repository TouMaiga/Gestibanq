package com.gk.gestibank.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.gk.gestibank.entities.Actuality;
import com.gk.gestibank.entities.Message;
import com.gk.gestibank.entities.Service;
import com.gk.gestibank.repositories.ActualityRepository;
import com.gk.gestibank.repositories.MessageRepository;
import com.gk.gestibank.repositories.ServiceRepository;

@Controller
public class DashboardController {
    private final MessageRepository messageRepository;
	private final ServiceRepository serviceRepository;
	private final ActualityRepository actualityRepository;

	public DashboardController(ServiceRepository serviceRepository, ActualityRepository actualityRepository, MessageRepository messageRepository) {
		this.serviceRepository = serviceRepository;
		this.actualityRepository = actualityRepository;
		this.messageRepository = messageRepository;
	}

	@GetMapping("/")
	public String accueil(Model model) {

		Iterable<Service> services = serviceRepository.findAll();
		model.addAttribute("services", services);

		Iterable<Actuality> lp = actualityRepository.findAll();
		model.addAttribute("actualities", lp);
		
		  model.addAttribute("message", new Message()); 
		
		return "dashboard/index";
	}

	@GetMapping("/admin")
	public String dashbaordAdmin(Model model) {
		return "dashboard/admin";
	}

	@GetMapping("/agent")
	public String dashbaordAgent(Model model) {
		return "dashboard/agent";
	}

	@GetMapping("/client")
	public String dashbaordAClient(Model model) {
		return "dashboard/client";
	}


}
