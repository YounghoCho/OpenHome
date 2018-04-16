/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.dao;

import java.util.HashMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ManagerDAO")
public class ManagerDAO {

	private static final String NAMESPACE_MANAGER = "manager.";
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	//////////////////////////////Methods//////////////////////////////
	public int checkAdminLogin(String managerId, String managerPwd) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("managerId", managerId);
		paramMap.put("managerPwd", managerPwd);
		
		return sqlsession.selectOne(NAMESPACE_MANAGER + "checkAdminLogin", paramMap);
	}
	
}
