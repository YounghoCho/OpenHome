package com.worksmobile.openhome.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleComment {
	
	private int commentNum;
	private int articleNum;
	private String commentAccessPwd;
	private String commentContent;
	private String commentDate;
	private String commentWriter;
	private String commentOriginUpload;
	private String commentStoredUpload;
	private int commentUploadSize;
	private MultipartFile commentFile;
}
