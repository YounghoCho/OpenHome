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
	public List<AttachmentFile> getFiles(int articleNum) {
		return sqlsession.selectList(NAMESPACE_ATTACHMENTFILE + "getFiles", articleNum);
	}
	
	//첨부파일 삭제
	public int removeFiles(int articleNum) {
		return sqlsession.delete(NAMESPACE_ATTACHMENTFILE + "removeFiles", articleNum);
	}
	
	//첨부파일 삭제
	public int removeFile(int fileNum) {
		return sqlsession.delete(NAMESPACE_ATTACHMENTFILE + "removeFile", fileNum);
	}
	
	public AttachmentFile getFile(int fileNum) {
		return sqlsession.selectOne(NAMESPACE_ATTACHMENTFILE + "getFile", fileNum);
	}
}
