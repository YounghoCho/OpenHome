package com.worksmobile.openhome.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.model.ApiCall;

@Service
public interface ApiCallBO {
	public void insertApiCall(String apiLevel);
	public List<ApiCall> getTotalApiList();
	
}
