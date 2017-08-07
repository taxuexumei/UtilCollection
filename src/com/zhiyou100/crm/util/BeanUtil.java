package com.zhiyou100.crm.util;

import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class BeanUtil {

	public static Object parseRequestToBean(HttpServletRequest request, Class<?> clz) {
		Object obj = null;
		// key 请求参参数名 value 请求参数值
		//{name:["zhangsa"],gender:["男"],birthDay:["2017-09-08"]}
		Map<String, String[]> paramaterMap = request.getParameterMap();
		try {
			obj = clz.newInstance();
			for (Map.Entry<String, String[]> entry : paramaterMap.entrySet()) {
				// 请求参数名
				String name = entry.getKey();
				// 请求参数值
				String[] values = entry.getValue();
				// 请求参数名要和对象属性的保持，约定大于编码
				Field field = clz.getDeclaredField(name);
				field.setAccessible(true);
				String filedTypeName = field.getType().getSimpleName();
				if (filedTypeName.equals("int")) {
					field.set(obj, Integer.parseInt(values[0]));
				} else if (filedTypeName.equals("Integer")) {
					field.set(obj, Integer.valueOf(values[0]));
				} else if (filedTypeName.equals("Date")) {

					field.set(obj, DateUtil.stringToDate(values[0]));
				} else if (filedTypeName.equals("Boolean")) {
					field.set(obj,Boolean.valueOf(values[0]) );
					
				}
				else {
					//
					field.set(obj, values[0]);

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return obj;
	}

}
