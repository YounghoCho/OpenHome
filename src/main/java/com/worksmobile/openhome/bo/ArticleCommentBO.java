package com.worksmobile.openhome.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.model.ArticleComment;

@Service
public interface ArticleCommentBO {

	public List<ArticleComment> getComments(int articleNum);
	public int getCommentCount(int articleNum);
}
