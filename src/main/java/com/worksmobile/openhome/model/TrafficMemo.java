package com.worksmobile.openhome.model;

import java.sql.Date;

import lombok.Data;

@Data
public class TrafficMemo {
	private String tmType;
	private int tmContentLength;
	private Date tmDate;
}
