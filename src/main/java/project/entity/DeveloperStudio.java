package project.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "studios")
@AttributeOverride(name = "id", column = @Column(name = "`studio_id`"))
public class DeveloperStudio extends AbstractEntity{
	
	@Column(name = "name", unique = true)
	private String name;
	
	@Column(name = "developers")
	private int numberOfDevelopers;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "developerStudio", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private Set<Game> games;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "`publisher_id`")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Publisher publisher;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNumberOfDevelopers() {
		return numberOfDevelopers;
	}
	
	public void setNumberOfDevelopers(int numberOfDevelopers) {
		this.numberOfDevelopers = numberOfDevelopers;
	}
	
	public Set<Game> getGames() {
		return games;
	}
	
	public void setGames(Set<Game> games) {
		this.games = games;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}
	
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	@Override
	public String toString() {
		return "DeveloperStudio " + id + " [name=" + name + ", numberOfDevelopers=" + numberOfDevelopers + ", games=" + games
				+ ", publisher=" + publisher + "]";
	}	
}
