package edu.gzhu.its.system.entity;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import edu.gzhu.its.profile.entity.ClassInfo;
import edu.gzhu.its.profile.entity.Subject;

/**
 * 用户类
 * <p>
 * Title : User
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Company :
 * </p>
 * 
 * @author 丁国柱
 * @date 2017年12月27日 下午6:09:13
 */
@Entity(name = "sys_user")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private Set<Role> roles;

	// 对应的资源
	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassInfo classInfo;

	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public User() {
	}

	@Column(name = "username", unique = true, columnDefinition = ("varchar(255) comment '用户名'"))
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "nickname")
	private String nickName;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(columnDefinition = "varchar(255) comment '民族'")
	private String ethnicity;
	
	@Column(columnDefinition = "varchar(255) comment '学号'")
	private String xuehao;
	
	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getXuehao() {
		return xuehao;
	}

	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
	}

	@Column(name = "sex")
	private String sex;
	
	@Column(name = "img")
	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User(String userName, String password, Collection<GrantedAuthority> grantedAuths) {
		this.username = userName;
		this.setGrantedAuths(grantedAuths);
		this.password = password;
	}

	// URL权限
	@Transient
	private Collection<GrantedAuthority> grantedAuths;

	public Collection<GrantedAuthority> getGrantedAuths() {
		return grantedAuths;
	}

	public void setGrantedAuths(Collection<GrantedAuthority> grantedAuths) {
		this.grantedAuths = grantedAuths;
	}

}
