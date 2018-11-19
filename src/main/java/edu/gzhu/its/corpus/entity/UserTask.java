package edu.gzhu.its.corpus.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.gzhu.its.system.entity.User;
/**
 * 用户标注任务
 * @author dingguozhu
 *
 */
@Entity
@Table(name = "user_task")
public class UserTask {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "user_comment_id")
	private UserComment userComment;

	// 是否被标注
	@Column(name = "isAnnotationed", columnDefinition = "int(1) default 0")
	private boolean isAnnotationed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserComment getUserComment() {
		return userComment;
	}

	public void setUserComment(UserComment userComment) {
		this.userComment = userComment;
	}

	public boolean isAnnotationed() {
		return isAnnotationed;
	}

	public void setAnnotationed(boolean isAnnotationed) {
		this.isAnnotationed = isAnnotationed;
	}
	
}
