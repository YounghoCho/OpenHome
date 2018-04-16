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
import com.worksmobile.openhome.bo.ManagerBO;

@RestController
@RequestMapping("/api/admin/")
public class ManagerController {
	@Resource
	private ManagerBO service;

	//로그인 정보 확인
	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	@ResponseBody
	public Object checkAdminLogin(HttpServletRequest req, HttpServletResponse res) throws Exception{
		Map<String, Object> result = new HashMap<>();
		String managerId = req.getParameter("managerId");
		String managerPwd = req.getParameter("managerPwd");
		result.put("adminLogin", service.checkAdminLogin(managerId, managerPwd));

		return result;
	}	
}
