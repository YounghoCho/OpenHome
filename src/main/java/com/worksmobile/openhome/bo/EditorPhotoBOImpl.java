package com.worksmobile.openhome.bo;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.worksmobile.openhome.model.AttachmentFile;

@Service("EditorPhotoBO")
public class EditorPhotoBOImpl implements EditorPhotoBO {

	private static final String FILE_PATH = "C:\\Users\\USER\\eclipse-workspace\\OpenHome\\src\\main\\webapp\\editorfile\\";
	
	@Override
	public String uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		String return1=request.getParameter("callback"); 
		String return2="?callback_func=" + request.getParameter("callback_func"); 
		String return3=""; 
		String name = ""; 
		
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
			return "FAIL";
		}
		
		
/*		return "<img src='" + "http://localhost:8090/OpenHome/editorfile/"+storedFileName + "' title='" + originalFileName + "' />";*/
	
		return3 += "&bNewLine=true";
		return3 += "&sFileName="+ originalFileName;
		return3 += "&sFileURL=/editorfile/"+storedFileName; 
					
		return "redirect:"+return1+return2+return3;
	}

		
}
