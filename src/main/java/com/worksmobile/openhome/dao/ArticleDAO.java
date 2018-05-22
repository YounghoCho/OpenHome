package com.worksmobile.openhome.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.openhome.model.Article;
import com.worksmobile.openhome.model.Board;
import com.worksmobile.openhome.model.SearchData;

@Repository("ArticleDAO")
public class ArticleDAO {

	private static final String NAMESPACE_ARTICLE = "article.";

	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public ArticleDAO() {}
	
	public List<Board> getBoardList() {
		return sqlsession.selectList(NAMESPACE_ARTICLE + "getBoardList");
	}
	
	/*@ author Suji Jang*/
	public int addArticle(Article article) {
		int num = sqlsession.update(NAMESPACE_ARTICLE + "addArticle", article);
		return num;
	}
	
	public int addArticleNum(Article article) {
		return sqlsession.insert(NAMESPACE_ARTICLE + "addArticleNum", article);
	}
	
	public Article getArticleAccessPwd(int articleNum) {
		return sqlsession.selectOne(NAMESPACE_ARTICLE + "getArticle", articleNum);
	}
	
	public int removeOwnArticle(int articleNum) {
		return sqlsession.delete(NAMESPACE_ARTICLE + "removeArticle", articleNum);
	}
	
	public Article getArticle(int articleNum) {
		return sqlsession.selectOne(NAMESPACE_ARTICLE + "getArticle", articleNum);
	}
	
	public int modArticle(Article article) {
		return sqlsession.update(NAMESPACE_ARTICLE + "modArticle", article);
	}
	
	public List<Article> searchArticle(SearchData searchdata) {
		return sqlsession.selectList(NAMESPACE_ARTICLE + "searchArticle", searchdata);
	}
	
	/*@ author Youngho Jo*/
	public List<Article> getArticleList(int boardNumber, int currentPageNo, int pageSize) {
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
	//admin
	public List<Article> getAllArticles(int currentPageNo, int pageSize) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("startNum", currentPageNo-1);
		paramMap.put("pageSize", pageSize);
		return sqlsession.selectList(NAMESPACE_ARTICLE + "getArticleListAdmin", paramMap);
	}
	public int getArticleTotalCount() {
		return sqlsession.selectOne(NAMESPACE_ARTICLE + "getArticleTotalCountAdmin");
	}
	
	public List<Article> getArticleDetails(int articleNumber) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("articleNumber", articleNumber);
	
		return sqlsession.selectList(NAMESPACE_ARTICLE + "getArticleDetails", paramMap);
	}
	
	public void removeArticle(int articleNum) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("articleNum", articleNum);
		sqlsession.delete(NAMESPACE_ARTICLE + "removeArticle", paramMap);
	}
	
	public void updateArticleCount(int articleNum) {
		sqlsession.update(NAMESPACE_ARTICLE + "updateArticleCount", articleNum);
	}

}
