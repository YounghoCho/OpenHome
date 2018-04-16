package com.worksmobile.openhome.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.model.Board;

@Service
public interface BoardBO {
	
	/*Board*/
	/*@author Youngho Jo*/
	public List<Board> getMenuList();
	public void removeBoard(int boardNum);	

}
