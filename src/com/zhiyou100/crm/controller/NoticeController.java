/**
 * @Title: NoticeController.java 
 * @Prject: javasite
 * @Package: com.zhiyou100.crm.controller 
 * @Description: TODO
 * @author: Liu Hao  
 * @date: 2017年8月1日 下午9:58:56 
 * @version: V1.0   
 */
package com.zhiyou100.crm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import org.eclipse.jdt.internal.compiler.ast.FalseLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zhiyou100.crm.model.Department;
import com.zhiyou100.crm.model.Notice;
import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.service.DepartmentService;
import com.zhiyou100.crm.service.NoticeService;
import com.zhiyou100.crm.service.UserService;
import com.zhiyou100.crm.util.BeanUtil;
import com.zhiyou100.crm.util.DBUtil;
import com.zhiyou100.crm.util.Pager;
import com.zhiyou100.crm.util.SessionKey;

/** 
 * @ClassName: NoticeController 
 * @Description: TODO
 * @author: Liu Hao
 * @date: 2017年8月1日 下午9:58:56  
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController{

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private  DepartmentService departmentService;
	@Autowired
	private UserService userService;
//===================================================================	
//	显示notice的list信息
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String getNoticeListIndex(HttpServletRequest request,Model model ){
		String keyword="";
		String field = "";
		
		int page =1;
		User user = (User) request.getSession().getAttribute(SessionKey.USER);
//		获取总的条数
		int totalCount = noticeService.getTotalcount(keyword, field, user);
//		分页取得数据
		Pager pager = new Pager(totalCount, page);
		model.addAttribute("pager", pager);
		List<Notice> list = noticeService.getNoticeByKeywordAndfieldAndPage(keyword, field, pager, user);
		model.addAttribute("list", list);
		return "/notice/list";
	}
	
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public String getNoticeList(HttpServletRequest request,Model model){
		String keyword = request.getParameter("keyword");
		String field = request.getParameter("field");
		String pageNo = request.getParameter("pageNo");
		model.addAttribute("keyword", keyword);
		model.addAttribute("field", field);
		model.addAttribute("pageNo", pageNo);
		List<Department> allDepartmen = departmentService.getAllDepartmen();
		model.addAttribute("department", allDepartmen);
		List<User> allUser = userService.getAllUser();
		model.addAttribute("user", allUser);
		int page = 1; 
		if (!pageNo.equals("")) {
			page = Integer.parseInt(pageNo);
		}
		
		User user = (User) request.getSession().getAttribute(SessionKey.USER);
		 // 第一步，要获取总条数
		int total = noticeService.getTotalcount(keyword, field,user);
		Pager pager = new Pager(total, page);
		// 第二步，分页取数据
		List<Notice> notices = noticeService.getNoticeByKeywordAndfieldAndPage(keyword, field, pager,user);
	    model.addAttribute("list", notices);
	    model.addAttribute("pager", pager);
//	    model.addAttribute("user", user);
        return "/notice/list";
	}
//	===========================================================
//	添加操作
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addNoticeIndex(Model model){
		return "/notice/add";
	}
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addNotice(HttpServletRequest request,Model model){
		Notice notices =  (Notice) BeanUtil.parseRequestToBean(request, Notice.class);
		noticeService.AddNotice(notices);
		return "redirect:/notice/list";
	}
	
	
//	============================================================
//	更新操作
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String updateNoticeIndex(Model model,@RequestParam(value="id",required=false,defaultValue="") String id){
//		在get方法中完成update的回显操作
		Notice notice = noticeService.getNoticeById(Integer.valueOf(id));
		model.addAttribute("notice", notice);
		return "/notice/update";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateNotice(HttpServletRequest request ,Model model){
		Notice notice = (Notice) BeanUtil.parseRequestToBean(request, Notice.class);
		List<Notice> list = noticeService.updateNotice(notice);
		
		model.addAttribute("notice", list);
		return "redirect:/notice/list";
	}
//	=============================================================
//	删除操作
	@RequestMapping(value="/remove",method=RequestMethod.GET)
	public String removeNotice(Model model,@RequestParam(value="id" , required=false ,defaultValue="") String id){
		Notice notice = noticeService.removeRoleById(Integer.valueOf(id));
		model.addAttribute("list", notice);
		return "redirect:/notice/list";
	}
	
}
