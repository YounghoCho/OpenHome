/*
 * Application java
 * @Author : Youngho Jo
 *           Suji    Jang
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.bo.ArticleBO;
import com.worksmobile.openhome.bo.AttachmentFileBO;
import com.worksmobile.openhome.bo.BoardBO;
import com.worksmobile.openhome.bo.ManagerBO;
import com.worksmobile.openhome.bo.TrafficBO;
import com.worksmobile.openhome.model.Article;
import com.worksmobile.openhome.model.AttachmentFile;

@RestController
@RequestMapping("/api")
public class RestapiController {
	
	@Resource(name = "ArticleBO")
	private ArticleBO articlebo;
	
	@Resource(name = "TrafficBO")
	private TrafficBO trafficbo;
	
	@Resource(name = "BoardBO")
	private BoardBO boardbo;
	
	@Resource(name = "ManagerBO")
	private ManagerBO managerbo;
	
	@Resource(name = "AttachmentFileBO")
	private AttachmentFileBO attachmentfilebo;

	/*@author Youngho Jo*/
	/*board*/
	//Board List
	@RequestMapping(value = "/menuList", method = RequestMethod.GET)
	@ResponseBody
	public Object getMenuList() throws Exception{
		Map<String, Object> result = new HashMap<>();
		result.put("menuList", boardbo.getMenuList());
		return result;	
	}
	//Board Remove
	@RequestMapping(value = "/boardRemove", method = RequestMethod.DELETE)
	@ResponseBody
	public String removeBoard(HttpServletRequest req) throws Exception {
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		boardbo.removeBoard(boardNum);
	return "SUCCESS";
	}
	
	
	/*Article*/
	//Home(4 boards)
	@RequestMapping(value = "/homeList", method = RequestMethod.GET)
	@ResponseBody
	public Object getHomeList(HttpServletRequest req) throws Exception{
		String[] arrNum = req.getParameterValues("stringArray[]");     		
		//System.out.println("배열에 들어있는값 : "+Arrays.toString(aStr));//출력1
		//for(String str : aStr){System.out.println(str);}//출력2
		String[] boardCount = req.getParameterValues("boardCount");     
		int len = Integer.parseInt(boardCount[0]);
		
		Map<String, Object> result = new HashMap<>();
		int currentPageNo = 1;
		int pageSize = 7;

		result.put("menuList", boardbo.getMenuList());
		for(int index = 0; index < len; index++) {
			result.put("articleList" + index, articlebo.getArticleList(Integer.parseInt(arrNum[index]), currentPageNo, pageSize));
		}

		return result;	
	}
	
	//Board Articles
	@RequestMapping(value = "/articleList", method = RequestMethod.GET)
	@ResponseBody
	public Object getBoard(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int boardNumber = Integer.parseInt(req.getParameter("boardNumber"));
		int currentPageNo = Integer.parseInt(req.getParameter("currentPageNo"));
		int pageSize = 10;
			Map<String, Object> result = new HashMap<>();
		result.put("articleList", articlebo.getArticleList(boardNumber, currentPageNo, pageSize));
		result.put("getArticleTotalCount", articlebo.getArticleTotalCount(boardNumber));
		return result;
	}
	
	//call data of board-read for each board's sentences
	@RequestMapping(value = "/articleDetails", method = RequestMethod.GET)
	@ResponseBody
	public Object getContents(HttpServletRequest req, HttpServletResponse res) throws Exception {	
			Map<String, Object> result = new HashMap<>();
		result.put("articleDetails", articlebo.getArticleDetails(Integer.parseInt(req.getParameter("articleNumber"))));
		return result;
	}
	
	//Admin Article Lists
	@RequestMapping(value = "/allArticles", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllArticles() throws Exception {	
			Map<String, Object> result = new HashMap<>();
		result.put("allArticles", articlebo.getAllArticles());
		return result;
	}
	//Admin Article Remove
	@RequestMapping(value = "/articleRemove", method = RequestMethod.DELETE)
	@ResponseBody
	public String removeArticle(HttpServletRequest req) throws Exception {
		int articleNum = Integer.parseInt(req.getParameter("articleNum"));
		articlebo.removeArticle(articleNum);
	return "SUCCESS";
	}
	
	//call Traffic data
	@RequestMapping(value = "/trafficData", method = RequestMethod.GET)
	@ResponseBody
	public Object getTraffic(HttpServletRequest req, HttpServletResponse res) throws Exception{
		Map<String, Object> result = new HashMap<>();
		result.put("trafficData", trafficbo.getTrafficData());
		result.put("trafficCount", trafficbo.getTrafficCount());
			return result;
	}	
	
	//Login data
	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	@ResponseBody
	public Object checkAdminLogin(HttpServletRequest req, HttpServletResponse res) throws Exception{
		Map<String, Object> result = new HashMap<>();
		String managerId = req.getParameter("managerId");
		String managerPwd = req.getParameter("managerPwd");
		result.put("checkAdminLogin", managerbo.checkAdminLogin(managerId, managerPwd));
		return result;
	}
	
	/*@author Suji Jang*/
	@RequestMapping(value="/addArticle", method=RequestMethod.POST)
	public String addArticle(HttpServletRequest req, HttpServletResponse res) throws Exception { 
		System.out.println("hh");
		articlebo.addArticleNum();
		Article article = new Article(Integer.parseInt(req.getParameter("boardNum")), req.getParameter("articleSubject"),
				req.getParameter("articleTextContent"), req.getParameter("articleContent"), req.getParameter("articleWriter"), 
				req.getParameter("articleAccessPwd"));
		int num = articlebo.addArticle(article);
		if (num == 1) {
			return "ok";
		} else {
			return "sorry";
		}
		
	}
	
	@RequestMapping(value="/addFile", method=RequestMethod.POST)
	public void addFile(int articleNum, String fileAttacher, MultipartHttpServletRequest multi) throws Exception { 

		attachmentfilebo.addFile(articleNum, fileAttacher, multi);
		
	}
	
}