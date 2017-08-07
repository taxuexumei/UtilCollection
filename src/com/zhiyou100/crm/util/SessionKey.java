package com.zhiyou100.crm.util;

// 注意：为了避免Session的key出现意外重复，我们把所有key都集中在这里
// 注意：需要改动的地方比较多
// UserFilter
// JSP
// LoginServlet
// 还要介绍在页面上导入类的方法以及EL表达式中的[]传字符串取值的方式
public class SessionKey {
	public static final String USERNAME = "ZY_CRM_SESSIONKEY_USERNAME";
	public static final String USER = "ZY_CRM_SESSIONKEY_USER";
}
