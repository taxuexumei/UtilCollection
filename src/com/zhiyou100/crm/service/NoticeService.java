/**
 * @Title: NoticeService.java 
 * @Prject: WTCRM
 * @Package: com.zhiyou100.crm.service 
 * @Description: TODO
 * @author: Liu Hao  
 * @date: 2017年7月27日 下午2:20:30 
 * @version: V1.0   
 */
package com.zhiyou100.crm.service;

import java.util.List;

import com.zhiyou100.crm.model.Notice;
import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.util.Pager;

/** 
 * @ClassName: NoticeService 
 * @Description: TODO
 * @author: Liu Hao
 * @date: 2017年7月27日 下午2:20:30  
 */
public interface NoticeService {

	public int getTotalcount(String keyword, String field,User user);
	public List<Notice> getNoticeByKeywordAndfieldAndPage(String keyword, String field,Pager page,User user);
	public List<Notice> AddNotice(Notice notice);
	public Notice getNoticeById(Integer id);
	public List<Notice> updateNotice(Notice notice);
	public Notice removeRoleById(Integer id);
}
