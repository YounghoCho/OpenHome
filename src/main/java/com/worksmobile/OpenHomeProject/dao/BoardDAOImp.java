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
		System.out.println("dao통과");
		return sqlsession.selectList("board.board_list");
	}

	@Override
	public List<BoardDTO> message_list() {
		System.out.println("dao통과");
		return sqlsession.selectList("board.message_list");
	}

}
