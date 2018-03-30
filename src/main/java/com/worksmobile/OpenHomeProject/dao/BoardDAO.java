package com.worksmobile.OpenHomeProject.dao;

import java.util.List;
import com.worksmobile.OpenHomeProject.dto.BoardDTO;

public interface BoardDAO {
	public List<BoardDTO> board_list() throws Exception;

	public List<BoardDTO> message_list();
	public List<BoardDTO> message_list2();
	public List<BoardDTO> message_list3();
	public List<BoardDTO> message_list4();

	public List<BoardDTO> count_list();
	public List<BoardDTO> count_list2();
	public List<BoardDTO> count_list3();
	public List<BoardDTO> count_list4();
}
