package com.worksmobile.openhome.bo;

import java.util.List;

import org.springframework.stereotype.Service;
import com.worksmobile.openhome.model.Traffic;

@Service
public interface TrafficBO {

	/*@author Youngho Jo*/
	public List<Traffic> getTrafficData();
	public int getTrafficCount();

}
