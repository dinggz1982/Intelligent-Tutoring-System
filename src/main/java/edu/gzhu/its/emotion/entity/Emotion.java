package edu.gzhu.its.emotion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "its_emotion")
public class Emotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	//词汇
	private String word;
	
	//词性种类
	private String characterType;
	
	//词义数
	private int semantics;
	
	//词义序号
	private int semanticsNumber;
	
	//情感类型
	private String emotionType;
	
	//强度
	private int strong;
	
	//极性
	private int polarity;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getCharacterType() {
		return characterType;
	}

	public void setCharacterType(String characterType) {
		this.characterType = characterType;
	}

	public int getSemantics() {
		return semantics;
	}

	public void setSemantics(int semantics) {
		this.semantics = semantics;
	}

	public int getSemanticsNumber() {
		return semanticsNumber;
	}

	public void setSemanticsNumber(int semanticsNumber) {
		this.semanticsNumber = semanticsNumber;
	}

	public String getEmotionType() {
		return emotionType;
	}

	public void setEmotionType(String emotionType) {
		this.emotionType = emotionType;
	}

	public int getStrong() {
		return strong;
	}

	public void setStrong(int strong) {
		this.strong = strong;
	}

	public int getPolarity() {
		return polarity;
	}

	public void setPolarity(int polarity) {
		this.polarity = polarity;
	}

	
	
	

}
