package com.worksmobile.OpenHomeProject.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.OpenHomeProject.dto.BoardDTO;

@Repository("BoardDAO")
public class BoardDAOImp implements BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public BoardDAOImp() {}
	
	@Override
	public List<BoardDTO> funcMessagelist(int boardNumberInt, int currentPageNo) {
		// TODO Auto-generated method stub
		HashMap<String, Object> paramMap= new HashMap<>();
		paramMap.put("boardNumberInt", boardNumberInt);
		paramMap.put("startNum", currentPageNo);
		paramMap.put("pageSize", currentPageNo+9);
		return sqlsession.selectList("board.mapMessagelist", paramMap);
	}

	@Override
	public List<BoardDTO> funcCountList(int boardNumberInt) {
		return sqlsession.selectList("board.count_list");
	}
}
