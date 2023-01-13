    package com.gk.gestibank.entities;
    import java.sql.Date;

import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.validation.constraints.NotBlank;
	@Entity

public class Message {
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	    
		@Column(name = "date")
	    private Date date ;
		
	    @NotBlank(message = "FirstName is mandatory")
	    @Column(name = "firstname")
	    private String firstname;
	    
	    @NotBlank(message = "LastName is mandatory")
	    @Column(name = "lastname")
	    private String lastname;
	    
	    @NotBlank(message = "Email is mandatory")
	    @Column(name = "email")
	    private String email;
	    @NotBlank(message = "Sujet is mandatory")
	    @Column(name = "sujet")
	    private String sujet;
	    
	    @NotBlank(message = "Contenu is mandatory")
	    @Column(name = "contenu")
	    private String contenu;
	    
	    @Column(name = "reponse")
	    private String reponse;

		


		public Message() {
			super();
			this.id = id;
			this.date = date;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.sujet = sujet;
			this.contenu = contenu;
			this.reponse = reponse;
		}


		public String getSujet() {
			return sujet;
		}


		public void setSujet(String sujet) {
			this.sujet = sujet;
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

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getContenu() {
			return contenu;
		}

		public void setContenu(String contenu) {
			this.contenu = contenu;
		}

		public String getReponse() {
			return reponse;
		}

		public void setReponse(String reponse) {
			this.reponse = reponse;
		}
	    
	   

}
