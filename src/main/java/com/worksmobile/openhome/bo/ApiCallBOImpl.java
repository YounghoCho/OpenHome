package com.worksmobile.openhome.bo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.ApiCallDAO;

@Service("ApiCallBO")
public class ApiCallBOImpl implements ApiCallBO{

	@Resource(name="ApiCallDAO")
	private ApiCallDAO dao;

	@Override
	public void insertApiCall(String apiLevel) {
		dao.insertApiCall(apiLevel);
	}

	@Override
	public Object getTotalApiList() {
		return dao.getTotalApiList();
	}

	@Override
	public Object getTotalapiCount() {
		return dao.getTotalapiCount();
	}
}
