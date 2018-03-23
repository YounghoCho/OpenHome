package com.worksmobile.OpenHomeProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FruitsController {
	
	@GetMapping(value="/index")
	public String index() {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("hello");
//		return mav;
		return "hello";
	}
}