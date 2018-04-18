package com.worksmobile.openhome.bo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.worksmobile.openhome.model.AttachmentFile;

@Service
public interface AttachmentFileBO {

	/*@author Suji Jang*/
	public String addFile(String fileAttacher, int articleNum, MultipartHttpServletRequest req);
	public List<AttachmentFile> getFiles(int aricleNumber);
}

