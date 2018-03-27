package com.worksmobile.OpenHomeProject.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.worksmobile.OpenHomeProject.service.BoardService;

@Controller
public class HomeController {
	
	@Resource(name="BoardService")
	private BoardService service;
	
	@RequestMapping(value="/index")
	public String index() {
		/*ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");
		return mav;*/
		return "layout";
	}
	
	@RequestMapping(value="/home")
	public ModelAndView gohome() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("homeview");
		return mav;
	}
	
	@RequestMapping(value="/board")
	public ModelAndView goboard() throws Exception {
		ModelAndView mav = new ModelAndView();
		System.out.println("������� ������!!!");
		mav.addObject("boardlist", service.BoardListProcess());
		System.out.println("������� ������");
		mav.setViewName("boardview");
		return mav;
	}
}