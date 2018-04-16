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

@RestController
@RequestMapping("/api/traffic")
public class TrafficController {
	@Resource
	private TrafficBO service;
	
	/*Traffic*/
	@RequestMapping(value = "/trafficData", method = RequestMethod.GET)
	@ResponseBody
	public Object getTraffic(HttpServletRequest req, HttpServletResponse res) throws Exception{
		Map<String, Object> result = new HashMap<>();
		result.put("trafficData", service.getTrafficData());
		result.put("trafficCount", service.getTrafficCount());

		return result;
	}	
}
