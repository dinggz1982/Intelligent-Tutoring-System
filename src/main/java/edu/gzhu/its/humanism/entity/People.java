package edu.gzhu.its.humanism.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 人物
 * @author : 丁国柱
 */
@Entity
@Table(name = "people")
public class People {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	//姓名
	@Column(name = "name")
	private String name;
	
	//描述维度
	@Column(name = "profileType")
	private String profileType;
	
	//子维度
	@Column(name = "subProfileType")
	private String subProfileType;
	
	//描述信息
	@Column(name = "profileDetail",columnDefinition="text")
	private String profileDetail;
	
	//人物总体描述
	@Column(name = "description",columnDefinition="text")
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileType() {
		return profileType;
	}

	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}

	public String getSubProfileType() {
		return subProfileType;
	}

	public void setSubProfileType(String subProfileType) {
		this.subProfileType = subProfileType;
	}

	public String getProfileDetail() {
		return profileDetail;
	}

	public void setProfileDetail(String profileDetail) {
		this.profileDetail = profileDetail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
