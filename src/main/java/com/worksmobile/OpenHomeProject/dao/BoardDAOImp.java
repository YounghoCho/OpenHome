package com.worksmobile.OpenHomeProject.dao;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.OpenHomeProject.dto.BoardDTO;

@Repository("BoardDAO")
public class BoardDAOImp implements BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public BoardDAOImp() {
		
	}

	@Override
	public List<BoardDTO> board_list() throws Exception {
		System.out.println("dao통과 home");
		return sqlsession.selectList("board.board_list");
	}

	@Override
	public List<BoardDTO> message_list() {
		System.out.println("dao통과1");
		return sqlsession.selectList("board.message_list");
	}
	@Override
	public List<BoardDTO> message_list2() {
		System.out.println("dao통과2");
		return sqlsession.selectList("board.message_list2");
	}
	@Override
	public List<BoardDTO> message_list3() {
		System.out.println("dao통과3");
		return sqlsession.selectList("board.message_list3");
	}
	@Override
	public List<BoardDTO> message_list4() {
		System.out.println("dao통과4");
		return sqlsession.selectList("board.message_list4");
	}

	
	@Override
	public List<BoardDTO> count_list() {
		return sqlsession.selectList("board.count_list");
	}

	@Override
	public List<BoardDTO> count_list2() {
		return sqlsession.selectList("board.count_list2");
	}

	@Override
	public List<BoardDTO> count_list3() {
		return sqlsession.selectList("board.count_list3");
	}

	@Override
	public List<BoardDTO> count_list4() {
		return sqlsession.selectList("board.count_list4");
	}

}
