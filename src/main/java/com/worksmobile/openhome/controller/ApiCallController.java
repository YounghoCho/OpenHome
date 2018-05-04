package com.worksmobile.openhome.controller;

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
	
	@RequestMapping(value = "/apiCount", method = RequestMethod.POST)
	@ResponseBody
	public void insertApiCall(HttpServletRequest req) throws Exception {
		String apiLevel = req.getParameter("apiLevel");
		service.insertApiCall(apiLevel);		
	}
}
