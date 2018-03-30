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
		/*ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");
		return mav;*/
		return "hello";
	}

	@RequestMapping(value="/home")
	public ModelAndView gohome() throws Exception{
		ModelAndView mav = new ModelAndView();
	//	mav.addObject("boardlist", service.BoardListProcess());
		mav.addObject("messagelist", service.MessageListProcess());
		mav.addObject("messagelist2", service.MessageListProcess2());
		mav.addObject("messagelist3", service.MessageListProcess3());
		mav.addObject("messagelist4", service.MessageListProcess4());				
		mav.setViewName("homeview");
		return mav;	
	}
	@RequestMapping(value="/board1")
	public ModelAndView goboard() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("messagelist", service.MessageListProcess());	//addObject("jsp에서 호출할 변수", db에서 가져오는데 사용되는 service.메소드);
		mav.addObject("countlist", service.CountListProcess());	
		mav.setViewName("boardview");
		return mav;
	}	
	@RequestMapping(value="/board2")	
	public ModelAndView goboard2() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("messagelist2", service.MessageListProcess2());
		mav.addObject("countlist", service.CountListProcess2());	
		mav.setViewName("boardview");
		return mav;
	}
	@RequestMapping(value="/board3")	
	public ModelAndView goboard3() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("messagelist3", service.MessageListProcess3());
		mav.addObject("countlist", service.CountListProcess3());	
		mav.setViewName("boardview");
		return mav;
	}
	@RequestMapping(value="/board4")	
	public ModelAndView goboard4() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("messagelist4", service.MessageListProcess4());
		mav.addObject("countlist", service.CountListProcess4());		
		mav.setViewName("boardview");
		return mav;
	}
	
	@RequestMapping(value="/read")	
	public ModelAndView goread() throws Exception {
		ModelAndView mav = new ModelAndView();
		//mav.addObject("messagelist4", service.MessageListProcess4());
		mav.setViewName("readview");
		return mav;
	}
}

