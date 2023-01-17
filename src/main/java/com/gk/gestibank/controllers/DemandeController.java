package com.gk.gestibank.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gk.gestibank.entities.Demande;
import com.gk.gestibank.entities.Role;
import com.gk.gestibank.entities.User;
import com.gk.gestibank.repositories.DemandeRepository;
import com.gk.gestibank.repositories.UserRepository;

@Controller
@RequestMapping("/demande/")
public class DemandeController {

	private final DemandeRepository demandeRepository;
	private final UserRepository userRepository;

	public DemandeController(DemandeRepository demandeRepository, UserRepository userRepository) {
		this.demandeRepository = demandeRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("list")
	public String listMessages(Model model) {

		List<Demande> demandes = demandeRepository.findAll();

		model.addAttribute("demandes", demandes);

		List<User> agents = new ArrayList<>();

		List<User> users = (List<User>) userRepository.findAll();

		for (User user : users) {
			Set<Role> userRoles = user.getRoles();
			Object roles[] = userRoles.toArray();
			Role role = (Role) roles[0];
			String userRole = role.getRole();

			if (userRole.equals("AGENT"))
				agents.add(user);

		}
		model.addAttribute("agents", agents);

		return "demande/listDemande";

	}

	@GetMapping("add")
	public String addDemande(Model model) {
		Demande demande = new Demande();
		model.addAttribute("demande", demande);
		return "demande/addDemande";
	}

	@PostMapping("add")
	public String adddemande(@Valid Demande demande, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "demande/addDemande";
		}

		demandeRepository.save(demande);
		return "redirect:list";
	}

	/*
	 * @GetMapping("affect") public String addDemande(@PathVariable("id") int
	 * id, @PathVariable("userId") int userId) { Demande demande =
	 * demandeRepository.findById(id) .orElseThrow(() -> new
	 * IllegalArgumentException("Invalid deamnde Id:" + id)); User user =
	 * userRepository.findById(userId) .orElseThrow(() -> new
	 * IllegalArgumentException("Invalid user Id:" + id));
	 * 
	 * demande.getUsers().add(user); demandeRepository.save(demande); return
	 * "demande/listDemande"; }
	 */

}
