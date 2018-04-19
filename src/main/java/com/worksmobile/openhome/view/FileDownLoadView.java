package com.worksmobile.openhome.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownLoadView extends AbstractView{
	
	public FileDownLoadView() {
		
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		/*
		String root = req.getSession().getServletContext().getRealPath("/");
		//root + "temp/"
		String saveDirectory = root +"file" + File.separator;*/
		
		/*String upload= dao.getFile(fileNum); //file 이름 가져오기
*/		/*String storedFileName = attachmentfile.getStoredFileName();
		String originalFileName = attachmentfile.getOriginalFileName();
		*/
		/*//파일명이 한글일때 인코딩 작업을 한다.
		String str = URLEncoder.encode(originalFileName,"UTF-8");*/
				
		/*//원본파일명에서 공백이 있을때 + 로 표시가 되므로 공백으로 처리해줌
		str =  str.replaceAll("\\+", "%20");*/
		
		/*String fullPath = saveDirectory +"/" + storedFileName;
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
			                                + new String(originalFileName.getBytes(), ));
    	} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
        
        res.setHeader("Content-Transfer-Encoding","binary");

        FileCopyUtils.copy(new FileInputStream(new File(saveDirectory, storedFileName)), res.getOutputStream());
	}
}*/
	}
	}