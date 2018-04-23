/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.controller;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.worksmobile.openhome.bo.BoardBO;
import com.worksmobile.openhome.status.ReturnStatus;

@RestController
@RequestMapping("/api/board")
public class BoardController {
	@Resource
	private BoardBO service;

	ReturnStatus returnStatus = ReturnStatus.SUCCESS;
	
	//게시판 목록을 가져온다.
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	@ResponseBody
	public Object getBoardList() throws Exception{
		Map<String, Object> result = new HashMap<>();
		result.put("boardList", service.getBoardList());
		return result;	
	}
	
	//게시판을 삭제한다.
	@RequestMapping(value = "/boardRemove", method = RequestMethod.DELETE)
	@ResponseBody
	public String removeBoard(HttpServletRequest req) throws Exception {
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		service.removeBoard(boardNum);
	return returnStatus.name();
	}
	
	//게시판을 추가한다.
	@RequestMapping(value = "/newBoard", method = RequestMethod.POST)
	@ResponseBody
	public String createNewBoard(HttpServletRequest req) throws Exception {
		service.createNewBoard(req.getParameter("boardTitle"));		
		return returnStatus.name();
	}
	
	//게시판 순서를 변경한다.
	@RequestMapping(value = "/boardOrders", method = RequestMethod.PUT)
	@ResponseBody
	public String updateBoardOrders(HttpServletRequest req) throws Exception {
		String[] tableOrder = req.getParameterValues("tableOrder[]"); 
		
		for(int index = 1; index < tableOrder.length; index++) {
			service.updateBoardOrders(Integer.parseInt(tableOrder[index-1]), index);//새로 정렬된 게시판의 고유번호, 게시판의 우선순위		
		}		
		return returnStatus.name();
	}
	
	//게시판 명을 변경한다.
	@RequestMapping(value = "/boardTitles", method = RequestMethod.PUT)
	@ResponseBody
	public String updateBoardTitles(HttpServletRequest req) throws Exception {
		System.out.println("*******************************게시판번호:"+req.getParameter("boardNum"));
		service.updateBoardTitles(req.getParameter("boardTitle"), Integer.parseInt(req.getParameter("boardNum")));		
		return returnStatus.name();
	}
}
