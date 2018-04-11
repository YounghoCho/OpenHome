/*
 * Application java
 * @Author : Youngho Jo
 *           Suji    Jang
 */
package com.worksmobile.openhome.BO;

import java.util.List;

import org.springframework.stereotype.Service;

import com.worksmobile.openhome.model.File_uploadDTO;
import com.worksmobile.openhome.model.Message;
import com.worksmobile.openhome.model.Traffic;

@Service
public interface OpenhomeBO {
	
	/*Board*/
	public List<Message> getArticleList(int boardNumber, int currentPageNo, int pageSize);
	public int getArticleTotalCount(int boardNumber);
	public List<Message> getArticleDetails(int articleNumber);

	/*Traffic*/
	public List<Traffic> getTrafficData();
	public int getTrafficCount();
	
	public int MessageInsertProcess(Message dto); //게시글 추가
	public void FileInsertProcess(List<File_uploadDTO> fList); //첨부파일 추가

	

}
