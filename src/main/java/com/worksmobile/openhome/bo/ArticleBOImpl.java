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
			return "success";
		} else {
			return "fail";
		}
	}
	
	//비밀번호체크
	@Override
	public String checkPwd(int articleNum, String articleAccessPwd) {
		String accessPwd = dao.getArticleAccessPwd(articleNum).getArticleAccessPwd();
		if (accessPwd.equals(articleAccessPwd)) {
			return String.valueOf(articleNum);
		} else {
			return "fail";
		}
	}	
		
	//비번체크된 게시글 삭제
	@Override
	public String delCheckedArticle(String check) {
		if (check.equals("fail")) {
			return "fail";
		} else {
			if(dao.removeOwnArticle(Integer.parseInt(check)) == 1 ) {
				return "success";
			} else {
				return "fail";
			}
		}
	}
	
	//비번체크 된 수정할 게시글 가져오기
	@Override
	public Article getArticle(String check) {
		if(check != "fail") {
			return dao.getArticle(Integer.parseInt(check));
		} else {
			Article article = new Article();
			article.setArticleAccessPwd(check);
			return article;
		}
	}
	@Override
	public String modArticle(Article article) {
		int num =  dao.modArticle(article);
		if (num == 1) {
			return "success";
		} else {
			return "fail";
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
	//admin
	@Override
	public List<Article> getAllArticles(int currentPageNo, int pageSize) {
		return dao.getArticleList(currentPageNo, pageSize);
	}
	@Override
	public int getArticleTotalCount() {
		return dao.getArticleTotalCount();
	}
	
	@Override
	public List<Article> getArticleDetails(int articleNumber) {
		return dao.getArticleDetails(articleNumber);
	}

	@Override
	public void removeArticle(int articleNum) {
		dao.removeArticle(articleNum);
	}





	
}
