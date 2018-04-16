package com.worksmobile.openhome.bo;

import org.springframework.stereotype.Service;

@Service
public interface ManagerBO {
	
	/*Manager*/
	public int checkAdminLogin(String managerId, String managerPwd);

}
