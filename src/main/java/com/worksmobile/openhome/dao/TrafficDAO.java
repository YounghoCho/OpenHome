package com.worksmobile.openhome.dao;

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
	
	

}
