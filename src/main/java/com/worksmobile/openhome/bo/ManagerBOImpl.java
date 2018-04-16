package com.worksmobile.openhome.bo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.ManagerDAO;

@Service("ManagerBO")
public class ManagerBOImpl implements ManagerBO {
	
	@Resource(name="ManagerDAO")
	private ManagerDAO managerdao;
	
	/*Manager*/
	@Override
	public int checkAdminLogin(String managerId, String managerPwd) {
		return managerdao.checkAdminLogin(managerId, managerPwd);
	}

}
