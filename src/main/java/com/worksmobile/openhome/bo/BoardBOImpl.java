package com.worksmobile.openhome.bo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.BoardDAO;
import com.worksmobile.openhome.model.Board;

@Service("BoardBO")
public class BoardBOImpl implements BoardBO{
	
	@Resource(name="BoardDAO")
	private BoardDAO boarddao;
	
	/*Board*/
	/*@author Youngho Jo*/
	@Override
	public List<Board> getMenuList() {
		return boarddao.getMenuList();
	}
	@Override
	public void removeBoard(int boardNum) {
		boarddao.removeBoard(boardNum);
	}

}

