/*
 * Application java
 * @Author : Youngho Jo
 *           Suji    Jang
 */
package com.worksmobile.openhome.dao;

import java.util.List;

import com.worksmobile.openhome.model.File_uploadDTO;
import com.worksmobile.openhome.model.Message;
import com.worksmobile.openhome.model.Traffic;

public interface BoardDAO {
	
	/*Board*/
	public List<Message> getArticleList(int boardNumber, int currentPageNo, int pageSize);
	public int getBoardTotalCount(int boardNumber);
	public List<Message> getArticleDetails(int articleNumber);

	/*Traffic*/
	public List<Traffic> getTrafficData();
	public int getTrafficCount();
	
	public int message_insert(Message dto);
	public void file_insert(List<File_uploadDTO> fList);
}
