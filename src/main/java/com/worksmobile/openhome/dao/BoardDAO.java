package com.worksmobile.openhome.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.openhome.model.Board;


@Repository("BoardDAO")
public class BoardDAO {
	private static final String NAMESPACE_BOARD = "board.";
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public BoardDAO() {}
	
	/*Board*/
	public List<Board> getMenuList() {
		return sqlsession.selectList(NAMESPACE_BOARD + "getMenuList");
	}
	
	public void removeBoard(int boardNum) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("boardNum", boardNum);
		sqlsession.delete(NAMESPACE_BOARD + "removeBoard", paramMap);
	}
	
	
	


}
