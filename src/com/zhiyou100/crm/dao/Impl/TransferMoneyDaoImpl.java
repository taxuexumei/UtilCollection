package com.zhiyou100.crm.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.zhiyou100.crm.dao.TransferMoneyDao;
import com.zhiyou100.crm.util.DBUtil;

@Repository
public class TransferMoneyDaoImpl extends JdbcDaoSupport implements TransferMoneyDao {

	/*
	 * @Resource和@Autowired
	 * @Resource和@Autowired都是做bean的注入时使用，其实@Resource并不是Spring的注解，它的包是javax.annotation.Resource，需要导入，但是Spring支持该注解的注入。
	 * 1、共同点

两者都可以写在字段和setter方法上。两者如果都写在字段上，那么就不需要再写setter方法。

2、不同点

（1）@Autowired

@Autowired为Spring提供的注解，需要导入包org.springframework.beans.factory.annotation.Autowired;
只按照byType注入。
@Autowired注解是按照类型（byType）装配依赖对象，默认情况下它要求依赖对象必须存在，如果允许null值，
可以设置它的required属性为false。如果我们想使用按照名称（byName）来装配，可以结合@Qualifier注解一起使用

	 * 
	 * 
	 * ①如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常。

       ②如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常。

       ③如果指定了type，则从上下文中找到类似匹配的唯一bean进行装配，找不到或是找到多个，都会抛出异常。

       ④如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配。

       @Resource的作用相当于@Autowired，只不过@Autowired按照byType自动注入
	 */
	//@Resource(type=JdbcTemplate.class)
	@Resource(name="jdbcTemplate")
	public void setJb(JdbcTemplate jb) {
	 super.setJdbcTemplate(jb);
	   
	}
	
	@Override
	public void transferMoney1(Integer userAId,Integer money) {
		
		String sql1 = "update transfer set money = money-? where userId=?";
		this.getJdbcTemplate().update(sql1, money,userAId);
		
	}

	@Override
	public void transferMoney2(Integer userBId, Integer money) {
		
   String sql2 =  "update transfer set money = money+? where userId=?";
		
   this.getJdbcTemplate().update(sql2, money,userBId);

	}

}
