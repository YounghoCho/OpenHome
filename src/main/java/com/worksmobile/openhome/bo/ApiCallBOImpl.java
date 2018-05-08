package com.worksmobile.openhome.bo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.ApiCallDAO;
import com.worksmobile.openhome.model.ApiCall;

@Service("ApiCallBO")
public class ApiCallBOImpl implements ApiCallBO{

	@Resource(name="ApiCallDAO")
	private ApiCallDAO dao;

	@Override
	public void insertApiCall(String apiLevel) {
		dao.insertApiCall(apiLevel);
	}

	@Override
	public List<ApiCall> getTotalApiList() {
		return dao.getTotalApiList();
	}
}
