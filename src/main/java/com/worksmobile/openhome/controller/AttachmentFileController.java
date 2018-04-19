package com.worksmobile.openhome.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.bo.AttachmentFileBO;
import com.worksmobile.openhome.model.AttachmentFile;

@RestController
@RequestMapping("/api/attachmentfile/")
public class AttachmentFileController {

	@Resource
	private AttachmentFileBO service;
	
	@RequestMapping(value="/addFile", method = RequestMethod.POST)
	@ResponseBody
	public String addFile(@RequestParam("articleWriter") String fileAttacher,
			@RequestParam("articleNum") int articleNum, MultipartHttpServletRequest req) throws Exception { 
		System.out.println("여기 들어오나여?");
		System.out.println(fileAttacher +  articleNum);
			return service.addFile(fileAttacher, articleNum, req);
	}
	
	@RequestMapping(value="/fileDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<AttachmentFile> getFiles(@RequestParam("articleNumber") int articleNumber) throws Exception { 
			return service.getFiles(articleNumber);
	}
	
	@RequestMapping(value="/fileDownload", method = RequestMethod.POST)
	@ResponseBody
	public void downLoadFile(@RequestParam("fileNum") String fileNum,
			@RequestParam("originalFileName") String originalFileName,
			@RequestParam("storedFileName") String storedFileName,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("일단 컨트롤러");
		System.out.println(fileNum);
		System.out.println(originalFileName);
		System.out.println(storedFileName);
		AttachmentFile attachmentfile = new AttachmentFile(Integer.parseInt(fileNum), originalFileName, storedFileName);
		service.downloadFile(attachmentfile, req, res);
	}
	
	
}
