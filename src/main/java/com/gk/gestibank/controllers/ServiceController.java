package com.gk.gestibank.controllers;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gk.gestibank.entities.Agent;
import com.gk.gestibank.entities.Service;
import com.gk.gestibank.repositories.ServiceRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/service/")
public class ServiceController {
	
	private ServiceRepository serviceRepository;
	

	public ServiceController(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}



	@GetMapping("list")
	public String listService(Model model) {

		List<Service> services =  (List<Service>) serviceRepository.findAll();
		
		model.addAttribute("services", services);

		return "service/listService";
	}
	
	@GetMapping("add")
	public String addService(Model model) {
		Service service = new Service();
    	model.addAttribute("service", service);
		return "service/addService";
	}
	
	@PostMapping("add")
    public String addAgent(@Valid Service service , BindingResult result, Model model) {
        if (result.hasErrors()) {
        	System.out.println(result);
            return "service/addService";
        }
        serviceRepository.save(service);
        return "redirect:list";
    }
}
