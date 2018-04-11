package com.worksmobile.openhome.BO;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.model.File_uploadDTO;
import com.worksmobile.openhome.model.Message;
import com.worksmobile.openhome.model.Traffic;

@Service
public interface OpenhomeBO {
	
	/*Board*/
	public List<Message> MessageList (int boardNumberInt, int currentPageNo, int pageSize);
	public List<Message> CountList (int boardNumberInt);
	public List<Message> OriginalMessage (int originalMessageNum);

	/*Traffic*/
	public List<Traffic> getTrafficData ();
	public List<Traffic> getTrafficCount ();
	
	public int MessageInsertProcess(Message dto); //게시글 추가
	public void FileInsertProcess(List<File_uploadDTO> fList); //첨부파일 추가

	

}
