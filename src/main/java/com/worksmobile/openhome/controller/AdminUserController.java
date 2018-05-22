/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.worksmobile.openhome.bo.ManagerBO;
import com.worksmobile.openhome.status.ReturnStatus;

@RestController
@RequestMapping("/api/admin/")
public class AdminUserController {
	@Resource
	private ManagerBO service;

	ReturnStatus returnStatus = ReturnStatus.SUCCESS;
	
	//로그인 정보 확인
	@RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
	@ResponseBody
	public Object checkAdminLogin(HttpServletRequest req, HttpSession session) throws Exception{

		session.setAttribute("userLoginInfo", req.getParameter("managerId"));
		
		Map<String, Object> result = new HashMap<>();
		String managerId = req.getParameter("managerId");
		String managerPwd = req.getParameter("managerPwd");
		result.put("adminLogin", service.checkAdminLogin(managerId, managerPwd));

		return result;
	}	

	//로그아웃
	@RequestMapping(value = "/logOut", method = RequestMethod.POST)
	@ResponseBody
	public String logOut(HttpServletRequest req, HttpSession session) throws Exception{

		session.removeAttribute("userLoginInfo");
		
		return returnStatus.name();
	}
}
