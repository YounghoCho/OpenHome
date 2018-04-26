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

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.worksmobile.openhome.bo.ArticleBO;
import com.worksmobile.openhome.bo.AttachmentFileBO;
import com.worksmobile.openhome.model.Article;
import com.worksmobile.openhome.status.ReturnStatus;

@RestController
@RequestMapping("/api/article/")
public class ArticleController {
	@Resource
	private ArticleBO service;
	
	@Resource
	private AttachmentFileBO fileservice;

	ReturnStatus returnStatus = ReturnStatus.SUCCESS;
	
	//홈화면에 필요한 게시판 내용들을 얻는다(board)
	@RequestMapping(value = "/homeList", method = RequestMethod.GET)
	@ResponseBody
	public Object getHomeList(HttpServletRequest req) throws Exception{
		String[] arrNum = req.getParameterValues("stringArray[]");     		
		String[] boardCount = req.getParameterValues("boardCount");     
		int len = Integer.parseInt(boardCount[0]);
		
		Map<String, Object> result = new HashMap<>();
		int currentPageNo = 1;
		int pageSize = 7;
	
		for(int index = 0; index < len; index++) {
			//게시판의 고유번호가 4,1,2,3,6으로 들어오면, articleList도 4,1,2,3,6 을 붙여 결과를 반환한다.
			result.put("homeList" + (Integer.parseInt(arrNum[index])-1), service.getArticleList(Integer.parseInt(arrNum[index]), currentPageNo, pageSize));
		}
		return result;	
	}
	
	//특정 게시판의 게시글과, 게시글 개수를 얻는다(board)
	@Transactional
	@RequestMapping(value = "/articleList", method = RequestMethod.GET)
	@ResponseBody
	public Object getBoard(HttpServletRequest req) throws Exception {		
		int boardNumber = Integer.parseInt(req.getParameter("boardNumber"));
		int currentPageNo = Integer.parseInt(req.getParameter("currentPageNo"));
		int pageSize = 10;
		
		Map<String, Object> result = new HashMap<>();
		result.put("articleList", service.getArticleList(boardNumber, currentPageNo, pageSize));
		result.put("getArticleTotalCount", service.getArticleTotalCount(boardNumber));
		return result;
	}
	
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
	
	//게시 글의 상세 내용을 얻는다.(board)
	@RequestMapping(value = "/articleDetails", method = RequestMethod.GET)
	@ResponseBody
	public Object getContents(HttpServletRequest req) throws Exception {	
		Map<String, Object> result = new HashMap<>();
		result.put("articleDetails", service.getArticleDetails(Integer.parseInt(req.getParameter("articleNumber"))));
		return result;
	}

	//게시글을 삭제한다.(board, admin)
	@RequestMapping(value = "/articleRemove", method = RequestMethod.DELETE)
	@ResponseBody
	public String removeArticle(HttpServletRequest req) throws Exception {
		int articleNum = Integer.parseInt(req.getParameter("articleNum"));
		service.removeArticle(articleNum);
	return returnStatus.name();
	}

	/*@author Suji Jang*///(board)
	@PostMapping(value = "/addArticleNum")
	@ResponseBody
	public String addArticleNum(HttpServletRequest req, HttpServletResponse res) throws Exception { 
		Article article = new Article(Integer.parseInt(req.getParameter("boardNum")));
		service.addArticleNum(article);
		return String.valueOf(article.getArticleNum());
	}
	//(board)
	@PostMapping(value = "/addArticle")
	@ResponseBody
	public String addArticle(HttpServletRequest req, HttpServletResponse res) throws Exception { 
		Article article = new Article(Integer.parseInt(req.getParameter("articleNum")), Integer.parseInt(req.getParameter("boardNum")),
				req.getParameter("articleSubject"), req.getParameter("articleTextContent"), req.getParameter("articleContent"),
				req.getParameter("articleWriter"), req.getParameter("articleAccessPwd"), "Y");
		return service.addArticle(article);
	}
	//(board)
	//비밀번호 체크 후 게시글 삭제
	@RequestMapping(value = "/checkAndDelArticle", method = RequestMethod.POST)
	@ResponseBody
	public String checkAndDelArticle(@RequestParam("articleNum") String articleNum, 
			@RequestParam("articleAccessPwd") String articleAccessPwd, HttpServletRequest req, HttpServletResponse res) throws Exception { 
		String articleDelResult = service.delCheckedArticle(service.checkPwd(Integer.parseInt(articleNum), articleAccessPwd));
		String fileDelResult = fileservice.removeFiles(Integer.parseInt(articleNum), req);
		if (articleDelResult.equals("success") && (fileDelResult.equals("success") || fileDelResult.equals("none"))) {
			return "success";
		}
		return "none";
	}
	//(board)
	//비밀번호 체크 후 게시글 가져오기(수정, 삭제)
	@RequestMapping(value = "/checkAndGetArticle", method = RequestMethod.POST)
	@ResponseBody
	public Article checkAndGetArticle(@RequestParam("articleNum") String articleNum, 
			@RequestParam("articleAccessPwd") String articleAccessPwd, HttpServletRequest req, HttpServletResponse res) throws Exception { 
		return service.getArticle(service.checkPwd(Integer.parseInt(articleNum), articleAccessPwd));
	}
	//(board)
	//게시글 수정
	@PostMapping(value = "/modArticle")
	@ResponseBody
	public String modArticle(@RequestParam("articleNum") int articleNum, HttpServletRequest req, HttpServletResponse res) throws Exception { 
		Article article = new Article(articleNum, req.getParameter("articleSubject"),
							req.getParameter("articleTextContent"), req.getParameter("articleContent"),
							req.getParameter("articleWriter"));
		return service.modArticle(article);
	}
   
}

