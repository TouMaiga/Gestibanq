	package com.gk.gestibank.entities;
	import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.validation.constraints.NotBlank;
	@Entity
	public class Actuality {
			@Id
		    @GeneratedValue(strategy = GenerationType.AUTO)
		    private long id;
		
		    @NotBlank(message = "Name is mandatory")
		    @Column(name = "name")
		    private String name;
		    
		    @NotBlank(message = "Contenu is mandatory")
		    @Column(name = "contenu")
		    private String contenu;
		    
	
		    public Actuality() {}
		
		    public Actuality (String name, String contenu) {
		        this.name = name;
		        this.contenu = contenu;
		       
		    }

			public long getId() {
				return id;
			}

			public void setId(long id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getContenu() {
				return contenu;
			}

			public void setContenu(String contenu) {
				this.contenu = contenu;
			}

			
		   
		    
		    
	}
