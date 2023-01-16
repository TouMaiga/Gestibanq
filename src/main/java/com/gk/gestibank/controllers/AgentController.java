package com.gk.gestibank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gk.gestibank.entities.Agent;
import com.gk.gestibank.repositories.AgentRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/agent/")

public class AgentController {
	
	private final AgentRepository agentRepository;
	
    @Autowired
    public AgentController(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    
    @GetMapping("list")
    //@ResponseBody
    public String listAgents(Model model) {
    	
    	List<Agent> lp = (List<Agent>)agentRepository.findAll();
    	long nbAgents =  agentRepository.count();
    	if(lp.size() == 0) {
    		
    		lp = null;
    	}
        model.addAttribute("agents",lp);
        model.addAttribute("nbAgents", nbAgents);
      
        return "agent/listAgents";
        
        //List<Provider> lp = (List<Provider>)providerRepository.findAll();
        //System.out.println(lp);
        
        //return "Nombre de fournisseur = " + lp.size();
    }
    
    @GetMapping("add")
    public String showAddAgentForm(Model model) {
    	Agent agent = new Agent();// object dont la valeur des attributs par defaut
    	model.addAttribute("agent", agent);
        return "agent/addAgent";
    }
    
    @PostMapping("add")
    public String addAgent(@Valid Agent agent, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	System.out.println(result);
            return "agent/addAgent";
        }
        /*LocalDate ld = LocalDate.now();
        Date d = new Date(System.currentTimeMillis());
        agent.setDate(d);*/
        agentRepository.save(agent);
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
}
