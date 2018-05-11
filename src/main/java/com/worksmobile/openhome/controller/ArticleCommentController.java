package com.worksmobile.openhome.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.worksmobile.openhome.bo.ArticleCommentBO;
import com.worksmobile.openhome.model.ArticleComment;
import com.worksmobile.openhome.status.ReturnStatus;

@RestController
@RequestMapping("/api/articlecomment")
public class ArticleCommentController {
	
	@Resource
	private ArticleCommentBO service;

	ReturnStatus returnStatus = ReturnStatus.SUCCESS;
	
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
	
}
