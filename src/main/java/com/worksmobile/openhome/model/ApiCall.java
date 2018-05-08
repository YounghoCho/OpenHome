package com.worksmobile.openhome.model;

import java.sql.Date;

import lombok.Data;
@Data
public class ApiCall {
	 private int articleList;
	 private int articleDetail;
	 private int articleWrite;
	 private int fileUpload;
	 private int fileDownload;
	 private Date apiDate;
}
