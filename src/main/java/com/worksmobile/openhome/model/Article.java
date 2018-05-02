package com.worksmobile.openhome.model;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Article {
	
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
}
