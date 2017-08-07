package com.zhiyou100.crm.filter;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request;
	public EncodingRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		
		String value = this.request.getParameter(name) ;
		if (value != null) {
			try {
				value = new String(value.getBytes("ISO8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		return value;
	}
	
	
	@Override
	public Map<String, String[]> getParameterMap() {
		
		Map<String, String[]> paramaterMap = this.request.getParameterMap();
	    Map<String, String[]> nomalMap = new HashMap<>();
		 for (Map.Entry<String, String[]> entry:paramaterMap.entrySet()) {
				String name = entry.getKey();
				String[] values = entry.getValue();
				String[] nomalValues = new String[values.length];
				for (int i = 0; i < values.length; i++) {
					
					try {
						nomalValues[i] = new String(values[i].getBytes("ISO8859-1"), "utf-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				nomalMap.put(name, nomalValues);
				
			}
			
		return nomalMap;
		
	}

}
