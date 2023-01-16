package com.gk.gestibank.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gk.gestibank.entities.Compte;
import com.gk.gestibank.repositories.CompteRepository;

@Controller
@RequestMapping("/compte/")
public class CompteController {

	private final CompteRepository compteRepository;
	
	@Autowired
	public CompteController(CompteRepository compteRepository)
	{
			this.compteRepository = compteRepository;
	}
	
	@GetMapping("list")
    public String listComptes(Model model) {
    	
    	List<Compte> lp = (List<Compte>)compteRepository.findAll();
    	long nbComptes =  compteRepository.count();
    	if(lp.size() == 0) {
    		
    		lp = null;
    	}
        model.addAttribute("compte",lp);
        model.addAttribute("nbComptes", nbComptes);
      
        return "compte/listComptes";
        
    }
	
	@GetMapping("add")
    public String showAddCompteForm(Model model) {
    	Compte compte = new Compte();// object dont la valeur des attributs par defaut
    	model.addAttribute("agent", compte);
        return "compte/addCompte";
    }
	
	@PostMapping("add")
    public String addCompte(@Valid Compte compte, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	System.out.println(result);
            return "compte/addComptet";
        }
        
       compteRepository.save(compte);
        
        return "redirect:list";
    }
	
	@GetMapping("delete/{id}")
    public String deleteCompte(@PathVariable("id") long id, Model model) {
    	
    	
        Compte compte = compteRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid compte Id:" + id));
        
        System.out.println("suite du programme...");
        
        compteRepository.delete(compte);
        
        return "redirect:../list";
    }
	
	@GetMapping("edit/{id}")
    public String showCompteFormToUpdate(@PathVariable("id") long id, Model model) {
        Compte compte = compteRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid compte Id:" + id));
        
        model.addAttribute("compte", compte);
        
        return "compte/updateCompte";
    }
	
	@PostMapping("update")
    public String updateAgent(@Valid Compte compte, BindingResult result, Model model) {
    	
    	compteRepository.save(compte);
    	return"redirect:list";
    	
    }
	
}
