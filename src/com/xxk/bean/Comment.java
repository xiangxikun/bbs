package com.xxk.bean;

import java.sql.Timestamp;

public class Comment {
	private int commentId;
	private String text;
	private Timestamp createdTime;
	private int postId;
	private String username;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
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

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Comment(int commentId, String text, Timestamp createdTime, int postId, String username) {
		super();
		this.commentId = commentId;
		this.text = text;
		this.createdTime = createdTime;
		this.postId = postId;
		this.username = username;
	}

	public Comment() {
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", text=" + text + ", createdTime=" + createdTime + ", postId="
				+ postId + ", username=" + username + "]";
	}
}
