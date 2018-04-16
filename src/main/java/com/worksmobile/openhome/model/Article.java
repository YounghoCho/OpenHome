package com.worksmobile.openhome.model;

import java.util.Date;

public class Article {

	private int articleNum;
	private int boardNum;
	private String articleSubject;
	private String articleTextContent;	
	private String articleContent;
	private Date articleDate;
	private String articleWriter;
	private String articleAccessPwd;
	private int ROWNUM;
	
	public Article() {

	}
	
	public Article(int boardNum, String articleSubject, String articleTextContent, String articleContent,
			String articleWriter, String articleAccessPwd) {
		this.boardNum = boardNum;
		this.articleSubject = articleSubject;
		this.articleTextContent = articleTextContent;
		this.articleContent = articleContent;
		this.articleWriter = articleWriter;
		this.articleAccessPwd = articleAccessPwd;
	}
	
	
	public int getROWNUM() {
		return ROWNUM;
	}

	public void setROWNUM(int ROWNUM) {
		this.ROWNUM = ROWNUM;
	}

	public int getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getArticleSubject() {
		return articleSubject;
	}

	public void setArticleSubject(String articleSubject) {
		this.articleSubject = articleSubject;
	}

	public String getArticleTextContent() {
		return articleTextContent;
	}

	public void setArticleTextContent(String articleTextContent) {
		this.articleTextContent = articleTextContent;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public Date getArticleDate() {
		return articleDate;
	}

	public void setArticleDate(Date articleDate) {
		this.articleDate = articleDate;
	}

	public String getArticleWriter() {
		return articleWriter;
	}

	public void setArticleWriter(String articleWriter) {
		this.articleWriter = articleWriter;
	}

	public String getArticleAccessPwd() {
		return articleAccessPwd;
	}

	public void setArticleAccessPwd(String articleAccessPwd) {
		this.articleAccessPwd = articleAccessPwd;
	}


}
