/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.model;

import java.util.Date;

public class Message {
	private String boardTitle;

	private int articleNum;
	private int boardNum;
	private int countAll;
	private int ROWNUM;
	private String articleSubject;
	private String articleTextContent;	
	private String articleContent;
	private Date articleDate;
	private String articleWriter;
	private String articleAccessPwd;
	
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
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
	public int getCountAll() {
		return countAll;
	}
	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}
	public int getROWNUM() {
		return ROWNUM;
	}
	public void setROWNUM(int rOWNUM) {
		ROWNUM = rOWNUM;
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
