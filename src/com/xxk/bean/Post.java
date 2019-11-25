package com.xxk.bean;

import java.sql.Timestamp;

public class Post {
	private int postId;
	private String text;
	private Timestamp createdTime;
	private String username;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Post(int postId, String text, Timestamp createdTime, String username) {
		super();
		this.postId = postId;
		this.text = text;
		this.createdTime = createdTime;
		this.username = username;
	}

	public Post() {
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", text=" + text + ", createdTime=" + createdTime + ", username=" + username
				+ "]";
	}
}
