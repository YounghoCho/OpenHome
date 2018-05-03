package com.worksmobile.openhome.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.worksmobile.openhome.bo.ArticleBO;
import com.worksmobile.openhome.status.ReturnStatus;

@RestController
@RequestMapping("/api/admin/")
public class AdminArticleController {

	@Resource
	private ArticleBO service;
	
	ReturnStatus returnStatus = ReturnStatus.SUCCESS;
	//관리자용 게시글 전체 목록(admin)

	@RequestMapping(value = "/allArticles", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllArticles(HttpServletRequest req) throws Exception {	
		int currentPageNo = Integer.parseInt(req.getParameter("currentPageNo"));
		int pageSize = 10;
		
		Map<String, Object> result = new HashMap<>();
		result.put("allArticles", service.getAllArticles(currentPageNo, pageSize));
		result.put("getArticleTotalCount", service.getArticleTotalCount());
		return result;
	}

	//게시글을 삭제한다.(admin)
	@RequestMapping(value = "/articleRemove", method = RequestMethod.DELETE)
	@ResponseBody
	public String removeArticle(HttpServletRequest req) throws Exception {
		int articleNum = Integer.parseInt(req.getParameter("articleNum"));
		service.removeArticle(articleNum);
	return returnStatus.name();
	}
}
