package com.worksmobile.OpenHomeProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.OpenHomeProject.dto.BoardDTO;

@Service
public interface BoardService {
	public List<BoardDTO> BoardListProcess() throws Exception;

	public List<BoardDTO> MessageListProcess();

}
