package edu.gzhu.its.corpus.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户评论
 * 
 * @author dinggz
 *
 */
@Entity
@Table(name = "user_comment")
public class UserComment {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	// 课程类型
	@Column(name = "courseType")
	private String courseType;

	// 汉字
	@Column(name = "content", columnDefinition = "text")
	private String content;

	// 课程
	@Column(name = "course")
	private String course;

	@Column(name = "createTime")
	private Date createTime;

	@Column(name = "against", columnDefinition = "int(2) default 0")
	private int against;

	@Column(name = "vote", columnDefinition = "int(2) default 0")
	private int vote;

	@Column(name = "favCount", columnDefinition = "int(2) default 0")
	private int favCount;

	// 是否被标注
	@Column(name = "isAnnotationed", columnDefinition = "int(1) default 0")
	private boolean isAnnotationed;

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
	
	public boolean isEffectiveComment() {
		return isEffectiveComment;
	}

	public void setEffectiveComment(boolean isEffectiveComment) {
		this.isEffectiveComment = isEffectiveComment;
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

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public boolean isAnnotationed() {
		return isAnnotationed;
	}

	public void setAnnotationed(boolean isAnnotationed) {
		this.isAnnotationed = isAnnotationed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getAgainst() {
		return against;
	}

	public void setAgainst(int against) {
		this.against = against;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public int getFavCount() {
		return favCount;
	}

	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}

}
