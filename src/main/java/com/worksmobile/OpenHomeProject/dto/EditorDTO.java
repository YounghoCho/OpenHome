package com.worksmobile.OpenHomeProject.dto;

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
