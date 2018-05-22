package com.worksmobile.openhome.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.bo.AttachmentFileBO;
import com.worksmobile.openhome.controller.annotaion.GetTrafficData;
import com.worksmobile.openhome.model.AttachmentFile;

@RestController
@RequestMapping("/api/attachmentfile")
public class AttachmentFileController {

	@Resource
	private AttachmentFileBO service;
	//author : Suji
	

	//파일을 읽어온다.(5)
	@RequestMapping(value="/fileDetails", method = RequestMethod.POST)
	@ResponseBody
	public List<AttachmentFile> getFiles(@RequestParam("articleNumber") int articleNumber) throws Exception { 
			return service.getFiles(articleNumber);
	}
	//파일을 읽어온다.(5)
	@RequestMapping(value="/checkAndGetAttachmentFile", method = RequestMethod.POST)
	@ResponseBody
	public List<AttachmentFile> checkAndGetAttachmentFile(@RequestParam("articleNum") int articleNum, HttpServletRequest req) throws Exception { 
			return service.checkAndGetAttachmentFile(articleNum, req);
	}

	//에디터에 사진을 추가한다(Level 3)
	@GetTrafficData
	@RequestMapping(value="/addPhotoFile", method = RequestMethod.POST)
	public String addPhotoFile(MultipartHttpServletRequest mreq) throws Exception {
			
			return "service.";
			/*return service.addPhotoFile(Integer.parseInt(req.getParameter("articleNum")), mreq);*/
	}
	
	//파일을 업로드한다(Level 4)
	@GetTrafficData
	@RequestMapping(value="/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public AttachmentFile uploadFile(@RequestParam("articleNum") String articleNum, @RequestParam("fileupload") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.uploadAndAddFile(Integer.parseInt(articleNum), file);
	}
	
	//파일을 삭제한다.
	@RequestMapping(value="/removeAndDelFile", method = RequestMethod.POST)
	@ResponseBody
	public String removeAndDelFile(@RequestParam("fileNum") String fileNum, @RequestParam("storedFileName") String storedFileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.removeAndDelFile(Integer.parseInt(fileNum), storedFileName);
	}
	
	//파일을 다운로드 받는다.
	@RequestMapping(value="/download/{storedFileName}/{originalFileName}")
	public void downloadFile(@PathVariable("storedFileName") String storedFileName, @PathVariable("originalFileName") String originalFileName, HttpServletResponse response) throws Exception {
		service.downloadFile(storedFileName, originalFileName, response);
	}
}