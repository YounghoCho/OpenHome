/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.controller;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	//(board)
	@RequestMapping(value = "/board")	
	public ModelAndView goBoard() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	//(board)
	@RequestMapping(value = "/admin")
	public ModelAndView goadmin() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin");
		return mav;
	}
}