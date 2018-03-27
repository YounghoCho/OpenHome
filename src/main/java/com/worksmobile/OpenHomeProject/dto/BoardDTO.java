package com.worksmobile.OpenHomeProject.dto;

import java.util.Date;

public class BoardDTO {

	private int board_num;
	private String board_title;
	private Date board_reg_date;
	private int board_maker;
	private int board_list_num;
	
	public BoardDTO() {
		
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public Date getBoard_reg_date() {
		return board_reg_date;
	}

	public void setBoard_reg_date(Date board_reg_date) {
		this.board_reg_date = board_reg_date;
	}

	public int getBoard_maker() {
		return board_maker;
	}

	public void setBoard_maker(int board_maker) {
		this.board_maker = board_maker;
	}

	public int getBoard_list_num() {
		return board_list_num;
	}

	public void setBoard_list_num(int board_list_num) {
		this.board_list_num = board_list_num;
	}
	
	
}
