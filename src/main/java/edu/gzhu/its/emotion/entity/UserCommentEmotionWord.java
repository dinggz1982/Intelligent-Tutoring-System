package edu.gzhu.its.emotion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**   
* Title: its 
* Description:     
* Copyright: Copyright (c) 2018 
* Company:广州大学-教育技术 
* Makedate:2018年11月27日 下午3:04:38 
* @author:丁国柱
* @version 1.0
* @since 1.0 
**/


@Entity
@Table(name = "its_user_comment_emotion")
public class UserCommentEmotionWord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "word")
	private String word;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getUserCommentId() {
		return userCommentId;
	}

	public void setUserCommentId(String userCommentId) {
		this.userCommentId = userCommentId;
	}

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	@Column(name = "userCommentId",columnDefinition = "text comment '用户评论的id字符串，用逗号隔开'")
	private String userCommentId;
	
	@Column(name = "wordCount")
	private int wordCount;
	
	

}
