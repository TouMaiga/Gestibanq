package com.gk.gestibank.controllers;

<<<<<<< HEAD


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller

public class DashboardController {

	
	@GetMapping("/")
    //@ResponseBody
    public String home(Model model) {
		
		return "dashboard/index";
	}
	
	@GetMapping("/admin")
    //@ResponseBody
    public String admin(Model mode) {
		
		return "dashboard/admin";
	}
	
	@GetMapping("/client")
    //@ResponseBody
    public String client(Model mode) {
		
		return "dashboard/client";
	}
	
	@GetMapping("/agent")
    //@ResponseBody
    public String agent(Model mode) {
		
		return "dashboard/agent";
	}
    	
    	
        
       
        
	
=======
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
	

    @GetMapping("/")
    public String accueil(Model model) {
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

>>>>>>> 9128042ab65a7356e3203f9c359b2348c4c4192e
}
