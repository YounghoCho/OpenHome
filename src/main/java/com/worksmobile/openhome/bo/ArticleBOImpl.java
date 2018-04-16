package com.worksmobile.openhome.bo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.ArticleDAO;
import com.worksmobile.openhome.model.Article;


@Service("ArticleBO")
public class ArticleBOImpl implements ArticleBO{

	@Resource(name="ArticleDAO")
	private ArticleDAO articledao;

	/*@author Suji Jang*/
	@Override
	public void addArticleNum() {
		articledao.addArticleNum();
	}
	
	@Override
	public int addArticle(Article article) {
		return articledao.addArticle(article);
	}
	

	/*@author Youngho Jo*/
	@Override
	public List<Article> getArticleList(int boardNumber, int currentPageNo, int pageSize) {
		return articledao.getArticleList(boardNumber, currentPageNo, pageSize);
	}
	@Override
	public int getArticleTotalCount(int boardNumber) {
		return articledao.getArticleTotalCount(boardNumber);
	}
	@Override
	public List<Article> getArticleDetails(int articleNumber) {
		return articledao.getArticleDetails(articleNumber);
	}
	
	@Override
	public List<Article> getAllArticles() {
		return articledao.getAllArticles();
	}
	@Override
	public void removeArticle(int articleNum) {
		articledao.removeArticle(articleNum);
	}



}
