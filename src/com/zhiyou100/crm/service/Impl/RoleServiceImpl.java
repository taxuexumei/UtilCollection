package com.zhiyou100.crm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhiyou100.crm.dao.RoleDao;
import com.zhiyou100.crm.dao.Impl.RoleDaoImpl;
import com.zhiyou100.crm.model.Role;
import com.zhiyou100.crm.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;
	
	@Override
	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		return roleDao.getAll();
	}

}
