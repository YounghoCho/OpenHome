package com.worksmobile.openhome.bo;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.ArticleDAO;
import com.worksmobile.openhome.model.Article;
import com.worksmobile.openhome.model.Board;

@Service("ArticleBO")
public class ArticleBOImpl implements ArticleBO{

	@Resource(name="ArticleDAO")
	private ArticleDAO dao;
	
/*	@Autowired
	BCryptPasswordEncoder passwordEncoder;*/

	@Override
	public List<Board> getBoardList() {
		return dao.getBoardList();
	}
	
	/*@author Suji Jang*/
	@Override
	public List<Article> searchArticle(Map<String, String> map) {
		System.out.println("bo");
		return dao.searchArticle(map);
	}

	@Override
	public void addArticleNum(Article article) {
		dao.addArticleNum(article);
	}
	
	@Override
	public String addArticle(Article article) {
		
		/*article.setArticleAccessPwd(passwordEncoderarticle.getArticleAccessPwd());*/
		
		int num =  dao.addArticle(article);
		if (num == 1) {
			return "success";
		} else {
			return "fail";
		}
	}
		
	//비번체크된 게시글 삭제
	@Override
	public String delCheckedArticle(int articleNum, String articleAccessPwd) {
		String accessPwd = dao.getArticleAccessPwd(articleNum).getArticleAccessPwd();
		if (accessPwd.equals(articleAccessPwd)) {
			if(dao.removeOwnArticle(articleNum) == 1 ) {
				return "success";
			} else {
				return "fail";
			}
		} else {
			return "not equal";
		}
	}
	
	//비번체크 된 수정할 게시글 가져오기
	@Override
	public Article getCheckedArticle(int articleNum, String articleAccessPwd) {
		String accessPwd = dao.getArticleAccessPwd(articleNum).getArticleAccessPwd();
		if (accessPwd.equals(articleAccessPwd)) {
				return dao.getArticle(articleNum);
		} else {
			Article article = new Article();
			article.setArticleAccessPwd("not equal");
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
		return dao.getAllArticles(currentPageNo, pageSize);
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
