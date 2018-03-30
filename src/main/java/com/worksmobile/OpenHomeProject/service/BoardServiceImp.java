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
		System.out.println("service통과 home");
		return dao.board_list();
	}
	
	@Override
	public List<BoardDTO> MessageListProcess() {
		System.out.println("service통과1");
		return dao.message_list();
	}
	@Override
	public List<BoardDTO> MessageListProcess2() {
		System.out.println("service통과2");
		return dao.message_list2();
	}
	@Override
	public List<BoardDTO> MessageListProcess3() {
		System.out.println("service통과3");
		return dao.message_list3();
	}
	@Override
	public List<BoardDTO> MessageListProcess4() {
		System.out.println("service통과3");
		return dao.message_list4();
	}

	@Override
	public List<BoardDTO> CountListProcess() {
		return dao.count_list();
	}

	@Override
	public List<BoardDTO> CountListProcess2() {
		return dao.count_list2();
	}

	@Override
	public List<BoardDTO> CountListProcess3() {
		return dao.count_list3();
	}

	@Override
	public List<BoardDTO> CountListProcess4() {
		return dao.count_list4();
	}

}

