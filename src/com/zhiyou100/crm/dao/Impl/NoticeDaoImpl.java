/**
 * @Title: NoticeDaoImpl.java 
 * @Prject: WTCRM
 * @Package: com.zhiyou100.crm.dao.Impl 
 * @Description: TODO
 * @author: Liu Hao  
 * @date: 2017年7月27日 下午2:26:02 
 * @version: V1.0   
 */
package com.zhiyou100.crm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhiyou100.crm.dao.NoticeDao;
import com.zhiyou100.crm.model.Notice;
import com.zhiyou100.crm.model.Role;
import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.util.DBUtil;
import com.zhiyou100.crm.util.Pager;

/** 
 * @ClassName: NoticeDaoImpl 
 * @Description: TODO
 * @author: Liu Hao
 * @date: 2017年7月27日 下午2:26:02  
 */
@Repository
public class NoticeDaoImpl implements NoticeDao {

	/* (non Javadoc) 
	 * @Title: getTotalcount
	 * @Description: TODO
	 * @param keyword
	 * @param field
	 * @return 
	 * @see com.zhiyou100.crm.dao.NoticeDao#getTotalcount(java.lang.String, java.lang.String) 
	 */
	@Override
	public int getTotalcount(String keyword, String field,User user) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
//		1.从数据库中获取所有的公告条数
		StringBuilder builder = new StringBuilder();
		/*
		 * # 当前登录用户 1 能看到没有过期，并且通知范围是全部的，没有被删除的公告
                
#              2 能看到没有过期，并且通知范围是你所在的部门，没有被删除的公告信息

#              3 能看到没有过期，并且是自己发布的公告
#              4 如果这个公告是自己创建的，那么就算公告过期自己也能看到
SELECT * FROM notice WHERE expire_time > NOW() AND (receive_id =0 OR receive_id=department_id OR creater=user_id) AND status=2
UNION ALL SELECT * FROM notice WHERE expire_time <= NOW() AND creater=user_id AND status=2

		 */
		builder.append("select count(a.notice_id) as total from (SELECT * FROM notice WHERE expire_time > NOW() AND"
				+ " (receive_id =0 OR receive_id=? OR creater=?) AND "
				+ "status=2 UNION ALL SELECT * FROM notice WHERE expire_time <= NOW() AND "
				+ "creater=? AND status=2)a");
		if (!keyword.equals("")) {
			builder.append("where a." + field + "like" + "'%"+keyword+"%'");
		}
//		链接数据库
		Connection con = DBUtil.getConnection();
//		获得预处理对象
		try {
			ps = con.prepareStatement(builder.toString());
			ps.setInt(1, user.getDepartmentId());
			ps.setInt(2, user.getUserId());
			ps.setInt(3, user.getUserId());
			rs = ps.executeQuery();
//			遍历结果集对象rs
			while (rs.next()) {
				int total = rs.getInt("total");
				return total;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/* (non Javadoc) 
	 * @Title: getNoticeByKeywordAndfieldAndPage
	 * @Description: TODO
	 * @param keyword
	 * @param field
	 * @param page
	 * @return 
	 * @see com.zhiyou100.crm.dao.NoticeDao#getNoticeByKeywordAndfieldAndPage(java.lang.String, java.lang.String, com.zhiyou100.crm.util.Pager) 
	 */
	@Override
	public List<Notice> getNoticeByKeywordAndfieldAndPage(String keyword, String field, Pager page,User user) {
		// TODO Auto-generated method stub
//		1.sql语句。
		StringBuilder builder = new StringBuilder();
		builder.append("select * from (SELECT * FROM notice WHERE expire_time > NOW() AND "
				+ "(receive_id =0 OR receive_id=? OR creater=?) AND "
				+ "status=2 UNION ALL SELECT * FROM notice WHERE expire_time <= NOW() "
				+ "AND creater=? AND status=2)m");
//			1).判断：keyword是否为空如果为空，跳过，不为空，则拼接字符串
		if (!keyword.equals("")) {
			builder.append("where m."+ field + "like" + "'%" + keyword + "%'");
		}
		String limit = " limit "+(page.getPageNo()-1)*page.getPageSize()+","+page.getPageSize();
//		String limit = " limit "+(pager.getPageNo()-1)*pager.getPageSize()+","+pager.getPageSize();
		builder.append(limit);
		String sql = builder.toString();
//		2.通过数据库语句将数据取出来，放在特定对象上。
		Object[] paramters = {user.getDepartmentId(),user.getUserId(),user.getUserId()};
		List<Notice> notices = (List<Notice>) DBUtil.queryForList(sql, paramters, Notice.class);
//		3.通过return把对象传递出去。
		return notices;
	}

	/* (non Javadoc) 
	 * @Title: AddNotice
	 * @Description: TODO
	 * @param notice
	 * @return 
	 * @see com.zhiyou100.crm.dao.NoticeDao#AddNotice(com.zhiyou100.crm.model.Notice) 
	 */
	@Override
	public List<Notice> AddNotice(Notice notice) {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		builder.append("insert into notice (`receive_id`,`subject`,`text`,`pub_time`,`expire_time`,`status`,`remark`"
				+ ",`create_time`,`creater`,`update_time`,`updater`) values (?,?,?,?,?,?,?,?,?,?,?)");
		Connection con = DBUtil.getConnection();
		List<Notice> notices = new ArrayList<>();
		Notice noticess = new Notice();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(builder.toString());
//			设置值
			ps.setInt(1, notice.getReceiveId());
			ps.setString(2, notice.getSubject());
			ps.setString(3, notice.getText());
			ps.setTimestamp(4, notice.getPubTime());
			ps.setTimestamp(5, notice.getExpireTime());
			ps.setInt(6, 2);
			ps.setString(7, notice.getRemark());
			ps.setTimestamp(8, notice.getCreateTime());
			ps.setInt(9, notice.getCreater());
			ps.setTimestamp(10, notice.getUpdateTime());
			ps.setInt(11, notice.getUpdater());

			int rowCount = ps.executeUpdate();
			
//			return rowCount > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non Javadoc) 
	 * @Title: getNoticeById
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhiyou100.crm.dao.NoticeDao#getNoticeById(java.lang.Integer) 
	 */
	@Override
	public Notice getNoticeById(Integer id) {
		String sql = "select * from notice where notice_id=?";
		Object[] paramters = {id};
		List<Notice> notices = (List<Notice>) DBUtil.queryForList(sql, paramters, Notice.class);
//		只有一个值，所以返回0
		Notice notice = notices.get(0);
		return notice;
	}

	/* (non Javadoc) 
	 * @Title: updateNotice
	 * @Description: TODO
	 * @param notice
	 * @return 
	 * @see com.zhiyou100.crm.dao.NoticeDao#updateNotice(com.zhiyou100.crm.model.Notice) 
	 */
	@Override
	public List<Notice> updateNotice(Notice notice) {
		// TODO Auto-generated method stub
		String sql = "update notice set pub_time=?,expire_time=?,subject=?,receive_id=?,"
				+ "update_time=? where notice_id=?";
		PreparedStatement ps = null;
		Connection con = DBUtil.getConnection();
		try {
			ps = con.prepareStatement(sql);
			ps.setTimestamp(1, notice.getPubTime());
			ps.setTimestamp(2, notice.getExpireTime());
			ps.setString(3, notice.getSubject());
			ps.setInt(4, notice.getReceiveId());
			ps.setTimestamp(5, notice.getUpdateTime());
			ps.setInt(6, notice.getNoticeId());
			
			ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non Javadoc) 
	 * @Title: removeRoleById
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhiyou100.crm.dao.NoticeDao#removeRoleById(java.lang.Integer) 
	 */
	@Override
	public Notice removeRoleById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "delete from notice where notice_id=?";
		Connection con = DBUtil.getConnection();
		PreparedStatement ps= null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
