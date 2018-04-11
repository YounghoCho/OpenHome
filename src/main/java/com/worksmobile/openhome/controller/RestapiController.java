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

import com.worksmobile.openhome.BO.OpenhomeBO;

@RestController
@RequestMapping("/api")
public class RestapiController {
	@Resource(name = "BoardService")
	private OpenhomeBO service;

	//call data of home-page for 4 boards
	@RequestMapping(value = "/homeList", method = RequestMethod.GET)
	@ResponseBody
	public Object getHomeList() throws Exception{
		Map<String, Object> result = new HashMap<>();
		int currentPageNo = 1;
		int pageSize = 7;
		result.put("articleList1", service.getArticleList(1, currentPageNo, pageSize)); //boardNumber, currentPageNo, pageSize
		result.put("articleList2", service.getArticleList(2, currentPageNo, pageSize));
		result.put("articleList3", service.getArticleList(3, currentPageNo, pageSize));
		result.put("articleList4", service.getArticleList(4, currentPageNo, pageSize));

		return result;	
	}

	//call data of board-page for board lists
	@RequestMapping(value = "/articleList", method = RequestMethod.GET)
	@ResponseBody
	public Object getBoard(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int boardNumber = Integer.parseInt(req.getParameter("boardNumber"));
		int currentPageNo = Integer.parseInt(req.getParameter("currentPageNo"));
		int pageSize = 10;

		Map<String, Object> result = new HashMap<>();
		result.put("articleList", service.getArticleList(boardNumber, currentPageNo, pageSize));
		result.put("boardTotalCount", service.getBoardTotalCount(boardNumber));

		return result;
	}
	
	//call data of board-read for each board's sentences
	@RequestMapping(value = "/articleDetails", method = RequestMethod.GET)
	@ResponseBody
	public Object getContents(HttpServletRequest req, HttpServletResponse res) throws Exception {	

		Map<String, Object> result = new HashMap<>();
		result.put("articleDetails", service.getArticleDetails(Integer.parseInt(req.getParameter("articleNumber"))));
		return result;
	}
	
	//call Traffic data
	@RequestMapping(value = "/trafficData", method = RequestMethod.GET)
	@ResponseBody
	public Object getTraffic(HttpServletRequest req, HttpServletResponse res) throws Exception{
		Map<String, Object> result = new HashMap<>();
		result.put("trafficData", service.getTrafficData());
		result.put("trafficCount", service.getTrafficCount());

		return result;
	}	

}
