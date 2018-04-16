/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.model.Message;


@Service
public interface ArticleBO {
	
	public List<Message> getArticleList(int boardNumber, int currentPageNo, int pageSize);
	public int getArticleTotalCount(int boardNumber);
	public List<Message> getArticleDetails(int articleNumber);
	public List<Message> getAllArticles();
	public void removeArticle(int articleNum);
	
}
