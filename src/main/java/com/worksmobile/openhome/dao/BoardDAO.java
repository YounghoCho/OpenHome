package com.worksmobile.openhome.dao;

import java.util.List;

import com.worksmobile.openhome.model.File_uploadDTO;
import com.worksmobile.openhome.model.Message;
import com.worksmobile.openhome.model.Traffic;

public interface BoardDAO {
	
	/*Board*/
	public List<Message> funcMessagelist(int boardNumberInt, int currentPageNo, int pageSize);
	public List<Message> funcCountList(int boardNumberInt);
	public List<Message> funcOriginalMessage(int originalMessageNum);

	/*Traffic*/
	public List<Traffic> funcGetTraffic();
	public List<Traffic> funcGetTrafficCount();
	
	public int message_insert(Message dto);
	public void file_insert(List<File_uploadDTO> fList);
}
