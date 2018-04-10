package com.worksmobile.OpenHomeProject.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.worksmobile.OpenHomeProject.service.BoardService;

@Controller
public class HomeController {
	
	@Resource(name = "BoardService")
	private BoardService service;

	@RequestMapping(value = "/board")	
	public ModelAndView goboard() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("body-home");
		return mav;
	}
	
	@RequestMapping(value = "/admin")
	public ModelAndView goadmin() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("body-admin");
		return mav;
	}
}