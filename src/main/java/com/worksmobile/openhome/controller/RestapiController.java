/*
 * Application java
 * @Author : Youngho Jo
 *           Suji    Jang
 */
package com.worksmobile.openhome.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.worksmobile.openhome.BO.OpenhomeBO;

@RestController
@RequestMapping("/api")
public class RestapiController {
	@Resource(name = "BoardService")
	private OpenhomeBO service;

	//Menu Lists
	@RequestMapping(value = "/menuList", method = RequestMethod.GET)
	@ResponseBody
	public Object getMenuList() throws Exception{
		Map<String, Object> result = new HashMap<>();
		result.put("menuList", service.getMenuList());

		return result;	
	}

	//Home(4 boards)
	@RequestMapping(value = "/homeList", method = RequestMethod.GET)
	@ResponseBody
	public Object getHomeList(HttpServletRequest req) throws Exception{
		String[] arrNum = req.getParameterValues("stringArray[]");     		
		//System.out.println("배열에 들어있는값 : "+Arrays.toString(aStr));//출력1
		//for(String str : aStr){System.out.println(str);}//출력2
		Map<String, Object> result = new HashMap<>();
		int currentPageNo = 1;
		int pageSize = 7;
		result.put("menuList", service.getMenuList());
		result.put("articleList1", service.getArticleList(Integer.parseInt(arrNum[1]), currentPageNo, pageSize)); //boardNumber, currentPageNo, pageSize
		result.put("articleList2", service.getArticleList(Integer.parseInt(arrNum[2]), currentPageNo, pageSize));
		result.put("articleList3", service.getArticleList(Integer.parseInt(arrNum[3]), currentPageNo, pageSize));
		result.put("articleList4", service.getArticleList(Integer.parseInt(arrNum[4]), currentPageNo, pageSize));

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
		result.put("articleList", service.getArticleList(boardNumber, currentPageNo, pageSize));
		result.put("getArticleTotalCount", service.getArticleTotalCount(boardNumber));

		return result;
	}
	
	//Board Details
	@RequestMapping(value = "/articleDetails", method = RequestMethod.GET)
	@ResponseBody
	public Object getContents(HttpServletRequest req, HttpServletResponse res) throws Exception {	

		Map<String, Object> result = new HashMap<>();
		result.put("articleDetails", service.getArticleDetails(Integer.parseInt(req.getParameter("articleNumber"))));
		return result;
	}
	
	//Traffic data
	@RequestMapping(value = "/trafficData", method = RequestMethod.GET)
	@ResponseBody
	public Object getTraffic(HttpServletRequest req, HttpServletResponse res) throws Exception{
		Map<String, Object> result = new HashMap<>();
		result.put("trafficData", service.getTrafficData());
		result.put("trafficCount", service.getTrafficCount());

		return result;
	}	

	//Login data
	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	@ResponseBody
	public Object checkAdminLogin(HttpServletRequest req, HttpServletResponse res) throws Exception{
		Map<String, Object> result = new HashMap<>();
		String managerId = req.getParameter("managerId");
		String managerPwd = req.getParameter("managerPwd");
		System.out.println(managerId + " " + managerPwd);
		result.put("checkAdminLogin", service.checkAdminLogin(managerId, managerPwd));

		return result;
	}	
}
