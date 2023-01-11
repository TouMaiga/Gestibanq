package com.gk.gestibank.entities;

import java.util.Date;

public class Compte {

	private int rib;
	private String description;
	private Date dateCreation;
	private int solde;

	public Compte(int rib, String description, Date dateCreation, int solde) {
		super();
		this.rib = rib;
		this.description = description;
		this.dateCreation = dateCreation;
		this.solde = solde;
	}

	public Compte() {

	}

	public int getRib() {
		return rib;
	}

	public void setRib(int rib) {
		this.rib = rib;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

}
