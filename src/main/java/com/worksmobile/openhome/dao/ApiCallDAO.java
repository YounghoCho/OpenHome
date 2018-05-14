package com.worksmobile.openhome.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.openhome.model.ApiCall;

@Repository("ApiCallDAO")
public class ApiCallDAO {
private static final String NAMESPACE_API = "apiCall.";
	
	@Autowired
	private SqlSessionTemplate sqlsession;
 	/*
 	 * [API 호출 트래픽 Level 5 Steps]
 	 * Level 1 : 게시글 목록 호출 (article_list)
 	 * Level 2 : 게시글 내용 호출 (article_detail)
 	 * Level 3 : 게시글 쓰기 호출 (article_write)
 	 * Level 4 : 파일 업로드 호출 (file_upload)
 	 * Level 5 : 파일 다운로드 호출 (file_download)
 	 */	
	public void insertApiCall(String apiType) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("apiType", apiType);
		sqlsession.insert(NAMESPACE_API + "insertApiCall", paramMap);	
	}
	public List<ApiCall> getTotalApiList() {
		return sqlsession.selectList(NAMESPACE_API + "getTotalApiList");
	}
}
