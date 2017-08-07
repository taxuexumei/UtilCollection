package com.zhiyou100.crm.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zhiyou100.crm.dao.RoleDao;
import com.zhiyou100.crm.model.Role;
import com.zhiyou100.crm.util.DBUtil;

@Repository(value="roleDao")
public class RoleDaoImpl implements RoleDao{

	@Override
	public List<Role> getAll() {
		String sql = "select * from role";
		Object[] paramters = {};
		List<Role> roles = (List<Role>) DBUtil.queryForList(sql, paramters, Role.class);
		return roles;
	}

	

}
