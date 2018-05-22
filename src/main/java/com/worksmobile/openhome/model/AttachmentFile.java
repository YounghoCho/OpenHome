package com.worksmobile.openhome.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttachmentFile {
		
	public AttachmentFile(int fileNum, String originalFileName, String storedFileName) {
		this.fileNum = fileNum;
		this.originalFileName = originalFileName;
		this.storedFileName = storedFileName;
	}
	
	public AttachmentFile(String originalFileName, String storedFileName, int fileSize) {
		this.originalFileName = originalFileName;
		this.storedFileName = storedFileName;
		this.fileSize = fileSize;
	}
	
	public AttachmentFile(int articleNum, String originalFileName, String storedFileName, int fileSize) {
		this.articleNum = articleNum;
		this.originalFileName = originalFileName;
		this.storedFileName = storedFileName;
		this.fileSize = fileSize;
	}
	
	public AttachmentFile(String originalFileName, String storedFileName, int fileSize, String uploadStatus, String databaseStatus) {
		this.originalFileName = originalFileName;
		this.storedFileName = storedFileName;
		this.fileSize = fileSize;
		this.uploadStatus = uploadStatus;
		this.databaseStatus = databaseStatus;
	}
	
	private int fileNum;
	private int articleNum;
	private String originalFileName;
	private String storedFileName;
	private int fileSize;
	private Date fileDate;
	private MultipartFile fileupload;
	private String uploadStatus;
	private String databaseStatus;
	
}
