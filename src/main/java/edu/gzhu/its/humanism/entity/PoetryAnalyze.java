package edu.gzhu.its.humanism.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 中国诗词赏析实体类
 * @author dinggz
 */

@Entity
@Table(name = "poetry_analyze")
public class PoetryAnalyze {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	
	/**
	 * 多个赏析对应诗词
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "poetryId", nullable = false)
	private Poetry poetry;
	
	//分析方向
	@Column(name = "analyzeName",nullable = false)
	private String analyzeName;
	
	@Column(name = "analyzeDetail", length = 10000)
	private String analyzeDetail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Poetry getPoetry() {
		return poetry;
	}

	public void setPoetry(Poetry poetry) {
		this.poetry = poetry;
	}

	public String getAnalyzeName() {
		return analyzeName;
	}

	public void setAnalyzeName(String analyzeName) {
		this.analyzeName = analyzeName;
	}

	public String getAnalyzeDetail() {
		return analyzeDetail;
	}

	public void setAnalyzeDetail(String analyzeDetail) {
		this.analyzeDetail = analyzeDetail;
	}
	
	
}
