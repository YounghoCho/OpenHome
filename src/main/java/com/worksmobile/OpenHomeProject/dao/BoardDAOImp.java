package com.worksmobile.OpenHomeProject.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.OpenHomeProject.dto.BoardDTO;
import com.worksmobile.OpenHomeProject.dto.TrafficDTO;

@Repository("BoardDAO")
public class BoardDAOImp implements BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public BoardDAOImp() {}
	
	/*Board*/
	@Override
	public List<BoardDTO> funcMessagelist (int boardNumberInt, int currentPageNo, int pageSize) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put ("boardNumberInt", boardNumberInt);
		paramMap.put ("startNum", currentPageNo);
		paramMap.put ("pageSize", pageSize);
		
		return sqlsession.selectList ("board.mapMessagelist", paramMap);
	}
	@Override
	public List<BoardDTO> funcCountList (int boardNumberInt) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put ("boardNumberInt", boardNumberInt);
	
		return sqlsession.selectList ("board.count_list", paramMap);
	}
	@Override
	public List<BoardDTO> funcOriginalMessage (int originalMessageNum) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put ("originalMessageNum", originalMessageNum);
	
		return sqlsession.selectList ("board.messageInfo", paramMap);
	}

	/*Traffic*/
	@Override
	public List<TrafficDTO> funcGetTraffic (){
		return sqlsession.selectList ("board.getTraffic");
	}
	@Override
	public List<TrafficDTO> funcGetTrafficCount () {
		return sqlsession.selectList ("board.getTrafficCount");
	}
}
