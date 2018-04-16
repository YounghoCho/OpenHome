/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.bo;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.BoardDAO;
import com.worksmobile.openhome.model.Board;

@Service("BoardBO")
public class BoardBOImpl implements BoardBO{

	@Resource(name="BoardDAO")
	private BoardDAO dao;
	
	@Override
	public List<Board> getBoardList() {
		return dao.getBoardList();
	}
	@Override
	public void removeBoard(int boardNum) {
		dao.removeBoard(boardNum);
	}
}

