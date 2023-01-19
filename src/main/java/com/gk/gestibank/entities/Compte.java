package com.gk.gestibank.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@NotNull(message = "solde is mandatory")
	@Column(name = "solde")
	private double solde;

	@NotNull(message = "numero_compte is mandatory")
	@Column(name = "numero_compte")
	private int numeroCompte;

	@NotBlank(message = "type is mandatory")
	@Column(name = "type")
	private String type;
	
	@Column(name = "active")
	private Integer active = 0;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User client;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}


	public int getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(int numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

}
