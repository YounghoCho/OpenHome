package com.worksmobile.openhome.bo;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.worksmobile.openhome.model.ArticleComment;

@Service
public interface ArticleCommentBO {
	public List<ArticleComment> getComments(int articleNum);
	public int getCommentCount(int articleNum);
	public String addComment(ArticleComment articlecomment);
	public String chkCommentPwd(int commentNum, String commentAccessPwd);
	public String modComment(ArticleComment articlecomment);
	public String chkAndDelComment(int commentNum, String commentAccessPwd, String commentStoredName, int articleNum);
	public ArticleComment uploadFile(MultipartFile file);
	public boolean delUploadedFile(String storedFileName);
	public void downloadCommentFile(String storedFileName, String originalFileName, HttpServletResponse response);
}
