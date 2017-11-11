package edu.gzhu.its.system.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
@Entity
@Table(name = "sys_role")
public class Role {
	@Id
    @GeneratedValue
    private Long id;
	
    private String name;
    
    @Lob   
    @Basic(fetch = FetchType.LAZY)   
    @Type(type="text")  
    @Column(name="description")  
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
