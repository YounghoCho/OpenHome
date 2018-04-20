package com.worksmobile.openhome.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.worksmobile.openhome.model.AttachmentFile;

@Repository("AttachmentFileDAO")
public class AttachmentFileDAO {
	
	private static final String NAMESPACE_ATTACHMENTFILE = "attachmentfile.";
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	public AttachmentFileDAO() {}
	
	//첨부파일 정보 추가
	public int addFile(AttachmentFile attachmentFile) {
		return sqlsession.insert(NAMESPACE_ATTACHMENTFILE + "addFile", attachmentFile);	
	}
	
	//첨부파일 조회
	public List<AttachmentFile> getFiles(int articleNumber) {
		return sqlsession.selectList(NAMESPACE_ATTACHMENTFILE + "getFiles", articleNumber);
	}
	
	//첨부파일 삭제
	public int removeFiles(int articleNumber) {
		return sqlsession.delete(NAMESPACE_ATTACHMENTFILE + "removeFiles", articleNumber);
	}
}
