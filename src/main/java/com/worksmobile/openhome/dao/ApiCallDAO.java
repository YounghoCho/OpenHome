package com.worksmobile.openhome.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ApiCallDAO")
public class ApiCallDAO {
private static final String NAMESPACE_API = "apiCall.";
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	public ApiCallDAO() {}
 	/*
 	 * [API 호출 트래픽 Level 5 Steps]
 	 * Level 1 : 게시글 목록 호출 (article_list)
 	 * Level 2 : 게시글 내용 호출 (article_detail)
 	 * Level 3 : 게시글 쓰기 호출 (article_write)
 	 * Level 4 : 파일 업로드 호출 (file_upload)
 	 * Level 5 : 파일 다운로드 호출 (file_download)
 	 */	
	public void insertApiCallLevel1() {
		sqlsession.insert(NAMESPACE_API + "insertApiCallLevel1");	
	}
	public void insertApiCallLevel2() {
		sqlsession.insert(NAMESPACE_API + "insertApiCallLevel2");	
	}
	public void insertApiCallLevel3() {
		sqlsession.insert(NAMESPACE_API + "insertApiCallLevel3");	
	}
	public void insertApiCallLevel4() {
		sqlsession.insert(NAMESPACE_API + "insertApiCallLevel4");	
	}
	public void insertApiCallLevel5() {
		sqlsession.insert(NAMESPACE_API + "insertApiCallLevel5");	
	}
}
