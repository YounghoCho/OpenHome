package com.worksmobile.OpenHomeProject.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.OpenHomeProject.dao.BoardDAO;
import com.worksmobile.OpenHomeProject.dto.BoardDTO;

@Service("BoardService")
public class BoardServiceImp implements BoardService{
	
	@Resource(name="BoardDAO")
	private BoardDAO dao;

	@Override
	public List<BoardDTO> BoardListProcess() throws Exception {
		System.out.println("service���");
		return dao.board_list();
	}

}

