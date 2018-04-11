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
	public List<Message> getArticleList(int boardNumber, int currentPageNo, int pageSize) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("boardNumber", boardNumber);
		paramMap.put("startNum", currentPageNo-1);	//currentPageNo is 1(default) but LIMIT(in mapper.xml) must start from 0
		paramMap.put("pageSize", pageSize);
		return sqlsession.selectList("board.getArticleList", paramMap);
	}
	@Override
	public int getBoardTotalCount(int boardNumber) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("boardNumber", boardNumber);
		return sqlsession.selectOne("board.getBoardTotalCount", paramMap);
	}
	@Override
	public List<Message> getArticleDetails(int articleNumber) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("articleNumber", articleNumber);
	
		return sqlsession.selectList("board.getArticleDetails", paramMap);
	}

	/*Traffic*/
	@Override
	public List<Traffic> getTrafficData (){
		return sqlsession.selectList("board.getTrafficData");
	}
	@Override
	public int getTrafficCount () {
		return sqlsession.selectOne("board.getTrafficCount");
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
