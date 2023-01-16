package com.gk.gestibank.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity

public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "date")
	private Date date;

	@NotBlank(message = "Sujet is mandatory")
	@Column(name = "sujet")
	private String sujet;

	@NotBlank(message = "Auteur is mandatory")
	@Column(name = "auteur")
	private String auteur;

	@NotBlank(message = "Contenu is mandatory")
	@Column(name = "contenu")
	private String contenu;

	@Column(name = "reponse")
	private String reponse;

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Message() {
	}

	public Message(@NotBlank(message = "Sujet is mandatory") String sujet,
			@NotBlank(message = "Auteur is mandatory") String auteur,
			@NotBlank(message = "Reponse is mandatory") String reponse,
			@NotBlank(message = "Contenu is mandatory") String contenu) {
		super();
		this.sujet = sujet;
		this.auteur = auteur;
		this.reponse = reponse;
		this.contenu = contenu;
	}

}
