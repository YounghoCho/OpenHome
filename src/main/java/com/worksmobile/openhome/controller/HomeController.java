/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/board")	
	public ModelAndView goBoard() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}

	@RequestMapping(value = "/admin")
	public ModelAndView goadmin() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin");
		return mav;
	}
}