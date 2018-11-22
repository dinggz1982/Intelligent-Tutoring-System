package edu.gzhu.its.corpus.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.gzhu.its.system.entity.User;

/**
 * 用户标注
 * 
 * @author dinggz
 *
 */
@Entity
@Table(name = "user_remark")
public class UserRemark {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentRelated == null) ? 0 : contentRelated.hashCode());
		result = prime * result + ((emotionRelated == null) ? 0 : emotionRelated.hashCode());
		result = prime * result + (isEffectiveComment ? 1231 : 1237);
		result = prime * result + ((otherRelated == null) ? 0 : otherRelated.hashCode());
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
		UserRemark other = (UserRemark) obj;
		if (contentRelated == null) {
			if (other.contentRelated != null)
				return false;
		} else if (!contentRelated.equals(other.contentRelated))
			return false;
		if (emotionRelated == null) {
			if (other.emotionRelated != null)
				return false;
		} else if (!emotionRelated.equals(other.emotionRelated))
			return false;
		if (isEffectiveComment != other.isEffectiveComment)
			return false;
		if (otherRelated == null) {
			if (other.otherRelated != null)
				return false;
		} else if (!otherRelated.equals(other.otherRelated))
			return false;
		return true;
	}

	// 标注的用户
	@ManyToOne
	@JoinColumn(name = "userComment_id")
	private UserComment userComment;

	public UserComment getUserComment() {
		return userComment;
	}

	public void setUserComment(UserComment userComment) {
		this.userComment = userComment;
	}

	// 标注的用户
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "createTime")
	private Date createTime;

	// 内容相关
	@Column(name = "contentRelated")
	private String contentRelated;

	// 情感相关
	@Column(name = "emotionRelated")
	private String emotionRelated;

	// 其他
	@Column(name = "otherRelated")
	private String otherRelated;

	@Column(name = "isEffectiveComment", columnDefinition = "int(1) default 0")
	private boolean isEffectiveComment;

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

	public String getContentRelated() {
		return contentRelated;
	}

	public void setContentRelated(String contentRelated) {
		this.contentRelated = contentRelated;
	}

	public String getEmotionRelated() {
		return emotionRelated;
	}

	public void setEmotionRelated(String emotionRelated) {
		this.emotionRelated = emotionRelated;
	}

	public String getOtherRelated() {
		return otherRelated;
	}

	public void setOtherRelated(String otherRelated) {
		this.otherRelated = otherRelated;
	}

	public boolean isEffectiveComment() {
		return isEffectiveComment;
	}

	public void setEffectiveComment(boolean isEffectiveComment) {
		this.isEffectiveComment = isEffectiveComment;
	}

}
