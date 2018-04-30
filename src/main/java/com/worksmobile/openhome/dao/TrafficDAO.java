/*
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.dao;

import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.openhome.model.Traffic;

@Repository("TrafficDAO")
public class TrafficDAO {


	private static final String NAMESPACE_TRAFFIC = "traffic.";
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	public TrafficDAO() {

	}

	/*Traffic*/
	public List<Traffic> getTrafficData (){
		return sqlsession.selectList(NAMESPACE_TRAFFIC + "getTrafficData");
	}
	public int getTrafficCount () {
		return sqlsession.selectOne(NAMESPACE_TRAFFIC + "getTrafficCount");
	}

	public void insertContentLength(int trafficContentLength, String trafficKind) {
		System.out.println("넘어온거 두개 : " + trafficContentLength + ", " + trafficKind);
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("trafficContentLength", trafficContentLength);
		paramMap.put("trafficKind", trafficKind);
		sqlsession.insert(NAMESPACE_TRAFFIC + "insertContentLength", paramMap);	
	}

}
