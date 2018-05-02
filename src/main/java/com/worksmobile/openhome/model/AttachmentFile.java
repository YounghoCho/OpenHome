package com.worksmobile.openhome.model;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class AttachmentFile {
		
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
	
	private int fileNum;
	private int articleNum;
	private String originalFileName;
	private String storedFileName;
	private int fileSize;
	private String fileAttacher;
	private Date fileDate;
}
