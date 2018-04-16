package com.worksmobile.openhome.bo;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public interface AttachmentFileBO {

	/*@author Suji Jang*/
	/*public void addFileInfo(List<AttachmentFile> attachmentFileList); */
	public void addFile(int articleNum, String fileAttacher, MultipartHttpServletRequest multi);
	
}
