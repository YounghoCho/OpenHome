package com.worksmobile.openhome.bo;

import java.util.List;
/*
 * @Author : Youngho Jo
 */

import org.springframework.stereotype.Service;
import com.worksmobile.openhome.model.Traffic;

@Service
public interface TrafficBO {

	public List<Traffic> getTrafficData();
	public int getTrafficCount();
	public void insertContentLength(int trafficContentLength, String trafficKind);	
}
