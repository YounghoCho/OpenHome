package com.worksmobile.OpenHomeProject.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.worksmobile.OpenHomeProject.service.BoardService;

@RestController
public class RestapiController {
	@Resource(name="BoardService")
	private BoardService service;

	//Ȩ �Խ��� ����Ʈ �о����(home)
	@RequestMapping(value="/homelist", method=RequestMethod.GET)
	@ResponseBody
	public Object gohomelist() throws Exception{
		Map<String, Object> result= new HashMap<>();
		int currentPageNo=1;
		int pageSize=7;
		result.put("messagelist1", service.MessageList(1, currentPageNo, pageSize)); //boardNumberInt, currentPageNo, pageSize
		result.put("messagelist2", service.MessageList(2, currentPageNo, pageSize));
		result.put("messagelist3", service.MessageList(3, currentPageNo, pageSize));
		result.put("messagelist4", service.MessageList(4, currentPageNo, pageSize));
		return result;	
	}

	//�Խ��� ����Ʈ �о����(board)
	@RequestMapping(value="/boardlist", method=RequestMethod.GET)
	@ResponseBody
	public Object goboard4(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int boardNumberInt= Integer.parseInt(req.getParameter("boardNumberInt"));
		int currentPageNo= Integer.parseInt(req.getParameter("currentPageNo"));
		int pageSize= 10;
		Map<String, Object> result= new HashMap<>();
		result.put("messagelist", service.MessageList(boardNumberInt, currentPageNo, pageSize));
		result.put("countlist", service.CountList(boardNumberInt));
		return result;
	}
	
	//�Խñ� ���� �о����(read)
	@RequestMapping(value="/readContents", method=RequestMethod.GET)
	@ResponseBody
	public Object goreadcontents(HttpServletRequest req, HttpServletResponse res) throws Exception {	

		Map<String, Object> result = new HashMap<>();
		result.put("originalMessageInfo", service.OriginalMessage(Integer.parseInt(req.getParameter("message_num"))));
		return result;
	}
}
