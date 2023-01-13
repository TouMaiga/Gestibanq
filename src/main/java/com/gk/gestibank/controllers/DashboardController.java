package com.gk.gestibank.controllers;
       
       
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gk.gestibank.repositories.MessageRepository;

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
    

}
