package com.zhiyou100.crm.service.Impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.crm.dao.UserDao;
import com.zhiyou100.crm.dao.Impl.UserDaoImpl;
import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.service.UserService;
import com.zhiyou100.crm.util.Pager;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao ;

	@Override
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserName(userName);
	}

	@Override
	public int getTotalCount(String keyword, String field) {
		
		return userDao.getTotalCount(keyword, field);
	}

	@Override
	public List<User> getUsersByKeywordAndFieldAndPage(String keyword, String field, Pager pager) {
		// TODO Auto-generated method stub
		return userDao.getUsersByKeywordAndFieldAndPage(keyword, field, pager);
	}

	@Override
	public boolean addUser(User user) {
	
		return userDao.addUser(user);
	}

	/* (non Javadoc) 
	 * @Title: getUserById
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhiyou100.crm.service.UserService#getUserById(java.lang.Integer) 
	 */
	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	/* (non Javadoc) 
	 * @Title: getUpdateUser
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.zhiyou100.crm.service.UserService#getUpdateUser(com.zhiyou100.crm.model.User) 
	 */
	@Override
	public boolean getUpdateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.getUpdateUser(user);
	}

	/* (non Javadoc) 
	 * @Title: removeUserById
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhiyou100.crm.service.UserService#removeUserById(java.lang.Integer) 
	 */
	@Override
	public User removeUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.removeUserById(id);
	}

	/* (non Javadoc) 
	 * @Title: getAllUser
	 * @Description: TODO
	 * @return 
	 * @see com.zhiyou100.crm.service.UserService#getAllUser() 
	 */
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}
	

	
}
