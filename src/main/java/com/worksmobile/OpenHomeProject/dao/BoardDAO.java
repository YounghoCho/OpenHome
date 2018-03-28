package com.worksmobile.OpenHomeProject.dao;

import java.util.List;
import com.worksmobile.OpenHomeProject.dto.BoardDTO;

public interface BoardDAO {
	public List<BoardDTO> board_list() throws Exception;

	public List<BoardDTO> message_list();
}
