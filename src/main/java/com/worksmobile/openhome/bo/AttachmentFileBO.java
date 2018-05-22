package com.worksmobile.openhome.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.model.AttachmentFile;

@Service
public interface AttachmentFileBO {

	/*@author Suji Jang*/

	public List<AttachmentFile> getFiles(int aricleNumber);
	/*public String removeFiles(int articleNumber, HttpServletRequest req);*/
	public List<AttachmentFile> checkAndGetAttachmentFile(int articleNumber, HttpServletRequest req) throws Exception;
	/*public String removeFile(int fileNum, HttpServletRequest req);*/
	public AttachmentFile uploadAndAddFile(int articleNum, MultipartFile files);
	public String removeAndDelFile(int fileNum, String storedFileName);
	public void downloadFile(String storedFileName, String originalFileName, HttpServletResponse response);
}