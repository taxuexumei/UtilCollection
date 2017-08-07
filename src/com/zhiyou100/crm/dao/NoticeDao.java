/**
 * @Title: NoticeDao.java 
 * @Prject: WTCRM
 * @Package: com.zhiyou100.crm.dao 
 * @Description: TODO
 * @author: Liu Hao  
 * @date: 2017年7月27日 下午2:25:25 
 * @version: V1.0   
 */
package com.zhiyou100.crm.dao;

import java.util.List;

import com.zhiyou100.crm.model.Notice;
import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.util.Pager;

/** 
 * @ClassName: NoticeDao 
 * @Description: TODO
 * @author: Liu Hao
 * @date: 2017年7月27日 下午2:25:25  
 */
public interface NoticeDao {

	public int getTotalcount(String keyword, String field,User user);
	public List<Notice> getNoticeByKeywordAndfieldAndPage(String keyword, String field,Pager page,User user);
	public List<Notice> AddNotice(Notice notice);
	public Notice getNoticeById(Integer id);
	public List<Notice> updateNotice(Notice notice);
	public Notice removeRoleById(Integer id);
}
