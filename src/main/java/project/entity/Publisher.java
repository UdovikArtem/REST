package project.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "publishers")
@AttributeOverride(name = "id", column = @Column(name = "`publisher_id`"))
public class Publisher extends AbstractEntity{
	
	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "main_office")
	private String countryOfMainOffice;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "publisher", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private Set<DeveloperStudio> developerStudios;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCountryOfMainOffice() {
		return countryOfMainOffice;
	}
	
	public void setCountryOfMainOffice(String countryOfMainOffice) {
		this.countryOfMainOffice = countryOfMainOffice;
	}
	
	public Set<DeveloperStudio> getDeveloperStudios() {
		return developerStudios;
	}
	
	public void setDeveloperStudios(Set<DeveloperStudio> developerStudios) {
		this.developerStudios = developerStudios;
	}
	
	@Override
	public String toString() {
		return "Publisher " + id + " [name=" + name + ", countryOfMainOffice=" + countryOfMainOffice + ", developerStudios="
				+ developerStudios + "]";
	}
}
