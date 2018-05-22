package com.worksmobile.openhome.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class SearchData {

	private int boardNum;
	private String searchType;
	private String searchContent;
	private String searchWriter;
	private String fileAnswer;
	private String commentAnswer;
	private String startDate;
	private String endDate;
}
