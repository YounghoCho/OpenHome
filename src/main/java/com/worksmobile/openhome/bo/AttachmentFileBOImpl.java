package com.worksmobile.openhome.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
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
		String saveDirectory = root + "file" + File.separator;
		
		System.out.println(saveDirectory);
		//파일 디렉터리 생성
		File dir = new File(saveDirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		System.out.println("여기까지");
		List<AttachmentFile> fList = new ArrayList<AttachmentFile>();
		Iterator<String> files = req.getFileNames();
		
		//파일 저장 및 map리스트 생성
		while (files.hasNext()) {
			String uploadFile = files.next();
			MultipartFile multipartfile = req.getFile(uploadFile);
			String originalFileName = multipartfile.getOriginalFilename();
			System.out.println(originalFileName);
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
		
		if (fList.size() != 0) {
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
		} else {
			return "sorry : no list";
		}
	}

	@Override
	public List<AttachmentFile> getFiles(int articleNumber) {
		return dao.getFiles(articleNumber);
	}
	
	@Override
	public String removeFiles(int articleNumber, HttpServletRequest req) {
		List<AttachmentFile> attachmentfileList = dao.getFiles(articleNumber);
		
		String root = req.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root + "file" + File.separator;
		
		if(dao.getFiles(articleNumber).size() != 0 ) {
			
			for(AttachmentFile attachmentfile : attachmentfileList) {
				File file = new File(saveDirectory, attachmentfile.getStoredFileName()); 
				file.delete();
			}
			
			if (dao.removeFiles(articleNumber) == 1) {
				return "success";
			} else {
				return "fail";
			}
		} else {
			return "none";
		}
	}

	@Override
	public void downloadFile(AttachmentFile attachmentfile, HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String root = req.getSession().getServletContext().getRealPath("/");
		//root + "temp/"
		String saveDirectory = root +"file" + File.separator;
		
		/*String upload= dao.getFile(fileNum); //file 이름 가져오기
*/		String storedFileName = attachmentfile.getStoredFileName();
		String originalFileName = attachmentfile.getOriginalFileName();
		
		/*//파일명이 한글일때 인코딩 작업을 한다.
		String str = URLEncoder.encode(originalFileName,"UTF-8");*/
				
		/*//원본파일명에서 공백이 있을때 + 로 표시가 되므로 공백으로 처리해줌
		str =  str.replaceAll("\\+", "%20");*/
		
		String fullPath = saveDirectory +"/" + storedFileName;
		System.out.println("1");
		File downloadFile = new File(fullPath);
		System.out.println(downloadFile.getName());
		System.out.println("2");
		
		//파일 사이즈 지정
		res.setContentLength((int)downloadFile.length());
		System.out.println((int)downloadFile.length());
		
		//다운로드 창을 띄우기 위한 헤더 조작
        res.setContentType("application/octet-stream; charset=utf-8");
        try {
			res.setHeader("Content-Disposition", "attachment;filename="
			                                + new String(originalFileName.getBytes(), "ISO8859_1"));
    	} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
        
        res.setHeader("Content-Transfer-Encoding","binary");

        FileCopyUtils.copy(new FileInputStream(new File(saveDirectory, storedFileName)), res.getOutputStream());
	}
}
