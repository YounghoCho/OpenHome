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
public class AttachmentFileImpl implements AttachmentFileBO{
	
	@Resource(name="AttachmentFileDAO")
	private AttachmentFileDAO attachmentfiledao;

/*	@ author Suji Jang */
/*	@Override
	public void addFileInfo(List<AttachmentFile> attachmentFileList) {
		attachmentfiledao.addFileInfo(attachmentFileList);
	}*/


	@Override
	public void addFile(int articleNum, String fileAttacher, MultipartHttpServletRequest multi) {
		
		//--첨부파일 등록--
		//경로 설정
		String root = multi.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root + "file/";
		
		//파일 디렉터리 생성
		File dir = new File(saveDirectory);
		if(!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		List<AttachmentFile> fList = new ArrayList<AttachmentFile>();
		Iterator<String> files = multi.getFileNames();
		
		//파일 저장 및 map리스트 생성
		while (files.hasNext()) {
			String uploadFile = files.next();
			MultipartFile multipartfile = multi.getFile(uploadFile);
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
			
			AttachmentFile attachmentfile = new AttachmentFile(articleNum, originalFileName, originalFileName, (int)multipartfile.getSize(), fileAttacher);
			fList.add(attachmentfile);
		}
		
		if (fList.size() != 0 ) {
			//첨부파일 데이터베이스에 등록
			attachmentfiledao.addFile(fList);
		}
	}
}
	
