package com.worksmobile.OpenHomeProject.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.OpenHomeProject.dao.BoardDAO;
import com.worksmobile.OpenHomeProject.dto.BoardDTO;
import com.worksmobile.OpenHomeProject.dto.File_uploadDTO;
import com.worksmobile.OpenHomeProject.dto.MessageDTO;
import com.worksmobile.OpenHomeProject.dto.TrafficDTO;

@Service("BoardService")
public class BoardServiceImp implements BoardService{
	
	@Resource(name="BoardDAO")
	private BoardDAO dao;

	/*Board*/
	@Override
	public List<BoardDTO> MessageList(int boardNumberInt, int currentPageNo, int pageSize) {
		return dao.funcMessagelist(boardNumberInt, currentPageNo, pageSize);
	}
	@Override
	public List<BoardDTO> CountList(int boardNumberInt) {
		return dao.funcCountList(boardNumberInt);
	}
	@Override
	public List<BoardDTO> OriginalMessage(int originalMessageNum) {
		return dao.funcOriginalMessage(originalMessageNum);
	}

	/*Traffic*/
	@Override
	public List<TrafficDTO> getTrafficData() {
		return dao.funcGetTraffic();
	}
	@Override
	public List<TrafficDTO> getTrafficCount() {
		return dao.funcGetTrafficCount();
	}
	
	@Override
	public int MessageInsertProcess(MessageDTO dto) {
		return dao.message_insert(dto);
	}

	@Override
	public void FileInsertProcess(List<File_uploadDTO> fList) {
		dao.file_insert(fList);
	}
	
}

