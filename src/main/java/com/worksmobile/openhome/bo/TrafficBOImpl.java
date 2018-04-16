package com.worksmobile.openhome.bo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.BoardDAO;
import com.worksmobile.openhome.dao.TrafficDAO;
import com.worksmobile.openhome.model.Traffic;

	
@Service("TrafficBO")
public class TrafficBOImpl implements TrafficBO{
	
	@Resource(name="TrafficDAO")
	private TrafficDAO trafficdao;
	
	/*@author Youngho Jo*/
	@Override
	public List<Traffic> getTrafficData() {
		return trafficdao.getTrafficData();
	}
	
	@Override
	public int getTrafficCount() {
		return trafficdao.getTrafficCount();
	}

}
