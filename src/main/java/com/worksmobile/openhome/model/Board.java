package com.worksmobile.openhome.model;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
	private int boardNum;
	private int countAll;
	private String boardTitle;
	private Date boardDate;
	private int managerNum;
	private int boardOrderNum;
}
