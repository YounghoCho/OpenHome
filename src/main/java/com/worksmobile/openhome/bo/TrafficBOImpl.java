/*
 * Application java
 * @Author : Youngho Jo
 */
package com.worksmobile.openhome.bo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.TrafficDAO;
import com.worksmobile.openhome.model.Traffic;

@Service("TrafficBO")
public class TrafficBOImpl implements TrafficBO{

	@Resource(name="TrafficDAO")
	private TrafficDAO dao;
	
	@Override
	public List<Traffic> getTrafficData() {
		return dao.getTrafficData();
	}
	@Override
	public int getTrafficCount() {
		return dao.getTrafficCount();
	}
}
