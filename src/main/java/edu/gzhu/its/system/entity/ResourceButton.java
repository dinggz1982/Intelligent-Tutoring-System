package edu.gzhu.its.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 资源按钮操作
 * <p>Title : ResourceOperate</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年1月31日 上午12:57:00
 */
@Entity
@Table(name = "sys_resource_button")
public class ResourceButton {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	//操作名
	private String name;
	
	//操作对应的URL
	private String url;
	
	//此处可以改进，新建一个枚举类来标识各种按钮权限
	//操作类型(增、删、改、查、导出、导出、流程查看、流程跟踪.....)
	private int type;
	
	//对应的资源
	@ManyToOne
	@JoinColumn(name="resource_id")
	private Resource resource;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
