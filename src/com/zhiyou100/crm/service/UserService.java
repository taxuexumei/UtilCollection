package com.zhiyou100.crm.service;

import java.util.List;

import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.util.Pager;

public interface UserService {
	 
	User getUserByUserName(String userName);
	
	int getTotalCount(String keyword,String field);
	List<User> getUsersByKeywordAndFieldAndPage(String keyword,String field ,Pager pager);
	boolean addUser(User user);
	public User getUserById(Integer id);
	public boolean getUpdateUser(User user);
	public User removeUserById(Integer id);
	public List<User> getAllUser();
}
