package com.worksmobile.OpenHomeProject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.worksmobile.OpenHomeProject.dto.BoardDTO;

public interface BoardDAO {
	public List<BoardDTO> board_list() throws Exception;
}
