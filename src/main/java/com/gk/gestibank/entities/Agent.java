package com.gk.gestibank.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Agent {
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	
	    @NotBlank(message = "Name is mandatory")
	    @Column(name = "nom")
	    private String nom;
	    
	    @NotBlank(message = "Name is mandatory")
	    @Column(name = "prenom")
	    private String prenom;
	    
	    @NotBlank(message = "Address is mandatory")
	    @Column(name = "address")
	    private String address;
	    
	    @NotBlank(message = "Email is mandatory")
	    @Column(name = "email")
	    private String email;
	    
	    @NotBlank(message = "Phone is mandatory")
	    @Column(name = "telephone")
	    private String telephone;
	    
	    /*@NotBlank(message = "Date is mandatory")
	    @Column(name = "date")
	    private Date date;*/
	  	
	    public Agent() {}
	
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

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		/*public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			
			
			
		}*/

		public Agent(String nom, String prenom,String address, String email, String telephone) {	
	        this.nom = nom;
	        this.prenom = prenom;
	        this.address = address;
	        this.email = email;
	        this.telephone = telephone;
	        
	    }
	
	    public void setId(long id) {
	        this.id = id;
	    }
	
	    public long getId() {
	        return id;
	    }
	
	    public void setName(String nom) {
	        this.nom = nom;
	    }
	    public String getName() {
	        return nom;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public String getEmail() {
	        return email;
	    }
	    public void setAddress(String address) {
	        this.address = address;
	    }
	
	    public String getAddress() {
	        return address;
	    }
}

