package edu.gzhu.its.humanism.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 汉字拼音
 * 
 * @author : 丁国柱
 * @date : 2014-6-23 上午10:51:58
 */
@Entity
@Table(name = "spell")
public class Spell {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	// 一个汉字有多个读音
	@ManyToOne
	@JoinColumn(name = "hanziId", referencedColumnName = "id")
	// 外键为hanziId，与hanzi中的id关联
	private ChineseCharacters hanzi;

	// 读音
	@Column(name = "spell")
	private String spell;
	
	//无声调的读音
	@Column(name = "spellNoTone")
	private String spellNoTone;
	
	
	public String getSpellNoTone() {
		return spellNoTone;
	}

	public void setSpellNoTone(String spellNoTone) {
		this.spellNoTone = spellNoTone;
	}

	//关联信息
	@Column(name = "linkedinfo", columnDefinition="text")
	private String linkedinfo;

	public String getLinkedinfo() {
		return linkedinfo;
	}

	public void setLinkedinfo(String linkedinfo) {
		this.linkedinfo = linkedinfo;
	}

	// 读音存放位置
	@Column(name = "spellPath")
	private String spellPath;
	
	//声母
	@Column(name = "shengmu")
	private String shengmu;
	
	//韵母
	@Column(name = "yunmu")
	private String yunmu;
	
	//整体认读音节
	@Column(name = "zhengtirendu")
	private String zhengtirendu;
	
	public String getShengmu() {
		return shengmu;
	}

	public void setShengmu(String shengmu) {
		this.shengmu = shengmu;
	}

	public String getYunmu() {
		return yunmu;
	}

	public void setYunmu(String yunmu) {
		this.yunmu = yunmu;
	}

	public String getZhengtirendu() {
		return zhengtirendu;
	}

	public void setZhengtirendu(String zhengtirendu) {
		this.zhengtirendu = zhengtirendu;
	}

	// 声调
	@Column(name = "tone")
	private int tone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChineseCharacters getHanzi() {
		return hanzi;
	}

	public void setHanzi(ChineseCharacters hanzi) {
		this.hanzi = hanzi;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getSpellPath() {
		return spellPath;
	}

	public void setSpellPath(String spellPath) {
		this.spellPath = spellPath;
	}

	public int getTone() {
		return tone;
	}

	public void setTone(int tone) {
		this.tone = tone;
	}

}
