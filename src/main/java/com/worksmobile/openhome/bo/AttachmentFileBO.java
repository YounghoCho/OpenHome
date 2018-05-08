package com.worksmobile.openhome.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.model.AttachmentFile;

@Service
public interface AttachmentFileBO {

	/*@author Suji Jang*/
	public String addFile(String fileAttacher, int articleNum, MultipartHttpServletRequest mreq);
	public List<AttachmentFile> getFiles(int aricleNumber);
	public String removeFiles(int articleNumber, HttpServletRequest req);
	public List<AttachmentFile> checkAndGetAttachmentFile(int articleNumber, HttpServletRequest req) throws Exception;
	public String modFile(String fileAttacher, int articleNum, MultipartHttpServletRequest mreq) throws Exception;
	/*public String addPhotoFile(int articleNum, MultipartHttpServletRequest mreq);*/
	public String removeFile(int fileNum, HttpServletRequest req);
}