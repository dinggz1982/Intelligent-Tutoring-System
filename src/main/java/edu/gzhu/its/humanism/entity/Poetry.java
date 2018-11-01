package edu.gzhu.its.humanism.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 中国诗词实体类
 * @author dinggz
 */

@Entity
@Table(name = "poetry")
public class Poetry {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "author", length = 50)
	private String author;
	
	@Column(name = "title", length = 100)
	private String title;
	
	//朝代
	@Column(name = "dynasty", length = 100)
	private String dynasty;
	
	@Column(name = "authorId",nullable=true)
	private Long authorId;
	
	//来源Id
	@Column(name = "sourceId")
	private int sourceId;
	
	//翻译连接Id
	@Column(name = "translationId",columnDefinition="text")
	private String translationId;
	
	public String getTranslationId() {
		return translationId;
	}
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "discipline")
//	private Set<PoetryAnalyze> poetryAnalyzes =  new HashSet<PoetryAnalyze>(0);

	public void setTranslationId(String translationId) {
		this.translationId = translationId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getDynasty() {
		return dynasty;
	}

	public void setDynasty(String dynasty) {
		this.dynasty = dynasty;
	}

	@Column(name = "content", columnDefinition="text")
	private String content;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	@Column(name = "translation", columnDefinition="text")
	private String translation;

}
