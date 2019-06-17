package edu.gzhu.its.experiment.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="word")
public class Word {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	private String word;
	
	@Column(name = "ifidf", columnDefinition = "float default 0")
	private float ifidf;
	
	@Column(name = "frequency", columnDefinition = "int(5) default 0")
	private int frequency;
	
	private String weight;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public float getIfidf() {
		return ifidf;
	}

	public void setIfidf(float ifidf) {
		this.ifidf = ifidf;
	}
	
	
	
	

}
