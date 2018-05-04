package com.worksmobile.openhome.bo;

import java.util.List;
import java.util.Map;

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
	public String modArticle(Article article);
	public List<Article> searchArticle(Map<String, String> map);

	/*@ author Youngho Jo*/
	public List<Article> getArticleList(int boardNumber, int currentPageNo, int pageSize);
	public int getArticleTotalCount(int boardNumber);
	public List<Article> getArticleDetails(int articleNumber);
	public List<Article> getAllArticles();
	
	public void removeArticle(int articleNum);
	
}
