package com.worksmobile.openhome.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.model.Article;


@Service
public interface ArticleBO {
	
	/*@ author Suji Jang*/
	public int addArticle(Article article);
	public void addArticleNum();

	/*@ author Youngho Jo*/
	public List<Article> getArticleList(int boardNumber, int currentPageNo, int pageSize);
	public int getArticleTotalCount(int boardNumber);
	public List<Article> getArticleDetails(int articleNumber);
	public List<Article> getAllArticles();
	
	public void removeArticle(int articleNum);
	
}
