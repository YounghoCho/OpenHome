package com.worksmobile.openhome.bo;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.model.AttachmentFile;

@Service
public interface AttachmentFileBO {

	/*@author Suji Jang*/
	public String addFile(String fileAttacher, int articleNum, MultipartHttpServletRequest req);
	public List<AttachmentFile> getFiles(int aricleNumber);
	public String removeFiles(int articleNumber, HttpServletRequest req);
	
	public void downloadFile(AttachmentFile attachmentfile, HttpServletRequest req, HttpServletResponse res) throws IOException;
}

