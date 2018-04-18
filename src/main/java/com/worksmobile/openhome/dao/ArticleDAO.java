package com.worksmobile.openhome.dao;
import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.openhome.model.Article;

@Repository("ArticleDAO")
public class ArticleDAO {

	private static final String NAMESPACE_ARTICLE = "article.";

	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public ArticleDAO() {}
	
	/*@ author Suji Jang*/
	public int addArticle(Article article) {
		int num = sqlsession.update(NAMESPACE_ARTICLE + "addArticle", article);
		return num;
	}
	
	public void addArticleNum(Article article) {
		sqlsession.insert(NAMESPACE_ARTICLE + "addArticleNum", article);
	}
	
	/*@ author Youngho Jo*/
	public List<Article> getArticleList(int boardNumber, int currentPageNo, int pageSize) {
		HashMap<String, Object> paramMap = new HashMap<>();
		System.out.println(boardNumber + " " + currentPageNo + " " + pageSize);
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
	
	public List<Article> getArticleDetails(int articleNumber) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("articleNumber", articleNumber);
	
		return sqlsession.selectList(NAMESPACE_ARTICLE + "getArticleDetails", paramMap);
	}
	
	public List<Article> getAllArticles() {
		return sqlsession.selectList(NAMESPACE_ARTICLE + "getAllArticles");
	}
	
	public void removeArticle(int articleNum) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("articleNum", articleNum);
		sqlsession.delete(NAMESPACE_ARTICLE + "removeArticle", paramMap);
	}
	
}
