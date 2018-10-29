package edu.gzhu.its.humanism.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 汉语词汇
 * @author dinggz
 *
 */

@Entity
@Table(name = "cihui")
public class Vocabulary {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	/**
	 * 成语
	 */
	@Column(name = "idiom", length = 100)
	private String idiom;

	/**
	 * 拼音
	 */
	@Column(name = "spell", length = 100)
	private String spell;

	/**
	 * 解释
	 */
	@Column(name = "paraphrase")
	private String paraphrase;

	/**
	 * 出处
	 */
	@Column(name = "provenance",columnDefinition = "text comment '出处'")
	private String provenance;
	
	/**
	 * 例子
	 */
	@Column(name = "example")
	private String example;

	
	public String getIdiom() {
		return idiom;
	}

	public void setIdiom(String idiom) {
		this.idiom = idiom;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getParaphrase() {
		return paraphrase;
	}

	public void setParaphrase(String paraphrase) {
		this.paraphrase = paraphrase;
	}

	public String getProvenance() {
		return provenance;
	}

	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	/**
	 * 感情色彩(快乐/安心/等)
	 * @author : 丁国柱
	 * @date : 2014-10-18 下午7:29:07
	 */
	@Column(name = "emotional")
	private String emotional;
	
	/**
	 * 感情色彩类型(褒义/贬义/中性/兼有褒贬两性)
	 * @author : 丁国柱
	 * @date : 2014-10-18 下午7:29:07
	 */
	@Column(name = "emotionalType")
	private String emotionalType;

	public String getEmotional() {
		return emotional;
	}

	public void setEmotional(String emotional) {
		this.emotional = emotional;
	}
	
	@Column(name = "description",columnDefinition = "text comment '说明'")
	private String description;
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	//词汇
	@Column(name = "cihui")
	private String cihui;
	
	//同义词
	@Column(name = "tongyici")
	private String tongyici;
	
	//近义词
	@Column(name = "jinyici")
	private String jinyici;
	
	//反义词
	@Column(name = "fanyici")
	private String fanyici;
	
	public String getTongyici() {
		return tongyici;
	}

	public void setTongyici(String tongyici) {
		this.tongyici = tongyici;
	}

	public String getJinyici() {
		return jinyici;
	}

	public void setJinyici(String jinyici) {
		this.jinyici = jinyici;
	}

	public String getFanyici() {
		return fanyici;
	}

	public void setFanyici(String fanyici) {
		this.fanyici = fanyici;
	}

	//词汇类型(名词（noun），动词（verb），形容词（adj），副词（adv），网络词语（nw），成语（idiom），介词短语（prep）)
	@Column(name = "type")
	private String type;
	
	//情感强度(情感强度分为1,3,5,7,9五档，9表示强度最大，1为强度最小)
	@Column(name = "strength")
	private String strength; 
	
	//是否成語，1是，0不是
	@Column(name = "isIdiom")
	private String isIdiom;
	
	//褒义、贬义、中性
	@Column(name = "jixing")
	private String jixing;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmotionalType() {
		return emotionalType;
	}

	public void setEmotionalType(String emotionalType) {
		this.emotionalType = emotionalType;
	}

	public String getCihui() {
		return cihui;
	}

	public void setCihui(String cihui) {
		this.cihui = cihui;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getIsIdiom() {
		return isIdiom;
	}

	public void setIsIdiom(String isIdiom) {
		this.isIdiom = isIdiom;
	}

	public String getJixing() {
		return jixing;
	}

	public void setJixing(String jixing) {
		this.jixing = jixing;
	}
	
}
