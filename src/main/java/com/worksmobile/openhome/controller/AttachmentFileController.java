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
import com.worksmobile.openhome.controller.annotaion.GetArticleWriteApiCall;
import com.worksmobile.openhome.controller.annotaion.GetFileUploadApiCall;
import com.worksmobile.openhome.controller.annotaion.GetWriteTraffic;
import com.worksmobile.openhome.model.AttachmentFile;

@RestController
@RequestMapping("/api/attachmentfile/")
public class AttachmentFileController {

	@Resource
	private AttachmentFileBO service;
	//author : Suji
	//파일을 업로드한다(Level 4)
	@GetWriteTraffic
	@GetFileUploadApiCall
	@RequestMapping(value="/addFile", method = RequestMethod.POST)
	@ResponseBody
	public String addFile(@RequestParam("articleWriter") String fileAttacher,
			@RequestParam("articleNum") int articleNum, MultipartHttpServletRequest mreq) throws Exception {
		System.out.println(fileAttacher +  articleNum);
			return service.addFile(fileAttacher, articleNum, mreq);
	}
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
	//파일을 수정한다.(Level 4)
	@GetWriteTraffic
	@GetFileUploadApiCall
	@RequestMapping(value="/modFile", method = RequestMethod.POST)
	@ResponseBody
	public String modFile(@RequestParam("articleWriter") String fileAttacher,
			@RequestParam("articleNum") int articleNum, MultipartHttpServletRequest mreq) throws Exception {
			return service.modFile(fileAttacher, articleNum, mreq);
	}
	//파일을 삭제한다.
	@RequestMapping(value="/removeFile", method = RequestMethod.POST)
	@ResponseBody
	public String removeFile(@RequestParam("fileNum") String fileNum,
			HttpServletRequest req) throws Exception {
			return service.removeFile(Integer.parseInt(fileNum), req);
	}
	//에디터에 사진을 추가한다(Level 3)
	@GetWriteTraffic
	@GetArticleWriteApiCall
	@RequestMapping(value="/addPhotoFile", method = RequestMethod.POST)
	public String addPhotoFile(MultipartHttpServletRequest mreq) throws Exception {
			
			return "service.";
			/*return service.addPhotoFile(Integer.parseInt(req.getParameter("articleNum")), mreq);*/
	}	
}