package com.worksmobile.openhome.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.ExitCodeEvent;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.worksmobile.openhome.bo.ArticleCommentBO;
import com.worksmobile.openhome.model.ArticleComment;
import com.worksmobile.openhome.model.AttachmentFile;
import com.worksmobile.openhome.status.ReturnStatus;

@RestController
@RequestMapping("/api/articlecomment")
public class ArticleCommentController {
	
	@Resource
	private ArticleCommentBO service;

	ReturnStatus returnSuccess = ReturnStatus.SUCCESS;
	
	@RequestMapping(value = "/commentDetails", method = RequestMethod.GET)
	@ResponseBody
	public List<ArticleComment> getComments(@RequestParam("articleNumber") int articleNum) {
		return service.getComments(articleNum);
	}
	
	@RequestMapping(value = "/commentCount", method = RequestMethod.GET)
	@ResponseBody
	public int getCommentCount(@RequestParam("articleNumber") int articleNum) {
		return service.getCommentCount(articleNum);
	}
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(@RequestBody ArticleComment articlecomment) {
		return service.addComment(articlecomment);
	}
	
	@RequestMapping(value = "/chkCommentPwd", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(@RequestParam("commentNum") String commentNum,
			@RequestParam("commentAccessPwd") String commentAccessPwd ) {
		return service.chkCommentPwd(Integer.parseInt(commentNum), commentAccessPwd);
	}
	
	@RequestMapping(value = "/modComment", method = RequestMethod.PUT)
	@ResponseBody
	public String modComment(@RequestBody ArticleComment articlecomment) {
		return service.modComment(articlecomment);
	}
	
	@RequestMapping(value = "/chkAndDelComment", method = RequestMethod.POST)
	@ResponseBody
	public String chkAndDelComment(@RequestParam("commentNum") String commentNum,
			@RequestParam("commentAccessPwd") String commentAccessPwd, 
			@RequestParam("commentStoredName") String commentStoredName,
			@RequestParam("articleNum") String articleNum) {
		return service.chkAndDelComment(Integer.parseInt(commentNum), commentAccessPwd, commentStoredName, (Integer.parseInt(articleNum)));
	}
	
	@RequestMapping(value="/uploadCommentFile", method = RequestMethod.POST)
	@ResponseBody
	public ArticleComment uploadCommentFile(@RequestParam("commentFile") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.uploadFile(file);
	}
	
	@RequestMapping(value="/delStoredFile", method = RequestMethod.POST)
	@ResponseBody
	public boolean delStoredFile(@RequestParam("storedFileName") String storedFileName) throws Exception {
		return service.delUploadedFile(storedFileName);
	}
	
	//파일을 다운로드 받는다.
	@RequestMapping(value="/download/{storedFileName}/{originalFileName}")
	public void downloadCommentFile(@PathVariable("storedFileName") String storedFileName, @PathVariable("originalFileName") String originalFileName, HttpServletResponse response) throws Exception {
		service.downloadCommentFile(storedFileName, originalFileName, response);
	}

}
