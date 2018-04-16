package com.worksmobile.openhome.model;

import java.util.Date;

public class Board {

	private int boardNum;
	private int countAll;
	private String boardTitle;
	private Date boardDate;
	private int managerNum;
	private int boardOrderNum;
	
	public Board() {
		
	}

	public int getCountAll() {
		return countAll;
	}

	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getManagerNum() {
		return managerNum;
	}

	public void setManagerNum(int managerNum) {
		this.managerNum = managerNum;
	}

	public int getBoardOrderNum() {
		return boardOrderNum;
	}

	public void setBoardOrderNum(int boardOrderNum) {
		this.boardOrderNum = boardOrderNum;
	}

}
