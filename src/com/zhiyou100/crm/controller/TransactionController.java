package com.zhiyou100.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyou100.crm.service.TransactionService;

@Controller
@RequestMapping("/test")
public class TransactionController {

	@Autowired
	private TransactionService transervice;
	
	@RequestMapping("/transaction")
	/*
	 * 形参的名字要和请求参数名字保持一致
	 */
	
   public String transferMoney(Integer userAId, Integer userBId,Integer money) {
		
		try {
			transervice.transferMoney(userAId, userBId, money);

		} catch (Exception e) {
			e.printStackTrace();
		}


		return "redirect:/";
	}
	
}
