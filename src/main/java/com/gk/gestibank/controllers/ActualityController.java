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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gk.gestibank.entities.Actuality;
import com.gk.gestibank.repositories.ActualityRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/actuality/")

public class ActualityController { 

	private final ActualityRepository actualityRepository;

	@Autowired
	public ActualityController(ActualityRepository actualityRepository) {
		this.actualityRepository = actualityRepository;
	}

	  @GetMapping("list")
	    //@ResponseBody
	    public String listActualities(Model model) {
	    	
	    	List<Actuality> lp = (List<Actuality>)actualityRepository.findAll();
	    	if(lp.size() == 0) {
	    		
	    		lp = null;
	    	}
	        model.addAttribute("actualities",lp);
	        
	       
	        
	        return "actuality/listActuality";
	        
	        //List<Provider> lp = (List<Provider>)providerRepository.findAll();
	        //System.out.println(lp);
	        
	        //return "Nombre de fournisseur = " + lp.size();
	    }
		  @GetMapping("add") 
		  public String showAddActualityForm(Model model) {
		  Actuality actuality = new Actuality(); 
		  // object dont la valeur des attributs par defaut
		  model.addAttribute("actuality", actuality); 
		  return "actuality/addActuality"; }
		 
	 

	@PostMapping("add")
	public String addActuality(@Valid Actuality actuality, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "actuality/addActuality";
		}
		actualityRepository.save(actuality);
		return "redirect:list";
	} 

	
	  @GetMapping("delete/{id}") 
	  public String deleteActuality(@PathVariable("id")
	  long id, Model model) {
	  
	  
	  //long id2 = 100L;
	  
	  Actuality actuality = actualityRepository.findById(id) .orElseThrow(()-> new
	  IllegalArgumentException("Invalid actuality Id:" + id));
	  
	  System.out.println("suite du programme...");
	  
	  actualityRepository.delete(actuality);
	  
	  /* model.addAttribute("providers", actualityRepository.findAll()); 
	  return"actuality/listActuality"; */
	  return "redirect:../list";
	  }
	  
	  
	  @GetMapping("edit/{id}")
	  public String showActualityFormToUpdate(@PathVariable("id") long id, Model model) {
	  Actuality actuality = actualityRepository.findById(id) .orElseThrow(()->new
	  IllegalArgumentException("Invalid actuality Id:" + id));
	  
	  model.addAttribute("actuality", actuality);
	  
	  return "actuality/updateActuality"; 
	  }
	  
	  @PostMapping("update") 
	  public String updateActuality(@Valid Actuality actuality, BindingResult result, Model model) {
	  
	  actualityRepository.save(actuality); 
	  return"redirect:list";
	  
	  }
	 
}
