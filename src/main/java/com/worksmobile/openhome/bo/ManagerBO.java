/*
 * Application java
 * @Author : Youngho Jo
 *           Suji    Jang
 */
package com.worksmobile.openhome.bo;

import org.springframework.stereotype.Service;

@Service
public interface ManagerBO {

	public int checkAdminLogin(String managerId, String managerPwd);

}
