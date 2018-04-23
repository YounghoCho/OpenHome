package com.worksmobile.openhome.model;

import java.util.Date;

public class Article {

	private int articleNum;
	private int boardNum;
	private String boardTitle;
	private String articleSubject;
	private String articleTextContent;	
	private String articleContent;
	private Date articleDate;
	private String articleWriter;
	private String articleAccessPwd;
	private String articleStatus;
	private int ROWNUM;
	
	public Article() {

	}
	
	public Article(int boardNum) {
		this.boardNum = boardNum;
	}
	
	public Article(int articleNum, String articleSubject, String articleTextContent, String articleContent,
			String articleWriter) {
		this.articleNum = articleNum;
		this.articleSubject = articleSubject;
		this.articleTextContent = articleTextContent;
		this.articleContent = articleContent;
		this.articleWriter = articleWriter;
	}
	
	public Article(int articleNum, int boardNum, String articleSubject, String articleTextContent, String articleContent,
			String articleWriter, String articleAccessPwd, String articleStatus) {
		this.articleNum = articleNum;
		this.boardNum = boardNum;
		this.articleSubject = articleSubject;
		this.articleTextContent = articleTextContent;
		this.articleContent = articleContent;
		this.articleWriter = articleWriter;
		this.articleAccessPwd = articleAccessPwd;
		this.articleStatus = articleStatus;
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

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
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

	public String getArticleStatus() {
		return articleStatus;
	}

	public void setArticleStatus(String articleStatus) {
		this.articleStatus = articleStatus;
	}

}
