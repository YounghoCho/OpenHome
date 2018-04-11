/*
 * Application java
 * @Author : Youngho Jo
 *           Suji    Jang
 */
package com.worksmobile.openhome.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.openhome.model.File_uploadDTO;
import com.worksmobile.openhome.model.Message;
import com.worksmobile.openhome.model.Traffic;

@Repository("BoardDAO")
public class BoardDAOImp implements BoardDAO{
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public BoardDAOImp(){}
	
	/*Board*/
	@Override
	public List<Message> getArticleList(int boardNumberInt, int currentPageNo, int pageSize) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("boardNumberInt", boardNumberInt);
		paramMap.put("startNum", currentPageNo);
		paramMap.put("pageSize", pageSize);
		
		return sqlsession.selectList("board.getArticleList", paramMap);
	}
	@Override
	public List<Message> getCount(int boardNumberInt) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("boardNumberInt", boardNumberInt);
	
		return sqlsession.selectList("board.getCount", paramMap);
	}
	@Override
	public List<Message> getArticleDetails(int originalMessageNum) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("originalMessageNum", originalMessageNum);
	
		return sqlsession.selectList("board.getArticleDetails", paramMap);
	}

	/*Traffic*/
	@Override
	public List<Traffic> funcGetTraffic (){
		return sqlsession.selectList("board.getTraffic");
	}
	@Override
	public List<Traffic> funcGetTrafficCount () {
		return sqlsession.selectList("board.getTrafficCount");
	}
	

	//게시글 추가
	@Override
	public int message_insert(Message dto) {
		int num = sqlsession.insert("board.message_insert", dto);
		return num;
	}

	@Override
	public void file_insert(List<File_uploadDTO> fList) {
		sqlsession.insert("board.file_insert", fList);	
	}
}
