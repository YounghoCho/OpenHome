/*
 * Application java
 * @Author : Youngho Jo
 *           Suji    Jang
 */
package com.worksmobile.openhome.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.model.Board;
import com.worksmobile.openhome.model.File_uploadDTO;
import com.worksmobile.openhome.model.Message;
import com.worksmobile.openhome.model.Traffic;

@Service
public interface OpenhomeBO {
	/*Board*/
	public List<Board> getMenuList();
	public void removeBoard(int boardNum);	
	
	/*Article*/
	public List<Message> getArticleList(int boardNumber, int currentPageNo, int pageSize);
	public int getArticleTotalCount(int boardNumber);
	public List<Message> getArticleDetails(int articleNumber);
	public List<Message> getAllArticles();
	public void removeArticle(int articleNum);
	
	/*Traffic*/
	public List<Traffic> getTrafficData();
	public int getTrafficCount();
	
	/*Manager*/
	public int checkAdminLogin(String managerId, String managerPwd);

	//Suji
	public int MessageInsertProcess(Message dto); //게시글 추가
	public void FileInsertProcess(List<File_uploadDTO> fList); //첨부파일 추가

	

	

	

}
