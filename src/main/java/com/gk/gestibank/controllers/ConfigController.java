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

import com.gk.gestibank.entities.Config;
import com.gk.gestibank.repositories.ConfigRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/config/")
public class ConfigController {
	
	private final ConfigRepository configRepository;
	
	@Autowired
	public ConfigController(ConfigRepository configRepository)
	{
		this.configRepository = configRepository;
	}
	
	 @GetMapping("list")
	    //@ResponseBody
	    public String listConfigs(Model model) {
	    	
	    	List<Config> lp = (List<Config>)configRepository.findAll();
	    	if(lp.size() == 0) {
	    		
	    		lp = null;
	    	}
	        model.addAttribute("configs",lp);
	        
	       
	        
	        return "config/listConfigs";
	        
	        //List<Provider> lp = (List<Provider>)providerRepository.findAll();
	        //System.out.println(lp);
	        
	        //return "Nombre de fournisseur = " + lp.size();
	    }
	    
	    @GetMapping("add")
	    public String showAddConfigForm(Model model) {
	    	Config config = new Config();// object dont la valeur des attributs par defaut
	    	model.addAttribute("config", config);
	        return "config/addConfig";
	    }
	    
	    @PostMapping("add")
	    public String addConfig(@Valid Config config, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	        	System.out.println(result);
	            return "config/addConfig";
	        }
	        /*LocalDate ld = LocalDate.now();
	        Date d = new Date(System.currentTimeMillis());
	        config.setDate(d);*/
	        configRepository.save(config);
	        return "redirect:list";
	    }

	    
	    @GetMapping("delete/{id}")
	    public String deleteConfig(@PathVariable("id") long id, Model model) {
	    	
	    	
	    	//long id2 = 100L;
	    	
	        Config config = configRepository.findById(id)
	            .orElseThrow(()-> new IllegalArgumentException("Invalid config Id:" + id));
	        
	        System.out.println("suite du programme...");
	        
	        configRepository.delete(config);
	        
	        /*model.addAttribute("providers", providerRepository.findAll());
	        return "provider/listProviders";*/
	        return "redirect:../list";
	    }
	    
	    
	    @GetMapping("edit/{id}")
	    public String showConfigFormToUpdate(@PathVariable("id") long id, Model model) {
	        Config config = configRepository.findById(id)
	            .orElseThrow(()->new IllegalArgumentException("Invalid config Id:" + id));
	        
	        model.addAttribute("config", config);
	        
	        return "config/updateConfig";
	    }


	    
	    @PostMapping("update")
	    public String updateConfig(@Valid Config config, BindingResult result, Model model) {
	    	
	    	
	    	configRepository.save(config);
	    	return"redirect:list";
	    	
	    }

}
