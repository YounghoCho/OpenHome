package com.worksmobile.openhome.BO;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.BoardDAO;
import com.worksmobile.openhome.model.File_uploadDTO;
import com.worksmobile.openhome.model.Message;
import com.worksmobile.openhome.model.Traffic;

@Service("BoardService")
public class OpenhomeBOImpl implements OpenhomeBO{
	
	@Resource(name="BoardDAO")
	private BoardDAO dao;

	/*Board*/
	@Override
	public List<Message> MessageList(int boardNumberInt, int currentPageNo, int pageSize) {
		return dao.funcMessagelist(boardNumberInt, currentPageNo, pageSize);
	}
	@Override
	public List<Message> CountList(int boardNumberInt) {
		return dao.funcCountList(boardNumberInt);
	}
	@Override
	public List<Message> OriginalMessage(int originalMessageNum) {
		return dao.funcOriginalMessage(originalMessageNum);
	}

	/*Traffic*/
	@Override
	public List<Traffic> getTrafficData() {
		return dao.funcGetTraffic();
	}
	@Override
	public List<Traffic> getTrafficCount() {
		return dao.funcGetTrafficCount();
	}
	
	@Override
	public int MessageInsertProcess(Message dto) {
		return dao.message_insert(dto);
	}

	@Override
	public void FileInsertProcess(List<File_uploadDTO> fList) {
		dao.file_insert(fList);
	}
	
}

