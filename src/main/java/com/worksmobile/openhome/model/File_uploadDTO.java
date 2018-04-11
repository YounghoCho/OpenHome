/*
 * Application java
 * @Author : Suji Jang
 */
package com.worksmobile.openhome.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class File_uploadDTO {
	private int file_num;
	private String original_file_name;
	private String stored_file_name;
	private int file_size;
	private String file_creater;
	private int message_num;
	private Date stored_date;
	
	private MultipartFile multi_file_name;
	
	public File_uploadDTO() {
		
	}

	public int getFile_num() {
		return file_num;
	}

	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}

	public String getOriginal_file_name() {
		return original_file_name;
	}

	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}

	public String getStored_file_name() {
		return stored_file_name;
	}

	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}

	public int getFile_size() {
		return file_size;
	}

	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}

	public String getFile_creater() {
		return file_creater;
	}

	public void setFile_creater(String file_creater) {
		this.file_creater = file_creater;
	}

	public int getMessage_num() {
		return message_num;
	}

	public void setMessage_num(int message_num) {
		this.message_num = message_num;
	}

	public Date getStored_date() {
		return stored_date;
	}

	public void setStored_date(Date stored_date) {
		this.stored_date = stored_date;
	}

	public MultipartFile getMulti_file_name() {
		return multi_file_name;
	}

	public void setMulti_file_name(MultipartFile multi_file_name) {
		this.multi_file_name = multi_file_name;
	}
	
	
	
	
}