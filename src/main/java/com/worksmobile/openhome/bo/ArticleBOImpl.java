package com.worksmobile.openhome.bo;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.nhncorp.lucy.security.xss.XssFilter;
import com.nhncorp.lucy.security.xss.XssPreventer;
import com.worksmobile.openhome.dao.ArticleDAO;
import com.worksmobile.openhome.dao.AttachmentFileDAO;
import com.worksmobile.openhome.model.Article;
import com.worksmobile.openhome.model.AttachmentFile;
import com.worksmobile.openhome.model.Board;
import com.worksmobile.openhome.model.SearchData;

@Commit
@Service("ArticleBO")
public class ArticleBOImpl implements ArticleBO{

	@Resource(name="ArticleDAO")
	private ArticleDAO dao;
	
	@Resource(name="AttachmentFileDAO")
	private AttachmentFileDAO attachmentfiledao;
	
	private static final String FILE_PATH = "C:\\Users\\USER\\eclipse-workspace\\OpenHome\\src\\main\\webapp\\file\\";
	
	@Autowired
	PasswordEncoder pwEncoder;

	@Override
	public List<Board> getBoardList() {
		return dao.getBoardList();
	}
	
	/*@author Suji Jang*/
	@Override
	public List<Article> searchArticle(SearchData searchdata) {
		return dao.searchArticle(searchdata);
	}

	@Override
	public void addArticleNum(Article article) {
		dao.addArticleNum(article);
	}
	
	@Override
	public String addArticle(Article article) {
		//비밀번호 인코딩
		article.setArticleAccessPwd(pwEncoder.encode(article.getArticleAccessPwd()));

		article.setArticleTextContent(XssPreventer.escape(article.getArticleTextContent()));
		article.setArticleWriter(XssPreventer.escape(article.getArticleWriter()));
		article.setArticleSubject(XssPreventer.escape(article.getArticleSubject()));

		int num =  dao.addArticle(article);
		if (num == 1) {
			return "success";
		} else {
			return "fail";
		}
	}
		
	//비번체크된 게시글 삭제
	@Transactional
	@Override
	public String delCheckedArticle(int articleNum, String articleAccessPwd) {

		if (pwEncoder.matches(articleAccessPwd, dao.getArticleAccessPwd(articleNum).getArticleAccessPwd())) {
		
			List<AttachmentFile> attachmentfileList = attachmentfiledao.getFiles(articleNum);
			if (attachmentfileList != null) {
				for(AttachmentFile attachmentfile : attachmentfileList) {
					delUploadedFile(attachmentfile.getStoredFileName());
				}
				if(dao.removeOwnArticle(articleNum) == 1 ) {
					return "SUCCESS";
				} else {
					return "Fail";
				}
			} else {
				if(dao.removeOwnArticle(articleNum) == 1 ) {
					return "SUCCESS";
				} else {
					return "Fail";
				}
			}
		} else {
			return "NOT EQAL";
		}
	}
	
	private boolean delUploadedFile(String storedFileName) {
		File file = new File(FILE_PATH, storedFileName);
		return file.delete();
	}
	
	//비번체크 된 수정할 게시글 가져오기
	@Override
	public Article getCheckedArticle(int articleNum, String articleAccessPwd) {
		
		if (pwEncoder.matches(articleAccessPwd, dao.getArticleAccessPwd(articleNum).getArticleAccessPwd())) {
				return dao.getArticle(articleNum);
		} else {
			Article article = new Article();
			article.setArticleAccessPwd("not equal");
			return article;
		}
	}
	@Override
	public String modArticle(Article article) {

		article.setArticleTextContent(XssPreventer.escape(article.getArticleTextContent()));
		article.setArticleWriter(XssPreventer.escape(article.getArticleWriter()));
		article.setArticleSubject(XssPreventer.escape(article.getArticleSubject()));
		
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
	
	@Override
	public void updateArticleCount(int articleNum) {
		dao.updateArticleCount(articleNum);
	}
	
}