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
	
	public int addComment(ArticleComment articlecomment) {
		return sqlsession.insert(NAMESPACE_ARTICLECOMMENT + "addComment", articlecomment);
	}
	
	public ArticleComment getCommentPwd(int commentNum) {
		return sqlsession.selectOne(NAMESPACE_ARTICLECOMMENT + "getCommentPwd", commentNum);
	}
	
	public int modComment(ArticleComment articlecomment) {
		return sqlsession.update(NAMESPACE_ARTICLECOMMENT + "modComment", articlecomment);
	}
	
	public int delComment(int commentNum) {
		return sqlsession.update(NAMESPACE_ARTICLECOMMENT + "delComment", commentNum);
	}

}
