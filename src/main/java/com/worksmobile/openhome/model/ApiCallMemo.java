package com.worksmobile.openhome.model;

import java.sql.Date;
import lombok.Data;

@Data
public class ApiCallMemo {
	private String acmType;
	private int acmCount;
	private Date acmDate;
}
