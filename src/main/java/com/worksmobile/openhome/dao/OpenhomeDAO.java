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

@Repository("OpenhomeDAO")
public class OpenhomeDAO{
	private static final String NAMESPACE_BOARD = "board.";
	private static final String NAMESPACE_ARTICLE = "article.";
	private static final String NAMESPACE_TRAFFIC = "traffic.";
	private static final String NAMESPACE_ATTACHMENTFILE = "attchmentFile.";
	
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	/*Board*/
	public List<Message> getArticleList(int boardNumber, int currentPageNo, int pageSize) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("boardNumber", boardNumber);
		paramMap.put("startNum", currentPageNo-1);	//currentPageNo is 1(default) but LIMIT(in mapper.xml) must start from 0
		paramMap.put("pageSize", pageSize);
		return sqlsession.selectList(NAMESPACE_ARTICLE + "getArticleList", paramMap);
	}
	public int getArticleTotalCount(int boardNumber) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("boardNumber", boardNumber);
		return sqlsession.selectOne(NAMESPACE_ARTICLE + "getArticleTotalCount", paramMap);
	}
	public List<Message> getArticleDetails(int articleNumber) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("articleNumber", articleNumber);
	
		return sqlsession.selectList(NAMESPACE_ARTICLE + "getArticleDetails", paramMap);
	}

	/*Traffic*/
	public List<Traffic> getTrafficData (){
		return sqlsession.selectList(NAMESPACE_TRAFFIC + "getTrafficData");
	}
	public int getTrafficCount () {
		return sqlsession.selectOne(NAMESPACE_TRAFFIC + "getTrafficCount");
	}
	

	//게시글 추가
	public int message_insert(Message dto) {
		int num = sqlsession.insert(NAMESPACE_ARTICLE + "message_insert", dto);
		return num;
	}
	public void file_insert(List<File_uploadDTO> fList) {
		sqlsession.insert("board.file_insert", fList);	
	}
}
