/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.bo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.ArticleDAO;
import com.worksmobile.openhome.model.Message;

@Service("ArticleBO")
public class ArticleBOImpl implements ArticleBO{

	@Resource(name="ArticleDAO")
	private ArticleDAO dao;
	
	/*Article*/
	@Override
	public List<Message> getArticleList(int boardNumber, int currentPageNo, int pageSize) {
		return dao.getArticleList(boardNumber, currentPageNo, pageSize);
	}
	@Override
	public int getArticleTotalCount(int boardNumber) {
		return dao.getArticleTotalCount(boardNumber);
	}
	@Override
	public List<Message> getArticleDetails(int articleNumber) {
		return dao.getArticleDetails(articleNumber);
	}
	@Override
	public List<Message> getAllArticles() {
		return dao.getAllArticles();
	}
	@Override
	public void removeArticle(int articleNum) {
		dao.removeArticle(articleNum);
	}	
}
