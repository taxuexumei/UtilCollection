package com.zhiyou100.crm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhiyou100.crm.dao.UserDao;
import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.util.DBUtil;
import com.zhiyou100.crm.util.Pager;

import sun.security.pkcs11.Secmod.DbMode;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public User getUserByUserName(String userName) {
	    
		String sql = "select u.*,d.department_name,r.role_name from user as u left join department as d on (u.department_id=d.department_id) "
				+ " left join role as r on (u.role_id=r.role_id) where username=?";
		Object[] paramters = {userName};
		
		List<User> users = (List<User>) DBUtil.queryForList(sql, paramters, User.class);
		
		if (users.size() > 0) {
			return users.get(0);
		}
		else {
			return null;
		}
		
	}

	@Override
	public int getTotalCount(String keyword, String field) {
		
		StringBuilder builder = new StringBuilder();
		builder.append("select count(*) as total from user as u where status=2 and is_system=0 ");
		if (!keyword.equals("")) {
			builder.append("and u."+field +" like "+"'%"+keyword+"%'");
		}
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			 ps = con.prepareStatement(builder.toString());
			 rs = ps.executeQuery();
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

	@Override
	public List<User> getUsersByKeywordAndFieldAndPage(String keyword, String field, Pager pager) {
		StringBuilder builder = new StringBuilder();
		builder.append("select u.*,d.department_name,r.role_name from user as u left join department as d on "+
		  "(u.department_id = d.department_id) left join role as r on (u.role_id=r.role_id) where u.status=2 and u.is_system=0 "
		);
		
		if (!keyword.equals("")) {
			builder.append("and u."+field +" like "+"'%"+keyword+"%'");
		}
		String limit = " limit "+(pager.getPageNo()-1)*pager.getPageSize()+","+pager.getPageSize();
		builder.append(limit);
		String sql = builder.toString();
		System.out.println(sql);
		Object[] paramters = {};
		List<User> users = (List<User>) DBUtil.queryForList(sql, paramters, User.class);
		return users;
	}

	@Override
	public boolean addUser(User user) {
		String sql = "INSERT INTO user (`username`, `password`, `department_id`, `role_id`, `is_male`, `mobile`, " +
				"`address`, `age`, `tel`, `id_num`, `email`, `qq`, " +
				"`hobby`, `education`, `card_num`, `nation`, `marry`, `status`, " + 
				"`remark`, `create_time`, `creater`,`is_system`"
				+ ") VALUES (?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?,?)";
		Connection con = DBUtil.getConnection();
		PreparedStatement s = null;
		try {
			s = con.prepareStatement(sql);
			s.setString(1, user.getUsername());
			s.setString(2, user.getPassword());
			s.setInt(3, user.getDepartmentId());
			s.setInt(4, user.getRoleId());
			s.setBoolean(5, user.getIsMale());
			s.setString(6, user.getMobile());
			s.setString(7, user.getAddress());
			s.setInt(8, user.getAge());
			s.setString(9, user.getTel());
			s.setString(10, user.getIdNum());
			s.setString(11, user.getEmail());
			s.setString(12, user.getQq());
			s.setString(13, user.getHobby());
			s.setInt(14, user.getEducation());
			s.setString(15, user.getCardNum());
			s.setString(16, user.getNation());
			s.setInt(17, user.getMarry());
			s.setInt(18, 2);
			s.setString(19, user.getRemark());
			s.setTimestamp(20, user.getCreateTime());
			s.setInt(21, user.getCreater());
			s.setBoolean(22, user.getIsSystem());
			// 增加，修改，删除都是调用executeUpdate方法
			int rowCount = s.executeUpdate();
			
			return rowCount > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtil.release(con, s, null);
		}
		
		
		return false;
	}

	/* (non Javadoc) 
	 * @Title: getUserById
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhiyou100.crm.dao.UserDao#getUserById(java.lang.Integer) 
	 */
	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		String sql = "select * from user where user_id= ?";
		Object[] paramters ={id};
		List<User> users = (List<User>) DBUtil.queryForList(sql, paramters, User.class);
		User user = users.get(0);
		return user;
	}

	/* (non Javadoc) 
	 * @Title: getUpdateUser
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.zhiyou100.crm.dao.UserDao#getUpdateUser(com.zhiyou100.crm.model.User) 
	 */
	@Override
	public boolean getUpdateUser(User user) {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		builder.append("update user set password=?,department_id=?,role_id=?,"
				+ "is_male=?,mobile=?,address=?,age=?,tel=?,id_num=?,email=?,qq=?,hobby=?,"
				+ "education=?,card_num=?,nation=?,marry=?,status=?,remark=?,"
				+ "create_time=?,creater=?,is_system=? where user_id=?");
		Connection con = DBUtil.getConnection();
		PreparedStatement s = null;
		try {
			s = con.prepareStatement(builder.toString());
			s.setString(1, user.getPassword());
			s.setInt(2, user.getDepartmentId());
			s.setInt(3, user.getRoleId());
			s.setBoolean(4, user.getIsMale());
			s.setString(5, user.getMobile());
			s.setString(6, user.getAddress());
			s.setInt(7, user.getAge());
			s.setString(8, user.getTel());
			s.setString(9, user.getIdNum());
			s.setString(10, user.getEmail());
			s.setString(11, user.getQq());
			s.setString(12, user.getHobby());
			s.setInt(13, user.getEducation());
			s.setString(14, user.getCardNum());
			s.setString(15, user.getNation());
			s.setInt(16, user.getMarry());
			s.setInt(17, 2);
			s.setString(18, user.getRemark());
			s.setTimestamp(19, user.getCreateTime());
			s.setInt(20, user.getCreater());
			s.setBoolean(21, user.getIsSystem());
			s.setInt(22, user.getUserId());
			// 增加，修改，删除都是调用executeUpdate方法
			int rowCount = s.executeUpdate();
			
			return rowCount > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtil.release(con, s, null);
		}
		
		return false;
	}

	/* (non Javadoc) 
	 * @Title: removeUserById
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhiyou100.crm.dao.UserDao#removeUserById(java.lang.Integer) 
	 */
	@Override
	public User removeUserById(Integer id) {
		// TODO Auto-generated method stub
		String sql= "delete from user where user_id=?";
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtil.release(con, ps, null);
		}
		return null;
	}

	/* (non Javadoc) 
	 * @Title: getAllUser
	 * @Description: TODO
	 * @return 
	 * @see com.zhiyou100.crm.dao.UserDao#getAllUser() 
	 */
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		String sql = "select * from user";
		Object[] paramters = {};
		List<User> user =  (List<User>) DBUtil.queryForList(sql, paramters, User.class);
		return user;
	}


	
	  
}
