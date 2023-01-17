package com.gk.gestibank.entities;

import java.util.HashSet;
import java.util.Set;

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
	private String firstNameCustomer;
	private String lastNameCustomer;
	private String email;
	private String sujet;
	private String contenu;
	private String date;
	private String etat;
	/*
	 * @ManyToOne(targetEntity = User.class, cascade = CascadeType.PERSIST) private
	 * Set<User> users = new HashSet<>();
	 */

	public Demande(int id, String firstNameCustomer, String lastNameCustomer, String email, String sujet, String contenu, String date,
			String etat) {
		this.id = id;
		this.firstNameCustomer = firstNameCustomer;
		this.lastNameCustomer = lastNameCustomer;
		this.email = email;
		this.sujet = sujet;
		this.contenu = contenu;
		this.date = date;
		this.etat = etat;
	}

	public Demande() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstNameCustomer() {
		return firstNameCustomer;
	}

	public void setFirstNameCustomer(String firstNameCustomer) {
		this.firstNameCustomer = firstNameCustomer;
	}

	public String getLastNameCustomer() {
		return lastNameCustomer;
	}

	public void setLastNameCustomer(String lastNameCustomer) {
		this.lastNameCustomer = lastNameCustomer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
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

	/*
	 * public Set<User> getUsers() { return users; }
	 * 
	 * public void setUsers(Set<User> users) { this.users = users; }
	 */

}
