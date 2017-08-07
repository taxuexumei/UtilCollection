package com.zhiyou100.crm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static void main(String[] args) {

		transferToNomal("user_name_sex");
	}
	
	
	public static String transferToNomal(String param) {
		// trim方法去空格  如果字符串是null 值  或者是空串的话 直接返回空
		 if (param==null||"".equals(param.trim())){  
	           return "";  
	       }
		 // String  StringBuffer  StringBuilder 的区别
	       StringBuilder sb=new StringBuilder(param);  
	       Matcher mc= Pattern.compile("_").matcher(param); 
	       
	       int i=0;
	       // mc.end() 找到匹配字符串的后面的那一个位置
	       while (mc.find()){ 
	    	   int end = mc.end();
	           int position=end-(i++); 
	           sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());  

	       }  
	       return sb.toString();  
		
	}

}
