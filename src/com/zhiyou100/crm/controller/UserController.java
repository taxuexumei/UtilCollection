package com.zhiyou100.crm.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiyou100.crm.model.Department;
import com.zhiyou100.crm.model.Jsonresult;
import com.zhiyou100.crm.model.Role;
import com.zhiyou100.crm.model.Shop;
import com.zhiyou100.crm.model.User;
import com.zhiyou100.crm.service.DepartmentService;
import com.zhiyou100.crm.service.RoleService;
import com.zhiyou100.crm.service.TransactionService;
import com.zhiyou100.crm.service.UserService;
import com.zhiyou100.crm.util.BeanUtil;
import com.zhiyou100.crm.util.Pager;
import com.zhiyou100.crm.util.SessionKey;

@Controller
@RequestMapping("/user")
public class UserController {

	/*
	 * 404
	 * 1 @Controller 没加
	 *  
	 * 2 地址写错了
	 * 
	 * 3 返回页面找不到也会404
	 * 
	 * 4 拦截路径写错的话也会404
	 * 
	 *  
	 */
	
	// http://blog.csdn.net/u010003835/article/details/51417644  注入複雜對象
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private RoleService roleservice;
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transervice;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String userAddindex(ModelMap model)
	{

		List<Department> departments = departmentService.getAllDepartmen();
		List<Role> roles = roleservice.getAllRole();
		model.addAttribute("departments", departments);
		model.addAttribute("roles", roles);
		return "/user/add";
		
	}
	
	
	/*
	 *  @RequestMapping(value="/user/{userId}/roles/{roleId}",method = RequestMethod.GET) 
	 *  public String getLogin(@PathVariable("userId") String userId,  
         @PathVariable("roleId") String roleId){  
         }
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String userAdd(User user,ModelMap model)
	{
		user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
		user.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
		user.setIsSystem(Boolean.valueOf("0"));
		if (userService.addUser(user)) {
			
			return "redirect:/user/list";
			
		}
		else {
			model.addAttribute("user", user);
			
			return "/user/add";
		}
	}

	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String userUpdateIndex(HttpServletRequest request,ModelMap model){
		List<Department> allDepartmen = departmentService.getAllDepartmen();
		List<Role> allRole = roleservice.getAllRole();
		String id = request.getParameter("id");
		User user = userService.getUserById(Integer.valueOf(id));
		model.addAttribute("departments", allDepartmen);
		model.addAttribute("roles", allRole);
		model.addAttribute("user", user);
		
		return "/user/update";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String userUpdate(HttpServletRequest request,ModelMap model) {
		
		User user = (User) BeanUtil.parseRequestToBean(request, User.class);
		user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
		user.setIsSystem(Boolean.valueOf("0"));
		if (userService.getUpdateUser(user)) {
			
//			response.sendRedirect(request.getContextPath()+"/user/list");
			return "redirect:/user/list";
		}
		return null;
	}
	
	@RequestMapping(value="/remove" ,method=RequestMethod.GET)
	public String removeUserIndex(Model model , @RequestParam(value="id",required=false,defaultValue="")  String id){
		
//		User users = userService.getUserById(Integer.valueOf(id));
//		获取id之后，直接通过id删除
		User user = userService.removeUserById(Integer.valueOf(id));
		return "redirect:/user/list";
	}
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String userListIndex(ModelMap model) {
		
		String keyword = "";
		String field = "";
		String pageNo = "1";
		int page = 1;      
		
		// 第一步，要获取总条数
		int total = userService.getTotalCount(keyword, field);
		
		Pager pager = new Pager(total, page);
		// 第二步，分页取数据
		List<User> users = userService.getUsersByKeywordAndFieldAndPage(keyword, field, pager);
	    model.addAttribute("list", users);
	    model.addAttribute("pager", pager);
        return "/user/list";
		
	}
	
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public String userList(ModelMap model,@RequestParam(value="keyword",required=false,defaultValue="") String keyword,String searchField,@RequestParam(value="pageNo",required=false,defaultValue="1") String pageNo) {
		     model.addAttribute("keyword", keyword);
		     model.addAttribute("field", searchField);
		     model.addAttribute("pageNo", pageNo);
		     int page = 1; 
		     if (!pageNo.equals("")) {
					page = Integer.parseInt(pageNo);
				}
		  // 第一步，要获取总条数
				int total = userService.getTotalCount(keyword, searchField);
				Pager pager = new Pager(total, page);
				// 第二步，分页取数据
				List<User> users = userService.getUsersByKeywordAndFieldAndPage(keyword, searchField, pager);
			    model.addAttribute("list", users);
			    model.addAttribute("pager", pager);
		        return "/user/list";
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String userLogin(HttpServletRequest request,String username,String password,ModelMap model)
	{
		  User user = userService.getUserByUserName(username);

		  if (user == null) {
			
			  model.addAttribute("errorMessage", "用户名不存在!");
			  model.addAttribute("username", username);
			  model.addAttribute("password", password);
			  return "/index";
			  

		}
		 else {
			
			 if (!password.equals(user.getPassword())) {
				 
					
				  model.addAttribute("errorMessage", "密碼錯誤!");
				  model.addAttribute("username", username);
				  model.addAttribute("password", password);
				  return "/index";

			}
			 else {
				
				 HttpSession session = request.getSession();
				 session.setAttribute(SessionKey.USER, user);
				 session.setAttribute(SessionKey.USERNAME, user.getUsername());
				 return "redirect:/admin";
				 
				 
			}
			 
		}
	}
	
	@RequestMapping(value="/shops")
	@ResponseBody
	public Jsonresult getShops()
	{
		// webservice 用的比较多的是xml 这种数据格式
		// 但绝大多数和前端提供接口都要返回json 数据
		Jsonresult jsresult = new Jsonresult();
		
		List<Shop> shops = new ArrayList<>();
		
		Shop shop = new Shop(1, "图书音像", "楚门的世界", 120);
		Shop shop1 = new Shop(2, "电子产品", "三星", 1200);
		Shop shop2 = new Shop(3, "电子产品", "华为", 12000);
		Shop shop3 = new Shop(4, "图书音像", "平凡的世界", 120);

		shops.add(shop3);
		shops.add(shop2);
		shops.add(shop1);
		shops.add(shop);
		jsresult.setErrroCode("0");
		
		jsresult.setErrMessage("请求成功");
		
		jsresult.setContent(shops);
	
		return jsresult;
	}
	
	
	
	
	
	
}
