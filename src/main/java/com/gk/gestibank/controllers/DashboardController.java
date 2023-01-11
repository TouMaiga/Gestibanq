package com.gk.gestibank.controllers;



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
    	
    	
        
       
        
	
}
