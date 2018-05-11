package com.worksmobile.openhome.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.openhome.model.ArticleComment;

@Repository("ArticleCommentDAO")
public class ArticleCommentDAO {
	
	private static final String NAMESPACE_ARTICLECOMMENT = "articleComment.";
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public ArticleCommentDAO() {}
	
	public List<ArticleComment> getComments(int articleNum) {
		return sqlsession.selectList(NAMESPACE_ARTICLECOMMENT + "getComments", articleNum);
	}
	
	public int getCommentCount(int articleNum) {
		return sqlsession.selectOne(NAMESPACE_ARTICLECOMMENT + "getCommentCount", articleNum);
	}

}
