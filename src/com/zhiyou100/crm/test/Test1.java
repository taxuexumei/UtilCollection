package com.zhiyou100.crm.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Test1 {

	public static void main(String[] args) {

		
		ApplicationContext con = new ClassPathXmlApplicationContext("applicationContext.xml");
		//JdbcTemplate jdbc = (JdbcTemplate) con.getBean("jdbcTemplate");
	}

}
