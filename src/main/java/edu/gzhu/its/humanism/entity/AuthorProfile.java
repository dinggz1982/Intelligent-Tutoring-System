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

@Entity
@Table(name = "poetry_profile")
public class AuthorProfile {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "profileName", length = 100)
	private String profileName;

	
	@Column(name = "profileDetail", length = 10000)
	private String profileDetail;
	
	/**
	 * 多个资料对应一个作者
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "authorId", nullable = false)
	private PoetryAuthor poetryAuthor;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public PoetryAuthor getPoetryAuthor() {
		return poetryAuthor;
	}


	public void setPoetryAuthor(PoetryAuthor poetryAuthor) {
		this.poetryAuthor = poetryAuthor;
	}


	public String getProfileName() {
		return profileName;
	}


	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}


	public String getProfileDetail() {
		return profileDetail;
	}


	public void setProfileDetail(String profileDetail) {
		this.profileDetail = profileDetail;
	}
	
}
