package com.worksmobile.openhome.model;

import java.util.Date;

public class Traffic {
	
	private int trafficNum;
	private int trafficContentLength;
	private String trafficKind;
	private Date trafficDate;
	private String trafficIp;
	
	public Traffic() {
		
	}

	public int getTrafficNum() {
		return trafficNum;
	}

	public void setTrafficNum(int trafficNum) {
		this.trafficNum = trafficNum;
	}

	public int getTrafficContentLength() {
		return trafficContentLength;
	}

	public void setTrafficContentLength(int trafficContentLength) {
		this.trafficContentLength = trafficContentLength;
	}

	public String getTrafficKind() {
		return trafficKind;
	}

	public void setTrafficKind(String trafficKind) {
		this.trafficKind = trafficKind;
	}

	public Date getTrafficDate() {
		return trafficDate;
	}

	public void setTrafficDate(Date trafficDate) {
		this.trafficDate = trafficDate;
	}

	public String getTrafficIp() {
		return trafficIp;
	}

	public void setTrafficIp(String trafficIp) {
		this.trafficIp = trafficIp;
	}

}
