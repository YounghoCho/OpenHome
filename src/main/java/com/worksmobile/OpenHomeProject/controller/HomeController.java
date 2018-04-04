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
		mav.setViewName("homeview");
		return mav;	
	}

	@RequestMapping(value="/{board}")	
	public ModelAndView goboard(HttpServletRequest req, @PathVariable("board") String board) throws Exception {
		//### Get Board Number ###
		String boardNumberString= board.substring(5,6);
		int boardNumberInt= Integer.parseInt(boardNumberString);
		//### Get Page Number ###
		int currentPageNo= 1;
		if(req.getParameter("pages") !=null)
			currentPageNo= Integer.parseInt(req.getParameter("pages"));
		//### Get DB Result ###
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardNumberInt", boardNumberInt);
		mav.addObject("currentPageNo", currentPageNo);	
		mav.setViewName("boardview");
		return mav;
	}
	
	@RequestMapping(value="/read")	
	public ModelAndView goread(HttpServletRequest req) throws Exception {
		//읽기페이지에 들어올때 넘어오는 파라미터값을 함께 readview페이지로 넘깁니다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("message_num", Integer.parseInt(req.getParameter("message_num")));
		mav.setViewName("readview");
		return mav;
	}
}

