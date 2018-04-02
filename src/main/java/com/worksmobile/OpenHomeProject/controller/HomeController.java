package com.worksmobile.OpenHomeProject.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.worksmobile.OpenHomeProject.service.BoardService;

@Controller
public class HomeController {
	
	@Resource(name="BoardService")
	private BoardService service;

	@RequestMapping(value="/home")
	public ModelAndView gohome() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("messagelist", service.MessageList(1,1));
		mav.addObject("messagelist2", service.MessageList(2,1));
		mav.addObject("messagelist3", service.MessageList(3,1));
		mav.addObject("messagelist4", service.MessageList(4,1));				
		mav.setViewName("homeview");
		return mav;	
	}

	@RequestMapping(value="/{board}")	
	public ModelAndView goboard4(HttpServletRequest req, @PathVariable("board") String board) throws Exception {
		//### Get Board Number ###
		String boardNumberString= board.substring(5,6);
			//System.out.println("board : "+ board);
		int boardNumberInt= Integer.parseInt(boardNumberString);
			//System.out.println("1: "+ boardNumberString + "\n2: "+ boardNumberInt);

		//### Get Page Number ###
		int currentPageNo= 1;
		if(req.getParameter("pages") !=null)
			currentPageNo= Integer.parseInt(req.getParameter("pages"));
			//System.out.println("req.getParameter(\"pages\") : " + req.getParameter("pages"));
			//System.out.println("currentPAgeNo : " + currentPageNo);

		//### Get DB Result ###
		ModelAndView mav = new ModelAndView();
		mav.addObject("messagelist", service.MessageList(boardNumberInt, currentPageNo));
		mav.addObject("countlist", service.CountList(boardNumberInt));	
		mav.setViewName("boardview");
		return mav;
	}

	@RequestMapping(value="/read")	
	public ModelAndView goread() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("readview");
		return mav;
	}
}

