package com.worksmobile.openhome.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.dao.AttachmentFileDAO;
import com.worksmobile.openhome.model.AttachmentFile;

import lombok.extern.java.Log;

@Commit
@Service("AttachmentFileBO")
public class AttachmentFileBOImpl implements AttachmentFileBO{
	
	@Resource(name="AttachmentFileDAO")
	private AttachmentFileDAO dao;
	
	private static final String FILE_PATH = "C:\\Users\\USER\\eclipse-workspace\\OpenHome\\src\\main\\webapp\\file\\";

/*	@ author Suji Jang */

	@Override
	public List<AttachmentFile> getFiles(int articleNumber) {
		return dao.getFiles(articleNumber);
	}
	
	@Override
	public List<AttachmentFile> checkAndGetAttachmentFile(@RequestParam("articleNumber") int articleNumber, HttpServletRequest req) throws Exception { 
		List<AttachmentFile> attachmentfileList = dao.getFiles(articleNumber);
		if (attachmentfileList.size() !=0) {
			return attachmentfileList;
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public AttachmentFile uploadAndAddFile(int articleNum, MultipartFile file) {
		
		AttachmentFile attachmentfile = uploadFile(file);
		if (attachmentfile.getUploadStatus().equals("Y")) {
			attachmentfile.setArticleNum(articleNum);
			 if (dao.addFile(attachmentfile) == 1) {
				attachmentfile.setDatabaseStatus("Y");
				return attachmentfile;
			 } else {
				return attachmentfile;
			 }
		} else {
			return attachmentfile;
		}
	}
	
	private AttachmentFile uploadFile(MultipartFile file) {
		
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
			AttachmentFile notUploadedAttachmentfile = new AttachmentFile(originalFileName, storedFileName, fileSize, "N", "N");
			return notUploadedAttachmentfile;
		}
		
		AttachmentFile uploadedAttachmentfile = new AttachmentFile(originalFileName, storedFileName, fileSize, "Y", "N");
		return uploadedAttachmentfile;
	}

	@Transactional
	@Override
	public String removeAndDelFile(int fileNum, String storedFileName) {
		if (dao.removeFile(fileNum) == 1) {
			File file = new File(FILE_PATH, storedFileName);
			if (file.delete()) {
				return "SUCCESS";
			} else {
				return "FAIL";
			}
		} else {
			return "FAIL_DEL";
		}
		
	}

	@Override
	public void downloadFile(String storedFileName, String originalFileName, HttpServletResponse response) {
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

