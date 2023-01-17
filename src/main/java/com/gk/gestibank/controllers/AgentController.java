package com.gk.gestibank.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.mail.javamail.JavaMailSender;

import com.gk.gestibank.entities.Agent;
import com.gk.gestibank.entities.Role;
import com.gk.gestibank.entities.User;
import com.gk.gestibank.repositories.AgentRepository;
import com.gk.gestibank.repositories.UserRepository;
import com.gk.gestibank.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/agent/")

public class AgentController {
	
	private final AgentRepository agentRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private UserService userService;
	
	@Autowired
    private JavaMailSender javaMailSender;

	
    @Autowired
    public AgentController(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    
    @GetMapping("list")
    //@ResponseBody
    public String listAgents(Model model) {

        
    	List<User> lu = (List<User>)userRepository.findAll(); // list de tout les Users
    	List<User>la = new ArrayList<>();
    	
    	for(User user : lu)
    	{
    		 Set<Role>userRoles = user.getRoles();
    		 Object roles[] = userRoles.toArray();
  	         Role role = (Role)roles[0]; 
  	         String userRole = role.getRole();
  	         if(userRole.equals("AGENT"))
  	        	 la.add(user);
    		
    	}
    	if(la.size()==0) {
    				la=null;			
    	}
    
    	//model.addAttribute("nbr", la.size());
        model.addAttribute("agents", la);
    	
        return "agent/listAgents";
    }
    
    @GetMapping("add")
    public String showAddAgentForm(Model model) {
    	 User user = new User();
         model.addAttribute("user", user);
        return "agent/addAgent";
    }
    
    @PostMapping("add")
    public String addAgent(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
        	System.out.println(result);
            return "agent/addAgent";
        }
        
        userService.saveUser(user,"AGENT",1);
        
        System.out.println(userService.toString());
       
    //    sendEmail("boulongnecorentin@gmail.com", true);
        
        return "redirect:list";
    }

    
    @GetMapping("delete/{id}")
    public String deleteAgent(@PathVariable("id") long id, Model model) {
    	
    	
    	//long id2 = 100L;
    	
        Agent agent = agentRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid agent Id:" + id));
        
        System.out.println("suite du programme...");
        
        agentRepository.delete(agent);
        
        /*model.addAttribute("providers", providerRepository.findAll());
        return "provider/listProviders";*/
        return "redirect:../list";
    }
    
    
    @GetMapping("edit/{id}")
    public String showAgentFormToUpdate(@PathVariable("id") long id, Model model) {
        Agent agent = agentRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid agent Id:" + id));
        
        model.addAttribute("agent", agent);
        
        return "agent/updateAgent";
    }


    
    @PostMapping("update")
    public String updateAgent(@Valid Agent agent, BindingResult result, Model model) {
    	
    	
    	agentRepository.save(agent);
    	return"redirect:list";
    	
    }
    
    
    public void sendEmail(String email, boolean state)
    {
 
    	SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        if(state == true)
        {
        msg.setSubject("Account Has Been Activated");
        msg.setText("Hello, Your account has been activated. "
        		+ 
        		"You can log in : http://127.0.0.1:8081/login"
        		+ " \n Best Regards!");
        }
        else
        {
        	msg.setSubject("Account Has Been disactivated");
            msg.setText("Hello, Your account has been disactivated.");
        }
        javaMailSender.send(msg);

    }

    }
    
