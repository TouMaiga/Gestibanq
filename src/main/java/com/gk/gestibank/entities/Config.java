package com.gk.gestibank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Config {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@NotBlank(message = "about is mandatory")
	@Column(name = "about")
	private String about;
	
	//@NotBlank(message = "logo is mandatory")
	@Column(name = "logo")
	private String logo;
	
	@NotBlank(message = "Mail is mandatory")
	@Column(name = "mail")
	private String mail;
	
	@NotBlank(message = "adresse is mandatory")
	@Column(name = "adresse")
	private String adresse;
	
	@NotBlank(message = "téléphone is mandatory")
	@Column(name = "telephone")
	private String telephone;
	
	//@NotBlank(message = "isActive is mandatory")
	@Column(name = "active")
	private Boolean isActive;

	
	public Config() {}
	
	public Config(String about,String logo, String mail, String adresse, String telephone, Boolean isActive) {
		super();
		this.about = about;
		this.logo = logo;
		this.mail = mail;
		this.adresse = adresse;
		this.telephone = telephone;
		this.isActive = isActive;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
}
