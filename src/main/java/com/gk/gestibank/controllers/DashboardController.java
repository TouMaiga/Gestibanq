package com.gk.gestibank.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

import com.gk.gestibank.entities.Actuality;
import com.gk.gestibank.entities.Message;
import com.gk.gestibank.entities.Role;
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
					"Cet adresse mail est déjà utilisée");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user,"CLIENT", 0);
			modelAndView.addObject("successMessage", "Votre compte à été créé, vous receverai un mail quand il sera activé.");
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
	
	
    @GetMapping("/dashboard")
    public String dashbaord(Model model) {
    	
    	 //1-Récuparation de la session du user Connecté <<Authentication>>
    	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         //2-Récupéartion du User
    	 User user = userService.findUserByEmail(auth.getName());
         
         System.out.println(user);
         model.addAttribute("user", user);
         //3-Récupération des roles du user
         Set<Role> userRoles = user.getRoles();
         //4-Conversion du set vers tableau pour la récupération du premier role
         Object roles[] = userRoles.toArray();
         System.out.println(roles[0].toString()); // On suppose qu'on a un seul role par user
         //5-Récupéation du rôle : userRole
         Role role = (Role)roles[0];
         String userRole = role.getRole();
         System.out.println(userRole);
         
         
         switch(userRole) {
         case "ADMIN" : return "dashboard/admin"; 
         case "AGENT" : return "dashboard/agent";
         case "CLIENT" : return "dashboard/client";
         default : return "dashboard/index";
         }
        
    }
    
	@GetMapping("/devise")
	public String dashbaordDevise(Model model) {
		return "devise/devise";
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
