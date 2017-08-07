/**
 * @Title: NoticeServiceImpl.java 
 * @Prject: WTCRM
 * @Package: com.zhiyou100.crm.service.Impl 
 * @Description: TODO
 * @author: Liu Hao  
 * @date: 2017年7月27日 下午2:26:44 
 * @version: V1.0   
 */
package com.zhiyou100.crm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.crm.dao.NoticeDao;
import com.zhiyou100.crm.dao.Impl.NoticeDaoImpl;
import com.zhiyou100.crm.model.Notice;
import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.service.NoticeService;
import com.zhiyou100.crm.util.Pager;

/** 
 * @ClassName: NoticeServiceImpl 
 * @Description: TODO
 * @author: Liu Hao
 * @date: 2017年7月27日 下午2:26:44  
 */
@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public int getTotalcount(String keyword, String field,User user) {
		// TODO Auto-generated method stub
		return noticeDao.getTotalcount(keyword, field , user);
	}
	
	@Override
	public List<Notice> getNoticeByKeywordAndfieldAndPage(String keyword, String field, Pager page,User user) {
		// TODO Auto-generated method stub
		return noticeDao.getNoticeByKeywordAndfieldAndPage(keyword, field, page,user);
	}

	/* (non Javadoc) 
	 * @Title: AddNotice
	 * @Description: TODO
	 * @param notice
	 * @return 
	 * @see com.zhiyou100.crm.service.NoticeService#AddNotice(com.zhiyou100.crm.model.Notice) 
	 */
	@Override
	public List<Notice> AddNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.AddNotice(notice);
	}

	/* (non Javadoc) 
	 * @Title: getNoticeById
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhiyou100.crm.service.NoticeService#getNoticeById(java.lang.Integer) 
	 */
	@Override
	public Notice getNoticeById(Integer id) {
		// TODO Auto-generated method stub
		return noticeDao.getNoticeById(id);
	}

	/* (non Javadoc) 
	 * @Title: updateNotice
	 * @Description: TODO
	 * @param notice
	 * @return 
	 * @see com.zhiyou100.crm.service.NoticeService#updateNotice(com.zhiyou100.crm.model.Notice) 
	 */
	@Override
	public List<Notice> updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		return noticeDao.updateNotice(notice);
	}

	/* (non Javadoc) 
	 * @Title: removeRoleById
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhiyou100.crm.service.NoticeService#removeRoleById(java.lang.Integer) 
	 */
	@Override
	public Notice removeRoleById(Integer id) {
		// TODO Auto-generated method stub
		return noticeDao.removeRoleById(id);
	}

}
