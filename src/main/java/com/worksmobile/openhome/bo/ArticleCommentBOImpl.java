package com.worksmobile.openhome.bo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.ArticleCommentDAO;
import com.worksmobile.openhome.model.ArticleComment;

@Service("ArticleCommentBO")
public class ArticleCommentBOImpl implements ArticleCommentBO {

	@Resource(name="ArticleCommentDAO")
	private ArticleCommentDAO dao;

	@Override
	public List<ArticleComment> getComments(int articleNum) {
		return dao.getComments(articleNum);
	}

	@Override
	public int getCommentCount(int articleNum) {
		return dao.getCommentCount(articleNum);
	}

}
