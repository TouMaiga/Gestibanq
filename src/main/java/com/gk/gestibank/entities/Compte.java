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
	
	@NotBlank(message = "solde is mandatory")
	@Column(name = "solde")
	private double solde;
	
	@NotBlank(message = "numero_compte is mandatory")
	@Column(name = "numero_compte")
	private int numero_compte;
	
	@NotBlank(message = "type is mandatory")
	@Column(name = "type")
	private String type;

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

	public int getNumero_compte() {
		return numero_compte;
	}

	public void setNumero_compte(int numero_compte) {
		this.numero_compte = numero_compte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
