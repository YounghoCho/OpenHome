package com.worksmobile.OpenHomeProject.dao;

import java.util.List;

import com.worksmobile.OpenHomeProject.dto.BoardDTO;
import com.worksmobile.OpenHomeProject.dto.TrafficDTO;

public interface BoardDAO {
	
	/*Board*/
	public List<BoardDTO> funcMessagelist(int boardNumberInt, int currentPageNo, int pageSize);
	public List<BoardDTO> funcCountList(int boardNumberInt);
	public List<BoardDTO> funcOriginalMessage(int originalMessageNum);

	/*Traffic*/
	public List<TrafficDTO> funcGetTraffic();
	public List<TrafficDTO> funcGetTrafficCount();
}
