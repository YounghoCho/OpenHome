package com.worksmobile.openhome.controller;

import java.util.List;

import javax.annotation.Resource;

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
			@RequestParam("articleNum") String articleNum, MultipartHttpServletRequest req) throws Exception { 
			return service.addFile(fileAttacher, Integer.parseInt(articleNum), req);
	}
	
	@RequestMapping(value="/fileDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<AttachmentFile> addFile(@RequestParam("articleNumber") int articleNumber) throws Exception { 
			return service.getFiles(articleNumber);
	}
	
	@RequestMapping(value="/fileDownload", method = RequestMethod.POST)
	@ResponseBody
	public void downLoadFile(){
		
	}
	
	
}
