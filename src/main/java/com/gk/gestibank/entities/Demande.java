package com.gk.gestibank.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Demande {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String sujet;
	private String type;
	private String contenu;
	private String date;
	private String etat;
	private Integer active = 0;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Compte compte;

	public Demande() {
	}

	public Demande(int id, String sujet, String type, String contenu, String date, String etat, Compte compte) {
		this.id = id;
		this.sujet = sujet;
		this.type = type;
		this.contenu = contenu;
		this.date = date;
		this.etat = etat;
		this.compte = compte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
