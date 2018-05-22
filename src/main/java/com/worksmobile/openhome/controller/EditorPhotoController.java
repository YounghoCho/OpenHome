package com.worksmobile.openhome.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.worksmobile.openhome.bo.EditorPhotoBO;

@RestController
@RequestMapping("/api/editorphoto/")
public class EditorPhotoController {
	
	@Resource
	private EditorPhotoBO service;
	
	@RequestMapping(value="/uploadPhotoFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadCommentFile(@RequestParam("photoname") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.uploadFile(file, request, response);
	}
}
