package com.gk.gestibank.controllers;

import java.security.Principal;
import java.util.ArrayList;
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
import com.gk.gestibank.entities.User;
import com.gk.gestibank.repositories.CompteRepository;
import com.gk.gestibank.repositories.UserRepository;

@Controller
@RequestMapping("/compte/")
public class CompteController {

	private final CompteRepository compteRepository;
	private final UserRepository userRepository;

	@Autowired
	public CompteController(CompteRepository compteRepository, UserRepository userRepository) {
		this.compteRepository = compteRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("list")
	public String listComptes(Model model) {

		List<Compte> lp = (List<Compte>) compteRepository.findAll();
		long nbComptes = compteRepository.count();
		if (lp.size() == 0) {

			lp = null;
		}
		model.addAttribute("comptes", lp);
		model.addAttribute("nbComptes", nbComptes);

		return "compte/listComptes";

	}

	@GetMapping("client/list")
	public String listComptesClient(Model model, Principal principal) {

		String name = principal.getName();

		List<Compte> comptes = (List<Compte>) compteRepository.findAll();

		List<Compte> comptesDuClient = new ArrayList<>();

		if (name != null) {
			User user = userRepository.findByEmail(name);
			for (Compte cpt : comptes) {
				User client = cpt.getClient();
				if (client != null && client.getId() == user.getId()) {
					comptesDuClient.add(cpt);
				}
			}
		}

		model.addAttribute("comptes", comptesDuClient);
		model.addAttribute("nbComptes", comptesDuClient.size());

		return "compte/listComptesClient";

	}

	@GetMapping("add")
	public String showAddCompteForm(Model model) {
		Compte compte = new Compte();// object dont la valeur des attributs par defaut
		model.addAttribute("compte", compte);
		return "compte/addCompte";
	}

	@PostMapping("add")
	public String addCompte(@Valid Compte compte, BindingResult result, Model model, Principal principal) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "compte/addCompte";
		}

		String name = principal.getName();

		if (name != null) {
			User user = userRepository.findByEmail(name);
			compte.setClient(user);
		}

		compteRepository.save(compte);

		return "redirect:list";
	}

	@GetMapping("delete/{id}")
	public String deleteCompte(@PathVariable("id") long id, Model model) {

		Compte compte = compteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid compte Id:" + id));

		System.out.println("suite du programme...");

		compteRepository.delete(compte);

		return "redirect:../list";
	}

	@GetMapping("edit/{id}")
	public String showCompteFormToUpdate(@PathVariable("id") long id, Model model) {
		Compte compte = compteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid compte Id:" + id));

		model.addAttribute("compte", compte);

		return "compte/updateCompte";
	}
	
	@GetMapping("virement")
	public String showVirementFrom(Model model) {

		

		return "compte/virement";

	}
	
	@PostMapping("update")
	public String updateCompte(@Valid Compte compte, BindingResult result, Model model) {

		compteRepository.save(compte);
		return "redirect:list";

	}

}
