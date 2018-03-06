package edu.gzhu.its.system.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

/**
 * 
 * <p>
 * Title : Resource
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Company :
 * </p>
 * 
 * @author 丁国柱
 * @date 2017年11月12日 上午1:54:17
 */
@Entity
@Table(name = "sys_resource")
public class Resource {
	
	private int datapermission;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resource other = (Resource) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 10)
	private int id;
	
	//url对应的访问路径
	private String url;

	//资源名字
	private String name;
	
	//是否为菜单上的url
	@Column(columnDefinition = "bit(1) DEFAULT 0 comment '是否为菜单上的url'")
	private boolean isMenu;
	
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private Resource parent;*/
	
/*	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}*/

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public boolean isHasButton() {
		return hasButton;
	}

	public void setHasButton(boolean hasButton) {
		this.hasButton = hasButton;
	}

	//子菜单
	@Transient
	private List<Resource> children;

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "remark", length = 200)
	private String remark;// 备注

	@Column(name = "resourceName", length = 400)
	private String resourceName;// 资源名称

	@Column(name = "methodName", length = 400)
	private String methodName;// 资源所对应的方法名

	@Column(name = "methodPath", length = 1000)
	private String methodPath;// 资源所对应的包路径
	
	/**
	 * 是否有叶子节点,@Formula 执行getter方法时执行语句
	 */
	@Formula(value = "(select count(*) from sys_resource sr where sr.parentId = id)")
	private boolean hasChildren;
	
	/**
	 * 判断该资源下是否有权限操作按钮
	 */
	@Formula(value = "(select count(*) from sys_resource_button srb where srb.resource_id = id)")
	private boolean hasButton;
	
	@Transient
	private Set<ResourceButton> operates;
	
	public Set<ResourceButton> getOperates() {
		return operates;
	}

	public void setOperates(Set<ResourceButton> operates) {
		this.operates = operates;
	}

	// 删除标示
	@Column(columnDefinition = "bit(1) DEFAULT 0 comment '软删除标识'")
	private boolean delFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodPath() {
		return methodPath;
	}

	public void setMethodPath(String methodPath) {
		this.methodPath = methodPath;
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

	public boolean isMenu() {
		return isMenu;
	}

	public void setMenu(boolean isMenu) {
		this.isMenu = isMenu;
	}

	public int getDatapermission() {
		return datapermission;
	}

	public void setDatapermission(int datapermission) {
		this.datapermission = datapermission;
	}

}
