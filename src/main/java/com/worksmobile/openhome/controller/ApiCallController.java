package com.worksmobile.openhome.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.worksmobile.openhome.bo.ApiCallBO;
import com.worksmobile.openhome.status.ReturnStatus;

@RestController
@RequestMapping("/api/apiCall")
public class ApiCallController {
	@Resource
	private ApiCallBO service;
	
	ReturnStatus returnStatus = ReturnStatus.SUCCESS;
	
	//API 데이터를 삽입한다.
	@RequestMapping(value = "/apiCount", method = RequestMethod.POST)
	@ResponseBody
	public void insertApiCall(HttpServletRequest req) throws Exception {
		String apiLevel = req.getParameter("apiLevel");
		service.insertApiCall(apiLevel);
	}
	//API 데이터를 호출한다.
	@RequestMapping(value = "/apiList", method = RequestMethod.GET)
	@ResponseBody
	public Object getTotalApiList(HttpServletRequest req) throws Exception{
		Map<String, Object> result = new HashMap<>();
		result.put("totalApiList", service.getTotalApiList());
		return result;
	}	
}
