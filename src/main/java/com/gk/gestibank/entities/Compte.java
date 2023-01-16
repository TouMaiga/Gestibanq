package com.gk.gestibank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Compte {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank(message = "Nom is mandatory")
	@Column(name = "nom")
	private String nom;
	
	@NotBlank(message = "Prenom is mandatory")
	@Column(name = "prenom")
	private String prenom;
	
	@NotBlank(message = "email is mandatory")
	@Column(name = "email")
	private String email;
	

	
}
