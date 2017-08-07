package com.zhiyou100.crm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.crm.dao.DepartMentDao;
import com.zhiyou100.crm.dao.Impl.DepartMentDaoImpl;
import com.zhiyou100.crm.model.Department;
import com.zhiyou100.crm.service.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartMentDao departmentDao ;
	@Override
	public List<Department> getAllDepartmen() {
		
		return departmentDao.getAllDepartmen();
	}

}
