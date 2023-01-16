package com.gk.gestibank.entities;
	
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

	@Entity
	public class Actuality {
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	
	    @NotBlank(message = "Name is mandatory")
	    @Column(name = "name")
	    private String name;
	    
	    @NotBlank(message = "Contenu is mandatory")
	    @Column(name = "contenu", length = 2048)
	    private String contenu;
	    
	    private String logo;
	    

	    public Actuality() {}
	
	    public Actuality (String name, String contenu,String logo) {
	        this.name = name;
	        this.contenu = contenu;
	        this.logo = logo;
	       
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

		public String getLogo() {
			return logo;
		}

		public void setLogo(String logo) {
			this.logo = logo;
		}
	    
}

