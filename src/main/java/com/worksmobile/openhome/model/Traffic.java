/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.model;

import java.util.Date;

public class Traffic {
	private int traffic_num;
	private int traffic_content_length;
	private String traffic_kind;
	private Date traffic_date;
	private String traffic_ip;
	private int countAll;
	
	public int getCountAll() {
		return countAll;
	}
	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}
	public int getTraffic_num() {
		return traffic_num;
	}
	public void setTraffic_num(int traffic_num) {
		this.traffic_num = traffic_num;
	}
	public int getTraffic_content_length() {
		return traffic_content_length;
	}
	public void setTraffic_content_length(int traffic_content_length) {
		this.traffic_content_length = traffic_content_length;
	}
	public String getTraffic_kind() {
		return traffic_kind;
	}
	public void setTraffic_kind(String traffic_kind) {
		this.traffic_kind = traffic_kind;
	}
	public Date getTraffic_date() {
		return traffic_date;
	}
	public void setTraffic_date(Date traffic_date) {
		this.traffic_date = traffic_date;
	}
	public String getTraffic_ip() {
		return traffic_ip;
	}
	public void setTraffic_ip(String traffic_ip) {
		this.traffic_ip = traffic_ip;
	}
	
	
}
