package com.worksmobile.OpenHomeProject.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.worksmobile.OpenHomeProject.dto.BoardDTO;
import com.worksmobile.OpenHomeProject.dto.File_uploadDTO;
import com.worksmobile.OpenHomeProject.dto.MessageDTO;
import com.worksmobile.OpenHomeProject.dto.TrafficDTO;

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
