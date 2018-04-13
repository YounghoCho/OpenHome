/*
 * Application java
 * @Author : Youngho Jo
 *           Suji    Jang
 */
package com.worksmobile.openhome.bo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.dao.OpenhomeDAO;
import com.worksmobile.openhome.model.Board;
import com.worksmobile.openhome.model.File_uploadDTO;
import com.worksmobile.openhome.model.Message;
import com.worksmobile.openhome.model.Traffic;

@Service("BoardService")
public class OpenhomeBOImpl implements OpenhomeBO{
	
	@Resource(name="OpenhomeDAO")
	private OpenhomeDAO dao;
	/*Menu List*/
	@Override
	public List<Board> getMenuList() {
		return dao.getMenuList();
	}
	
	/*Board*/
	@Override
	public List<Message> getArticleList(int boardNumber, int currentPageNo, int pageSize) {
		return dao.getArticleList(boardNumber, currentPageNo, pageSize);
	}
	@Override
	public int getArticleTotalCount(int boardNumber) {
		return dao.getArticleTotalCount(boardNumber);
	}
	@Override
	public List<Message> getArticleDetails(int articleNumber) {
		return dao.getArticleDetails(articleNumber);
	}
	
	/*Admin*/
	@Override
	public List<Message> getAllArticles() {
		return dao.getAllArticles();
	}
	@Override
	public void removeArticle(int articleNum) {
		System.out.println("in BO");
		dao.removeArticle(articleNum);
	}	

	/*Traffic*/
	@Override
	public List<Traffic> getTrafficData() {
		return dao.getTrafficData();
	}
	@Override
	public int getTrafficCount() {
		return dao.getTrafficCount();
	}
	
	/*Manager*/
	@Override
	public int checkAdminLogin(String managerId, String managerPwd) {
		return dao.checkAdminLogin(managerId, managerPwd);
	}
	
	//SuJi
	@Override
	public int MessageInsertProcess(Message dto) {
		return dao.message_insert(dto);
	}
	@Override
	public void FileInsertProcess(List<File_uploadDTO> fList) {
		dao.file_insert(fList);
	}


}

