/*
 * Application java
 * @Author : Suji Jang
 */
package com.worksmobile.openhome.model;

import org.springframework.web.multipart.MultipartFile;

public class EditorDTO {

	private MultipartFile Filedata;

	public MultipartFile getFiledata() {
		return Filedata;
	}

	public void setFiledata(MultipartFile filedata) {
		Filedata = filedata;
	}

}
