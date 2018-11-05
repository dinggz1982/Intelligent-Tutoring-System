package edu.gzhu.its.humanism.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 汉字信息本体
 * @author : 丁国柱
 */

@Entity
@Table(name = "characters")
public class ChineseCharacters {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	// 汉字
	@Column(name = "hanzi")
	private String hanzi;

	// 部首
	@Column(name = "radical")
	private String radical;

	// 拼音
	@OneToMany(mappedBy = "hanzi", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Spell> spells;

	// 笔顺
	@Column(name = "stroke")
	private String stroke;

	// 笔顺编号
	@Column(name = "strokeNo")
	private String strokeNo;

	// 笔画数
	@Column(name = "strokeNum")
	private int strokeNum;

	// 解释
	@Column(name = "analysis", length = 4000)
	private String analysis;

	// 详细解释
	@Column(name = "detailExplain", length = 4000)
	private String detailExplain;

	// 动画演示
	@Column(name = "demonstration")
	private String demonstration;

	// 同音字
	@Column(name = "tongyinzi")
	private String tongyinzi;

	// 同义字
	@Column(name = "tongyizi")
	private String tongyizi;

	// 反义字
	@Column(name = "fanyizi")
	private String fanyizi;
	
	//近义字
	@Column(name = "jinyizi")
	private String jinyizi;
	
	// 形似字
	@Column(name = "xingsizi")
	private String xingsizi;
	
	//显示的关联信息
	@Column(name = "linkedinfo",columnDefinition="text")
	private String linkedinfo;

	// 五笔输入法
	@Column(name = "wubi")
	private String wubi;
	
	//组词
	@Column(name = "zuci",columnDefinition="text")
	private String zuci;

	public String getZuci() {
		return zuci;
	}

	public void setZuci(String zuci) {
		this.zuci = zuci;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHanzi() {
		return hanzi;
	}

	public void setHanzi(String hanzi) {
		this.hanzi = hanzi;
	}

	public String getRadical() {
		return radical;
	}

	public void setRadical(String radical) {
		this.radical = radical;
	}

	public String getStroke() {
		return stroke;
	}

	public void setStroke(String stroke) {
		this.stroke = stroke;
	}

	public String getStrokeNo() {
		return strokeNo;
	}

	public void setStrokeNo(String strokeNo) {
		this.strokeNo = strokeNo;
	}

	public Set<Spell> getSpells() {
		return spells;
	}

	public void setSpells(Set<Spell> spells) {
		this.spells = spells;
	}

	public int getStrokeNum() {
		return strokeNum;
	}

	public void setStrokeNum(int strokeNum) {
		this.strokeNum = strokeNum;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getDetailExplain() {
		return detailExplain;
	}

	public void setDetailExplain(String detailExplain) {
		this.detailExplain = detailExplain;
	}

	public String getDemonstration() {
		return demonstration;
	}

	public void setDemonstration(String demonstration) {
		this.demonstration = demonstration;
	}

	public String getWubi() {
		return wubi;
	}

	public void setWubi(String wubi) {
		this.wubi = wubi;
	}

	public String getTongyinzi() {
		return tongyinzi;
	}

	public void setTongyinzi(String tongyinzi) {
		this.tongyinzi = tongyinzi;
	}

	public String getXingsizi() {
		return xingsizi;
	}

	public void setXingsizi(String xingsizi) {
		this.xingsizi = xingsizi;
	}

	public String getTongyizi() {
		return tongyizi;
	}

	public void setTongyizi(String tongyizi) {
		this.tongyizi = tongyizi;
	}

	public String getFanyizi() {
		return fanyizi;
	}

	public void setFanyizi(String fanyizi) {
		this.fanyizi = fanyizi;
	}

	public String getJinyizi() {
		return jinyizi;
	}

	public void setJinyizi(String jinyizi) {
		this.jinyizi = jinyizi;
	}

	public String getLinkedinfo() {
		return linkedinfo;
	}

	public void setLinkedinfo(String linkedinfo) {
		this.linkedinfo = linkedinfo;
	}

}
