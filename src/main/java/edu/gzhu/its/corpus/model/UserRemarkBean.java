package edu.gzhu.its.corpus.model;

import edu.gzhu.its.system.entity.User;

public class UserRemarkBean {
	
	private User user;
	
	private long hasRemarkCount;
	
	private long notRemarkCount;

	public long getHasRemarkCount() {
		return hasRemarkCount;
	}

	public void setHasRemarkCount(long hasRemarkCount) {
		this.hasRemarkCount = hasRemarkCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getNotRemarkCount() {
		return notRemarkCount;
	}

	public void setNotRemarkCount(long notRemarkCount) {
		this.notRemarkCount = notRemarkCount;
	}

}
