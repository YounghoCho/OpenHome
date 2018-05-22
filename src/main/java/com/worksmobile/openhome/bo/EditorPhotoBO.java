package com.worksmobile.openhome.bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface EditorPhotoBO {

	public String uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response);
}
