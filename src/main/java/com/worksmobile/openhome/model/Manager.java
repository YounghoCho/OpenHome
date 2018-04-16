package com.worksmobile.openhome.model;

public class Manager {

	private int managerNum;
	private String managerId;
	private String managerPwd;
	private String managerName;
	private String managerPhone;
	
	private int checkLoginManager;
	
	public Manager() {
	
	}

	public int getManagerNum() {
		return managerNum;
	}
	public void setManagerNum(int managerNum) {
		this.managerNum = managerNum;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerPwd() {
		return managerPwd;
	}
	public void setManagerPwd(String managerPwd) {
		this.managerPwd = managerPwd;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public int getCheckLoginManager() {
		return checkLoginManager;
	}
	public void setCheckLoginManager(int checkLoginManager) {
		this.checkLoginManager = checkLoginManager;
	}
	
}
