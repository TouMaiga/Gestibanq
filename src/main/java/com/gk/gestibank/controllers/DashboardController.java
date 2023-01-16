package com.gk.gestibank.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

import com.gk.gestibank.entities.Actuality;
import com.gk.gestibank.entities.Message;
import com.gk.gestibank.entities.Service;
import com.gk.gestibank.entities.User;
import com.gk.gestibank.repositories.ActualityRepository;
import com.gk.gestibank.repositories.ServiceRepository;
import com.gk.gestibank.services.UserService;

@Controller
public class DashboardController {

	@Autowired
	private UserService userService;
	private ServiceRepository serviceRepository;
	private ActualityRepository actualityRepository;

	
	
	public DashboardController(UserService userService, ServiceRepository serviceRepository,
			ActualityRepository actualityRepository) {
		super();
		this.userService = userService;
		this.serviceRepository = serviceRepository;
		this.actualityRepository = actualityRepository;
	}

	@GetMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@GetMapping(value = "/registration")
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@PostMapping(value = "/registration")
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		User userExists = userService.findUserByEmail(user.getEmail());

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
		}
		return modelAndView;
	}

	/*
	 * @GetMapping("/403") public String error403() { return "/error/403"; }
	 */

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
