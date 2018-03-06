package edu.gzhu.its.study.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.gzhu.its.system.entity.User;

/**
 * 点名的具体情况
 * <p>Title : RollCallInfo</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午2:34:30
 */
@Entity
@Table(name = "study_rollcallinfo")
public class RollCallInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	//被点名的学生
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	//1.上课 2.迟到  3.早退 4.请假  
	private int type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
