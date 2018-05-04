package com.worksmobile.openhome.bo;

import org.springframework.stereotype.Service;

@Service
public interface ApiCallBO {
	public void insertApiCall(String apiLevel);

	public Object getTotalApiList();

	public Object getTotalapiCount();
}
