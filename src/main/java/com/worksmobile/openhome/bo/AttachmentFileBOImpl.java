package com.worksmobile.openhome.bo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String addFile(String fileAttacher, int articleNum, MultipartHttpServletRequest mreq) {
		
		//--첨부파일 등록--
		//경로 설정
		String root = mreq.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root + "file" + File.separator;
		
		System.out.println(saveDirectory);
		//파일 디렉터리 생성
		File dir = new File(saveDirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		List<AttachmentFile> fList = new ArrayList<AttachmentFile>();
		Iterator<String> files = mreq.getFileNames();
		
		//파일 저장 및 map리스트 생성
		while (files.hasNext()) {
			String uploadFile = files.next();
			MultipartFile multipartfile = mreq.getFile(uploadFile);
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
				return "success";
			} else {
				return "fail";
			}
		} else {
			return "none";
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
	public List<AttachmentFile> checkAndGetAttachmentFile(@RequestParam("articleNumber") int articleNumber, HttpServletRequest req) throws Exception { 
		List<AttachmentFile> attachmentfileList = dao.getFiles(articleNumber);
		if (attachmentfileList.size() !=0) {
			return attachmentfileList;
		} else {
			return null;
		}
	}

	@Override
	public String modFile(String fileAttacher, int articleNum,  MultipartHttpServletRequest mreq) throws Exception {
		
		String root = mreq.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root + "file" + File.separator;
		
		/*Enumeration<String> reqParams = req.getParameterNames();
		List<AttachmentFile> oldfileList = dao.getFiles(articleNum);
		
		while(reqParams.hasMoreElements()) {
			for (AttachmentFile attachmentfile : oldfileList) {
				if (req.getParameter(reqParams.nextElement()).equals(attachmentfile.getStoredFileName())) {
					oldfileList.remove(attachmentfile);
				}
			}
		}
		
		if (oldfileList.size() != 0) {
			for (AttachmentFile attachmentfile : oldfileList) {
				File file = new File(saveDirectory + attachmentfile.getStoredFileName());
				file.delete();
				dao.removeFile(attachmentfile.getFileNum());
			}
		}*/
	
		List<AttachmentFile> newfileList = new ArrayList<AttachmentFile>();
		Iterator<String> files = mreq.getFileNames();
		
		//파일 저장 및 map리스트 생성
		while (files.hasNext()) {
			String uploadFile = files.next();
			MultipartFile multipartfile = mreq.getFile(uploadFile);
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
			newfileList.add(attachmentfile);
		}
		
		if (newfileList.size() != 0) {
			int uploadFileCount = 0;
			//DB에 저장
			for (AttachmentFile file : newfileList) {
				uploadFileCount += dao.addFile(file);
			}
			
			if (uploadFileCount == newfileList.size()) {
				return "success";
			} else {
				return "fail";
			}
		} else {
			return "none";
		}
		
	}

	@Override
	public String addPhotoFile(int articleNum, MultipartHttpServletRequest mreq) {
		String root = mreq.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root + "photo" + File.separator;
		
		System.out.println(saveDirectory);
		//파일 디렉터리 생성
		File dir = new File(saveDirectory);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		List<AttachmentFile> fList = new ArrayList<AttachmentFile>();
		Iterator<String> files = mreq.getFileNames();
		
		String multiphoto = "";
		
		//파일 저장 및 map리스트 생성
		while (files.hasNext()) {
			String uploadFile = files.next();
			MultipartFile multipartfile = mreq.getFile(uploadFile);
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
			
			multiphoto += "<img src='/OpenHome/photo/" + storedFileName + "' title='" + originalFileName + "'/>";
			
/*			AttachmentFile attachmentfile = new AttachmentFile(articleNum, originalFileName, storedFileName, (int)multipartfile.getSize(), fileAttacher);
			fList.add(attachmentfile);*/
		}
		
		return multiphoto;
		
/*		if (fList.size() != 0) {
			int uploadFileCount = 0;
			//DB에 저장
			for (AttachmentFile file : fList) {
				uploadFileCount += dao.addFile(file);
			}
			
			if (uploadFileCount == fList.size()) {
				return "success";
			} else {
				return "fail";
			}
		} else {
			return "none";
		}*/
	}
}

