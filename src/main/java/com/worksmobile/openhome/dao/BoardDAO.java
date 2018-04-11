package com.worksmobile.openhome.dao;

import java.util.List;

import com.worksmobile.openhome.dto.BoardDTO;
import com.worksmobile.openhome.dto.File_uploadDTO;
import com.worksmobile.openhome.dto.MessageDTO;
import com.worksmobile.openhome.dto.TrafficDTO;

public interface BoardDAO {
	
	/*Board*/
	public List<BoardDTO> funcMessagelist(int boardNumberInt, int currentPageNo, int pageSize);
	public List<BoardDTO> funcCountList(int boardNumberInt);
	public List<BoardDTO> funcOriginalMessage(int originalMessageNum);

	/*Traffic*/
	public List<TrafficDTO> funcGetTraffic();
	public List<TrafficDTO> funcGetTrafficCount();
	
	public int message_insert(MessageDTO dto);
	public void file_insert(List<File_uploadDTO> fList);
}
