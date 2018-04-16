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

@RestController
@RequestMapping("/api/board")
public class BoardController {
	@Resource
	private BoardBO service;

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
	return "SUCCESS";
	}
	
	//게시판을 추가한다.
	@RequestMapping(value = "/newBoard", method = RequestMethod.POST)
	@ResponseBody
	public String createNewBoard(HttpServletRequest req) throws Exception {
		service.createNewBoard(req.getParameter("boardTitle"));		
		return "SUCCESS";
	}
}
