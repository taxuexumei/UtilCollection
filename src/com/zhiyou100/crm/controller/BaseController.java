package com.zhiyou100.crm.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.sql.Timestamp;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.zhiyou100.crm.util.DateUtil;
public class BaseController {
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtil.stringToDate(text));			
				
			}

		});
		
		binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(new Timestamp(DateUtil.stringToDate(text).getTime()));			
				
			}

		});
		
		
		
	}
	
	
}
