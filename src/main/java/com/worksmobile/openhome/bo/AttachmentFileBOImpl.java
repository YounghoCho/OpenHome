package com.worksmobile.openhome.bo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.dao.AttachmentFileDAO;
import com.worksmobile.openhome.model.AttachmentFile;

@Service("AttachmentFileBO")
public class AttachmentFileBOImpl implements AttachmentFileBO{
	
	@Resource(name="AttachmentFileDAO")
	private AttachmentFileDAO dao;

/*	@ author Suji Jang */
	@Override
	public String addFile(String fileAttacher, int articleNum, MultipartHttpServletRequest req) {
		
		//--첨부파일 등록--
		//경로 설정
		String root = req.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root + "file/";
		
		//파일 디렉터리 생성
		File dir = new File(saveDirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		List<AttachmentFile> fList = new ArrayList<AttachmentFile>();
		Iterator<String> files = req.getFileNames();
		
		//파일 저장 및 map리스트 생성
		while (files.hasNext()) {
			String uploadFile = files.next();
			MultipartFile multipartfile = req.getFile(uploadFile);
			String originalFileName = multipartfile.getOriginalFilename();
			
			//파일 중복 방지
			String storedFileName = System.currentTimeMillis() + UUID.randomUUID().toString()
					+ "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);
			
			//파일 저장하기
			try {
				multipartfile.transferTo(new File(saveDirectory+storedFileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			AttachmentFile attachmentfile = new AttachmentFile(articleNum, originalFileName, storedFileName, (int)multipartfile.getSize(), fileAttacher);
			fList.add(attachmentfile);
		}
		
		int uploadFileCount = 0;
		//DB에 저장
		for (AttachmentFile file : fList) {
			uploadFileCount += dao.addFile(file);
		}
		
		if (uploadFileCount == fList.size()) {
			return "ok";
		} else {
			return "sorry";
		}
	}

	@Override
	public List<AttachmentFile> getFiles(int articleNumber) {
		return dao.getFiles(articleNumber);
	}
}
	
