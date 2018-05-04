package com.worksmobile.openhome.model;

import java.util.Date;

public class ArticleComment {
	
	private int commentNum;
	private int articleNum;
	private String commentAccessPwd;
	private String commentContent;
	private Date commentDate;
	private String commentWriter;
	
	public ArticleComment() {
		
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

	public String getCommentAccessPwd() {
		return commentAccessPwd;
	}

	public void setCommentAccessPwd(String commentAccessPwd) {
		this.commentAccessPwd = commentAccessPwd;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentWriter() {
		return commentWriter;
	}

	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}
	
	
}
