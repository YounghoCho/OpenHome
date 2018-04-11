package com.worksmobile.openhome.dto;

import java.util.Date;

public class MessageDTO {
	private int message_num;
	private int board_num;
	private int countAll;
	private int ROWNUM;
	private String message_subject;
	private String message_sample;	
	private String message_content;
	private Date message_date;
	private String message_writer;
	private String message_pwd;

	public int getCountAll() {
		return countAll;
	}
	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}
	
	public int getMessage_num() {
		return message_num;
	}
	public void setMessage_num(int message_num) {
		this.message_num = message_num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getMessage_subject() {
		return message_subject;
	}
	public void setMessage_subject(String message_subject) {
		this.message_subject = message_subject;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public Date getMessage_date() {
		return message_date;
	}
	public void setMessage_date(Date message_date) {
		this.message_date = message_date;
	}
	public String getMessage_writer() {
		return message_writer;
	}
	public void setMessage_writer(String message_writer) {
		this.message_writer = message_writer;
	}
	public String getMessage_pwd() {
		return message_pwd;
	}
	public void setMessage_pwd(String message_pwd) {
		this.message_pwd = message_pwd;
	}
	public String getMessage_sample() {
		return message_sample;
	}
	public void setMessage_sample(String message_sample) {
		this.message_sample = message_sample;
	}
	public int getROWNUM() {
		return ROWNUM;
	}
	public void setROWNUM(int rOWNUM) {
		ROWNUM = rOWNUM;
	}
}
