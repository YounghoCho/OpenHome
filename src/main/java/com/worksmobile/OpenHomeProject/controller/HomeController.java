package com.worksmobile.OpenHomeProject.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.worksmobile.OpenHomeProject.service.BoardService;

@Controller
public class HomeController {
	
	@Resource(name="BoardService")
	private BoardService service;
	
	@RequestMapping(value="/index")
	public String index() {
		return "layout";
	}

	@RequestMapping(value="/home")
	public ModelAndView gohome() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardlist", service.BoardListProcess());
		mav.setViewName("homeview");
		return mav;
	}
	
	@RequestMapping(value="/board")
	public ModelAndView goboard() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("messagelist", service.MessageListProcess());	//addObject("jsp에서 호출할 변수", db에서 가져오는데 사용되는 service.메소드);
		mav.setViewName("boardview");
		return mav;
	}
}

