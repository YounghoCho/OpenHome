/*
 * Application java
 * @Author : Youngho Jo
 *           Suji    Jang
 */
package com.worksmobile.openhome.controller;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.worksmobile.openhome.BO.OpenhomeBO;
import com.worksmobile.openhome.model.File_uploadDTO;
import com.worksmobile.openhome.model.Message;

@Controller
public class HomeController {
	
	@Resource(name = "BoardService")
	private OpenhomeBO service;

	@RequestMapping(value = "/board")	
	public ModelAndView goBoard() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping(value = "/admin")
	public ModelAndView goAdmin() throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin");
		return mav;
	}
	
	//@author suji
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public ModelAndView goWrite(int board_num) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("body-message");
		return mav;
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String goWritePost(Message mdto, MultipartHttpServletRequest multi) throws Exception { 
	
		//--첨부파일 등록--
		//경로 설정
		String root = multi.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root + "img" + File.separator +"file_upload" + File.separator;
		
		//파일 디렉터리 생성
		File dir = new File(saveDirectory);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		//새이름 선언
		String newFileName; 
		
		//게시글 등록
		service.MessageInsertProcess(mdto);
		List<File_uploadDTO> fList = new ArrayList<File_uploadDTO>();
		Iterator<String> files = multi.getFileNames();
		File_uploadDTO fdto;
		
		//파일 저장 및 map리스트 생성
		while (files.hasNext()) {
			
			String uploadFile = files.next();
			MultipartFile mFile = multi.getFile(uploadFile);
			String fileName = mFile.getOriginalFilename();

			//파일 중복 방지
			newFileName = System.currentTimeMillis() + UUID.randomUUID().toString()
					+ "." + fileName.substring(fileName.lastIndexOf(".")+1);
			
			//파일 저장하기
			mFile.transferTo(new File(saveDirectory+newFileName));
			
			//dto 생성
			fdto = new File_uploadDTO();
			fdto.setMessage_num(mdto.getArticleNum());
			fdto.setOriginal_file_name(fileName);
			fdto.setStored_file_name(newFileName);
			fdto.setFile_size((int)mFile.getSize());
			fdto.setFile_creater(mdto.getArticleWriter());
			fList.add(fdto);
		}
		
		if (fList.size() != 0 ) {
			//첨부파일 데이터베이스에 등록
			service.FileInsertProcess(fList);
		}
		return "success";
	}
	
	
}