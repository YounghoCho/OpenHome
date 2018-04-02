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
	public List<BoardDTO> MessageList(int boardNumberInt, int currentPageNo) {
		return dao.funcMessagelist(boardNumberInt, currentPageNo);
	}

	@Override
	public List<BoardDTO> CountList(int boardNumberInt) {
		return dao.funcCountList(boardNumberInt);
	}
}

