package com.zhiyou100.crm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.util.Pager;
// shiro(重要 会讲) springsrcurerity(重要 自学) springdata(重要 自学) springboot(重要 会讲) 

public interface UserDao {
	User getUserByUserName(String userName);
	int getTotalCount(String keyword,String field);
	List<User> getUsersByKeywordAndFieldAndPage(String keyword,String field ,Pager pager);
	boolean addUser(User user);
	public User getUserById(Integer id);
	public boolean getUpdateUser(User user);
	public User removeUserById(Integer id);
	public List<User> getAllUser();
}
