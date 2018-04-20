package com.worksmobile.openhome.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.model.Article;


@Service
public interface ArticleBO {
	
	/*@ author Suji Jang*/
	public String addArticle(Article article);
	public void addArticleNum(Article article);
	public String checkPwd(int articleNum, String articleAccessPwd);
	public String delCheckedArticle(String check);
	public Article getArticle(String check);

	/*@ author Youngho Jo*/
	public List<Article> getArticleList(int boardNumber, int currentPageNo, int pageSize);
	public int getArticleTotalCount(int boardNumber);
	public List<Article> getArticleDetails(int articleNumber);
	public List<Article> getAllArticles();
	
	public void removeArticle(int articleNum);
	
}
