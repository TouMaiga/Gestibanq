package com.gk.gestibank.controllers;

import java.security.Principal;
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

import com.gk.gestibank.entities.Compte;
import com.gk.gestibank.entities.Demande;
import com.gk.gestibank.entities.Role;
import com.gk.gestibank.entities.User;
import com.gk.gestibank.repositories.CompteRepository;
import com.gk.gestibank.repositories.DemandeRepository;
import com.gk.gestibank.repositories.UserRepository;
import com.gk.gestibank.services.UserService;

@Controller
@RequestMapping("/demande/")
public class DemandeController {

	private final DemandeRepository demandeRepository;
	private final UserRepository userRepository;
	private final UserService userService;
	private final CompteRepository compteRepository;

	public DemandeController(DemandeRepository demandeRepository, UserRepository userRepository,
			UserService userService, CompteRepository compteRepository) {
		this.demandeRepository = demandeRepository;
		this.userRepository = userRepository;
		this.userService = userService;
		this.compteRepository = compteRepository;
	}

	@GetMapping("list")
	public String listDeamndes(Model model) {

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

	@GetMapping("/client/list")
	public String listDemandesClient(Model model, Principal principal) {

		String name = principal.getName();

		List<Demande> demandes = demandeRepository.findAll();
		
		List<Demande> demandeClients = new ArrayList<>();
		
		
		if (name != null) {
			User user = userRepository.findByEmail(name);
			for (Demande d : demandes) {
				Compte cptDmd = d.getCompte();
				if (cptDmd != null ) {
					if (cptDmd.getClient() != null && cptDmd.getClient().getId() == user.getId()) {
						demandeClients.add(d);
					}
				}
			}
		}

		model.addAttribute("demandes", demandeClients);

		return "demande/listDemandeClient";

	}

	@GetMapping("inscriptions")
	public String listInscriptionAdmin(Model model, Principal principal) {

		List<User> clients = userService.findInactiveUserByRole("CLIENT");

		for (User us : clients) {
			List<User> users = userService.findUsers("AGENT");
			for (User user : users) {
				if (user.getInscriptions().contains(us)) {
					us.getInscriptions().add(user);
				}
			}

		}

		model.addAttribute("clients", clients);

		List<User> agents = userService.findUsers("AGENT");

		model.addAttribute("agents", agents);

		return "demande/listDemandeAdmin";

	}

	@GetMapping("/agent/inscriptions")
	public String listMessages(Model model, Principal principal) {

		List<User> clients = new ArrayList<>();
		String name = principal.getName();

		if (name != null) {
			User user = userRepository.findByEmail(name);
			clients = user.getInscriptions();
		}

		model.addAttribute("clients", clients);

		return "demande/listDemandeAgent";

	}

	@GetMapping("add")
	public String addDemande(Model model, Principal principal) {
		Demande demande = new Demande();
		model.addAttribute("demande", demande);

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
		return "demande/addDemande";
	}

	@PostMapping("add")
	public String adddemande(@Valid Demande demande, BindingResult result, Model model, Principal principal,
			@RequestParam("compte") long compteId) {
		if (result.hasErrors()) {
			return "demande/addDemande";
		}

		Compte compte = compteRepository.findById(compteId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid compte id : " + compteId));

		demande.setEtat("Nouveau");
		demande.setCompte(compte);
		demandeRepository.save(demande);
		return "redirect:client/list";
	}

	@PostMapping("affect")
	public String addDemande(@RequestParam("clientEmail") String clientEmail,
			@RequestParam("agentEmail") String agentEmail) {
		User client = userRepository.findByEmail(clientEmail);
		User agent = userRepository.findByEmail(agentEmail);
		agent.getInscriptions().add(client);
		userRepository.save(agent);
		return "redirect:inscriptions";
	}

	@GetMapping("/{id}")
	public String validerDemande(@PathVariable("id") int id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		user.setActive(1);
		userRepository.save(user);
		return "redirect:agent/inscriptions";
	}

}
