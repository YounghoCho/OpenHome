package com.worksmobile.openhome.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.openhome.model.AttachmentFile;

@Repository("AttachmentFileDAO")
public class AttachmentFileDAO {
	
	private static final String NAMESPACE_ATTACHMENTFILE = "attchmentfile.";
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public AttachmentFileDAO() {}
	
	//첨부파일 정보 추가
	public void addFile(List<AttachmentFile> attachmentFile) {
		sqlsession.insert(NAMESPACE_ATTACHMENTFILE + "addFile", attachmentFile);	
	}

}
