/*
 * Application java
 * @Author : Suji Jang
 */
package com.worksmobile.openhome.model;

import org.springframework.web.multipart.MultipartFile;

public class Photo_uploadDTO {

	private int photo_num;
	private int message_num;
	private String original_file_name;
	private String stored_file_name;
	private int file_size;
	private String file_type;
	
	private MultipartFile file_name;
	
	
	public Photo_uploadDTO() {
		
	}


	public int getPhoto_num() {
		return photo_num;
	}


	public void setPhoto_num(int photo_num) {
		this.photo_num = photo_num;
	}


	public int getMessage_num() {
		return message_num;
	}


	public void setMessage_num(int message_num) {
		this.message_num = message_num;
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


	public String getFile_type() {
		return file_type;
	}


	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}


	public MultipartFile getFile_name() {
		return file_name;
	}


	public void setFile_name(MultipartFile file_name) {
		this.file_name = file_name;
	}
	
	
	
	
}
