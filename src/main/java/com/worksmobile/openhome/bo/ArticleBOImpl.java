package com.worksmobile.openhome.bo;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.worksmobile.openhome.dao.ArticleDAO;
import com.worksmobile.openhome.model.Article;

@Service("ArticleBO")
public class ArticleBOImpl implements ArticleBO{

	@Resource(name="ArticleDAO")
	private ArticleDAO dao;

	/*@author Suji Jang*/
	@Override
	public void addArticleNum(Article article) {
		dao.addArticleNum(article);
	}
	
	@Override
	public String addArticle(Article article) {
		int num =  dao.addArticle(article);
		if (num == 1) {
			return "ok";
		} else {
			return "sorry";
		}
	}

	/*Article*/
	@Override
	public List<Article> getArticleList(int boardNumber, int currentPageNo, int pageSize) {
		return dao.getArticleList(boardNumber, currentPageNo, pageSize);
	}
	@Override
	public int getArticleTotalCount(int boardNumber) {
		return dao.getArticleTotalCount(boardNumber);
	}
	@Override
	public List<Article> getArticleDetails(int articleNumber) {
		return dao.getArticleDetails(articleNumber);
	}
	@Override
	public List<Article> getAllArticles() {
		return dao.getAllArticles();
	}
	@Override
	public void removeArticle(int articleNum) {
		dao.removeArticle(articleNum);
	}	
}
