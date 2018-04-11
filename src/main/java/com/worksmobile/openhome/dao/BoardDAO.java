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
	public List<Message> getArticleList(int boardNumberInt, int currentPageNo, int pageSize);
	public List<Message> getCount(int boardNumberInt);
	public List<Message> getArticleDetails(int originalMessageNum);

	/*Traffic*/
	public List<Traffic> funcGetTraffic();
	public List<Traffic> funcGetTrafficCount();
	
	public int message_insert(Message dto);
	public void file_insert(List<File_uploadDTO> fList);
}
