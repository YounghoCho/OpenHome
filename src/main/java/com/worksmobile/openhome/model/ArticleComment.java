package com.worksmobile.openhome.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleComment {
	
	private int commentNum;
	private int articleNum;
	private String commentAccessPwd;
	private String commentContent;
	private Date commentDate;
	private String commentWriter;	
}
