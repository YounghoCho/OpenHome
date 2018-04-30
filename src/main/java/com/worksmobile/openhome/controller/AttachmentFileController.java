package com.worksmobile.openhome.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping(value="/checkFileExist", method = RequestMethod.POST)
	@ResponseBody
	public int checkFileExist(@RequestParam("articleNum") int articleNum) throws Exception {
			return service.checkFileExist(articleNum);
	}
	
	@RequestMapping(value="/addFile", method = RequestMethod.POST)
	@ResponseBody
	public String addFile(@RequestParam("articleWriter") String fileAttacher,
			@RequestParam("articleNum") int articleNum, MultipartHttpServletRequest mreq) throws Exception {
		System.out.println(fileAttacher +  articleNum);
			return service.addFile(fileAttacher, articleNum, mreq);
	}
	
	@RequestMapping(value="/fileDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<AttachmentFile> getFiles(@RequestParam("articleNumber") int articleNumber) throws Exception { 
			return service.getFiles(articleNumber);
	}
	
	@RequestMapping(value="/checkAndGetAttachmentFile", method = RequestMethod.POST)
	@ResponseBody
	public List<AttachmentFile> checkAndGetAttachmentFile(@RequestParam("articleNum") int articleNum, HttpServletRequest req) throws Exception { 
			return service.checkAndGetAttachmentFile(articleNum, req);
	}
	
	@RequestMapping(value="/modFile", method = RequestMethod.PUT)
	@ResponseBody
	public String modFile(@RequestParam("articleWriter") String fileAttacher,
			@RequestParam("articleNum") int articleNum, MultipartHttpServletRequest mreq) throws Exception {
			return service.modFile(fileAttacher, articleNum, mreq);
	}
	
	@RequestMapping(value="/removeFiles", method = RequestMethod.DELETE)
	@ResponseBody
	public String modFile(@RequestParam("articleNum") int articleNum, 
			MultipartHttpServletRequest mreq) throws Exception {
			return service.removeFiles(articleNum, mreq);
	}
	
	@RequestMapping(value="/removeFile", method = RequestMethod.DELETE)
	@ResponseBody
	public String removeFile(@RequestParam("fileNum") String fileNum,
			HttpServletRequest req) throws Exception {
			return service.removeFile(Integer.parseInt(fileNum), req);
	}

	
	@RequestMapping(value="/addPhotoFile", method = RequestMethod.POST)
	public String addPhotoFile(MultipartHttpServletRequest mreq) throws Exception {
			
			return "service.";
			/*return service.addPhotoFile(Integer.parseInt(req.getParameter("articleNum")), mreq);*/
	}
	
	
}
