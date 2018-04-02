package com.worksmobile.OpenHomeProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.OpenHomeProject.dto.BoardDTO;

@Service
public interface BoardService {
	public List<BoardDTO> MessageList(int boardNumberInt, int currentPageNo);

	public List<BoardDTO> CountList(int boardNumberInt);

	public List<BoardDTO> OriginalMessage(int originalMessageNum);
}
