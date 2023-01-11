package com.gk.gestibank.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="service")
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "titre")
	private String titre;
	@Column(name = "type")
	private String type;
	@Column(name = "description")
	private String description;

	// private Agent agent; // un service est fait par un agent

	// private Compte compte; // un service est lié à un compte

	public Service() {

	}

	public Service(Integer id, String titre, String type, String description) {
		super();
		this.id = id;
		this.titre = titre;
		this.type = type;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
