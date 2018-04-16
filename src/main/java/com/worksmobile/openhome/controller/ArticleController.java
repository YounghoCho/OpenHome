/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.worksmobile.openhome.bo.ArticleBO;
import com.worksmobile.openhome.model.Article;


@RestController
@RequestMapping("/api/article/")
public class ArticleController {
	@Resource
	private ArticleBO service;
	
	//홈화면에 필요한 게시판 내용들을 얻는다.
	@RequestMapping(value = "/homeList", method = RequestMethod.GET)
	@ResponseBody
	public Object getHomeList(HttpServletRequest req) throws Exception{
		String[] arrNum = req.getParameterValues("stringArray[]");     		
		String[] boardCount = req.getParameterValues("boardCount");     
		int len = Integer.parseInt(boardCount[0]);
		
		Map<String, Object> result = new HashMap<>();
		int currentPageNo = 1;
		int pageSize = 7;
	
		//Board Articles
		for(int index = 0; index < len; index++) {
			//게시판의 고유번호가 4,1,2,3,6으로 들어오면, articleList도 4,1,2,3,6 을 붙여 결과를 반환한다.
			result.put("articleList" + (Integer.parseInt(arrNum[index])-1), service.getArticleList(Integer.parseInt(arrNum[index]), currentPageNo, pageSize));
		}
		return result;	
	}
	
	//특정 게시판의 게시글과, 게시글 개수를 얻는다.
	@RequestMapping(value = "/articleList", method = RequestMethod.GET)
	@ResponseBody
	public Object getBoard(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int boardNumber = Integer.parseInt(req.getParameter("boardNumber"));
		int currentPageNo = Integer.parseInt(req.getParameter("currentPageNo"));
		int pageSize = 10;
			Map<String, Object> result = new HashMap<>();
		result.put("articleList", service.getArticleList(boardNumber, currentPageNo, pageSize));
		result.put("getArticleTotalCount", service.getArticleTotalCount(boardNumber));
		return result;
	}
	
	//게시 글의 상세 내용을 얻는다.
	@RequestMapping(value = "/articleDetails", method = RequestMethod.GET)
	@ResponseBody
	public Object getContents(HttpServletRequest req, HttpServletResponse res) throws Exception {	
			Map<String, Object> result = new HashMap<>();
		result.put("articleDetails", service.getArticleDetails(Integer.parseInt(req.getParameter("articleNumber"))));
		return result;
	}
	
	
	@RequestMapping(value = "/allArticles", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllArticles() throws Exception {	

		Map<String, Object> result = new HashMap<>();
		result.put("allArticles", service.getAllArticles());
		return result;
	}
	
	//게시글을 삭제한다.
	@RequestMapping(value = "/articleRemove", method = RequestMethod.DELETE)
	@ResponseBody
	public String removeArticle(HttpServletRequest req) throws Exception {
		int articleNum = Integer.parseInt(req.getParameter("articleNum"));
		service.removeArticle(articleNum);
	return "SUCCESS";
	}

	/*@author Suji Jang*/
	@RequestMapping(value="/addArticle", method=RequestMethod.POST)
	public String addArticle(HttpServletRequest req, HttpServletResponse res) throws Exception { 
		System.out.println("hh");
		service.addArticleNum();
		Article article = new Article(Integer.parseInt(req.getParameter("boardNum")), req.getParameter("articleSubject"),
				req.getParameter("articleTextContent"), req.getParameter("articleContent"), req.getParameter("articleWriter"), 
				req.getParameter("articleAccessPwd"));
		int num = service.addArticle(article);
		if (num == 1) {
			return "ok";
		} else {
			return "sorry";
		}
		
	}
	
	
}

