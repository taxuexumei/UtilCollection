package com.zhiyou100.crm.dao.Impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.zhiyou100.crm.dao.DepartMentDao;
import com.zhiyou100.crm.model.Department;
import com.zhiyou100.crm.util.DBUtil;

@Repository
/*@Scope(value="singleton")
prototype*/
public class DepartMentDaoImpl implements DepartMentDao {

	@Override
	public List<Department> getAllDepartmen() {
		
		String sql = "select * from department";
		Object[] paramters = {};
		List<Department> departments = (List<Department>) DBUtil.queryForList(sql, paramters, Department.class);
		return departments;
		
	}

	

}
