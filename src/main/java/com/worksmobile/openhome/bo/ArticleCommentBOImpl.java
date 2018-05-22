package com.worksmobile.openhome.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nhncorp.lucy.security.xss.XssFilter;
import com.nhncorp.lucy.security.xss.XssPreventer;
import com.worksmobile.openhome.dao.ArticleCommentDAO;
import com.worksmobile.openhome.dao.ArticleDAO;
import com.worksmobile.openhome.model.ArticleComment;
import com.worksmobile.openhome.model.AttachmentFile;

@Commit
@Service("ArticleCommentBO")
public class ArticleCommentBOImpl implements ArticleCommentBO {

	@Resource(name="ArticleCommentDAO")
	private ArticleCommentDAO dao;
	
	@Resource(name="ArticleDAO")
	private ArticleDAO articledao;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	private static final String FILE_PATH = "C:\\Users\\USER\\eclipse-workspace\\OpenHome\\src\\main\\webapp\\commentfile\\";

	@Override
	public List<ArticleComment> getComments(int articleNum) {
		return dao.getComments(articleNum);
	}

	@Override
	public int getCommentCount(int articleNum) {
		return dao.getCommentCount(articleNum);
	}

	@Override
	public String addComment(ArticleComment articlecomment) {
		articlecomment.setCommentAccessPwd(pwEncoder.encode(articlecomment.getCommentAccessPwd()));
		articlecomment.setCommentWriter(XssPreventer.escape(articlecomment.getCommentWriter()));
		articlecomment.setCommentContent(XssPreventer.escape(articlecomment.getCommentContent()));
		if (dao.addComment(articlecomment) == 1) {
			articledao.increaseArticleCommentCount(articlecomment.getArticleNum());
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	@Override
	public String chkCommentPwd(int commentNum, String commentAccessPwd) {
		if (pwEncoder.matches(commentAccessPwd, dao.getCommentPwd(commentNum).getCommentAccessPwd())) {
				return "SUCCESS";
			} else {
				return "FAIL";
			}
	}

	@Override
	public String modComment(ArticleComment articlecomment) {
		articlecomment.setCommentWriter(XssPreventer.escape(articlecomment.getCommentWriter()));
		articlecomment.setCommentContent(XssPreventer.escape(articlecomment.getCommentContent()));
		if ((dao.modComment(articlecomment)) == 1) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	@Transactional
	@Override
	public String chkAndDelComment(int commentNum, String commentAccessPwd, String commentStoredName, int articleNum) {
		if (pwEncoder.matches(commentAccessPwd, dao.getCommentPwd(commentNum).getCommentAccessPwd())) {
			if (commentStoredName.equals(null)) {
				if (delUploadedFile(commentStoredName) == true) {
					if (dao.delComment(commentNum) == 1) {
						articledao.decreaseArticleCommentCount(articleNum);
						return "SUCCESS";
					} else {
						return "FAIL_DEL";
					}
				} else {
					return "FAIL_DEL";
				}
			} else {
					if (dao.delComment(commentNum) == 1) {
						articledao.decreaseArticleCommentCount(articleNum);
						return "SUCCESS";
					} else {
						return "FAIL_DEL";
					}
			}
		} else {
			return "FAIL";
		}
	}

	@Override
	public ArticleComment uploadFile(MultipartFile file) {
		return uploadCommentFile(file);
	}
	
	private ArticleComment uploadCommentFile(MultipartFile file) {
		
		File dir = new File(FILE_PATH);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		  
		String originalFileName = file.getOriginalFilename();
		
		int fileSize = (int)file.getSize();
		
		//파일 중복 방지
		String storedFileName = System.currentTimeMillis() + UUID.randomUUID().toString()
				+ "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		
		try {
			file.transferTo(new File(FILE_PATH + storedFileName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			ArticleComment notUploadArticleCommentFile = new ArticleComment(originalFileName, storedFileName, fileSize, "N");
			return notUploadArticleCommentFile;
		}
		
		ArticleComment uploadArticleCommentFile = new ArticleComment(originalFileName, storedFileName, fileSize, "Y");
		return uploadArticleCommentFile;
	}

	@Override
	public boolean delUploadedFile(String storedFileName) {
		File file = new File(FILE_PATH, storedFileName);
		return file.delete();
	}
	
	@Override
	public void downloadCommentFile(String storedFileName, String originalFileName, HttpServletResponse response) {
		 try {
		        File file = new File(FILE_PATH, storedFileName);
		        InputStream inputStream = new FileInputStream(file);
		        response.setContentType("application/force-download");
		        response.setHeader("Content-Disposition", "attachment; filename=" + originalFileName); 
		        IOUtils.copy(inputStream, response.getOutputStream());
		        response.flushBuffer();
		        inputStream.close();
		    } catch (Exception e){
		        e.printStackTrace();
		    }
	}
}