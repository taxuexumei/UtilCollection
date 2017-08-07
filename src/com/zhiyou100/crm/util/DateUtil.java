package com.zhiyou100.crm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zhiyou100.crm.common.Myconstant;



public class DateUtil {

	// 字符串转日期对象
	public static java.sql.Date stringToDate(String dateString) {
		// yyyy-MM-dd
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(Myconstant.yyyyMMdd);
		try {
			date = format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new java.sql.Date(date.getTime());
	}
	
	
	public static String dateToString(java.sql.Date date) {
		
		SimpleDateFormat format = new SimpleDateFormat(Myconstant.yyyyMMdd);
		String dateString = format.format(date);
	
		return dateString;
	}
	
	
}
