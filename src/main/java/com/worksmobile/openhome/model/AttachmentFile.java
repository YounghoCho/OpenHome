package com.worksmobile.openhome.model;

import java.io.File;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class AttachmentFile {
	
	private int fileNum;
	private int articleNum;
	private String originalFileName;
	private String storedFileName;
	private int fileSize;
	private String fileAttacher;
	private Date fileDate;
	
	public AttachmentFile() {
		
	}
	
	public AttachmentFile(int fileNum, String originalFileName, String storedFileName) {
		this.fileNum = fileNum;
		this.originalFileName = originalFileName;
		this.storedFileName = storedFileName;
	}
	
	public AttachmentFile(int articleNum, String originalFileName, String storedFileName, int fileSize, String fileAttacher) {
		this.articleNum = articleNum;
		this.originalFileName = originalFileName;
		this.storedFileName = storedFileName;
		this.fileSize = fileSize;
		this.fileAttacher = fileAttacher;
	}

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	public int getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getStoredFileName() {
		return storedFileName;
	}

	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileAttacher() {
		return fileAttacher;
	}

	public void setFileAttacher(String fileAttacher) {
		this.fileAttacher = fileAttacher;
	}

	public Date getFileDate() {
		return fileDate;
	}

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}


}
