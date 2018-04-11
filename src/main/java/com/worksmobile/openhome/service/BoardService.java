package com.worksmobile.openhome.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.model.BoardDTO;
import com.worksmobile.openhome.model.File_uploadDTO;
import com.worksmobile.openhome.model.MessageDTO;
import com.worksmobile.openhome.model.TrafficDTO;

@Service
public interface BoardService {
	
	/*Board*/
	public List<BoardDTO> MessageList (int boardNumberInt, int currentPageNo, int pageSize);
	public List<BoardDTO> CountList (int boardNumberInt);
	public List<BoardDTO> OriginalMessage (int originalMessageNum);

	/*Traffic*/
	public List<TrafficDTO> getTrafficData ();
	public List<TrafficDTO> getTrafficCount ();
	
	public int MessageInsertProcess(MessageDTO dto); //게시글 추가
	public void FileInsertProcess(List<File_uploadDTO> fList); //첨부파일 추가

	

}
