package project.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "games")
public class Game extends AbstractEntity{
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "score")
	private double score;
	
	@Column(name = "sales")
	private int numberOfSales;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "`studio_id`", nullable = false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private DeveloperStudio developerStudio;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public int getNumberOfSales() {
		return numberOfSales;
	}
	
	public void setNumberOfSales(int numberOfSales) {
		this.numberOfSales = numberOfSales;
	}
	
	public DeveloperStudio getDeveloperStudio() {
		return developerStudio;
	}
	
	public void setDeveloperStudio(DeveloperStudio developerStudio) {
		this.developerStudio = developerStudio;
	}
	
	@Override
	public String toString() {
		return "Game " + id + " [name=" + name + ", score=" + score + ", numberOfSales=" + numberOfSales + ", developerStudios="
				+ developerStudio + "]";
	}
}
