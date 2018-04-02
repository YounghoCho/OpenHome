package com.worksmobile.OpenHomeProject.dao;

import java.util.List;
import com.worksmobile.OpenHomeProject.dto.BoardDTO;

public interface BoardDAO {
	
	public List<BoardDTO> funcMessagelist(int boardNumberInt, int currentPageNo);

	public List<BoardDTO> funcCountList(int boardNumberInt);

	public List<BoardDTO> funcOriginalMessage(int originalMessageNum);
}
