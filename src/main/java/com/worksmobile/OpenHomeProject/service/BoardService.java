package com.worksmobile.OpenHomeProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.OpenHomeProject.dto.BoardDTO;

@Service
public interface BoardService {
	public List<BoardDTO> BoardListProcess() throws Exception;

	public List<BoardDTO> MessageListProcess();
	public List<BoardDTO> MessageListProcess2();
	public List<BoardDTO> MessageListProcess3();
	public List<BoardDTO> MessageListProcess4();

	public List<BoardDTO> CountListProcess();
	public List<BoardDTO> CountListProcess2();
	public List<BoardDTO> CountListProcess3();
	public List<BoardDTO> CountListProcess4();
}
