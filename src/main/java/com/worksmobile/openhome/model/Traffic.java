package com.worksmobile.openhome.model;

import java.util.Date;
import lombok.Data;

@Data
public class Traffic {
	private int trafficNum;
	private int trafficContentLength;
	private String trafficKind;
	private Date trafficDate;
}
