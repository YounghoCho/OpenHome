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

import com.worksmobile.openhome.bo.TrafficBO;
import com.worksmobile.openhome.status.ReturnStatus;

@RestController
@RequestMapping("/api/traffic")

public class TrafficController {
	@Resource
	private TrafficBO service;
	
	ReturnStatus returnStatus = ReturnStatus.SUCCESS;
	
	//트래픽 데이터를 호출한다.
	@RequestMapping(value = "/trafficData", method = RequestMethod.GET)
	@ResponseBody
	public Object getTraffic(HttpServletRequest req, HttpServletResponse res) throws Exception{
		Map<String, Object> result = new HashMap<>();
		result.put("trafficData", service.getTrafficData());
		result.put("trafficCount", service.getTrafficCount());

		return result;
	}	
	//게시판 명을 변경한다.
	@RequestMapping(value = "/contentLength", method = RequestMethod.POST)
	@ResponseBody
	public String insertContentLength(HttpServletRequest req) throws Exception {
		int trafficContentLength = Integer.parseInt(req.getParameter("trafficContentLength"));
		String trafficKind = req.getParameter("trafficKind");
		service.insertContentLength(trafficContentLength, trafficKind);		
		return returnStatus.name();
	}
}
