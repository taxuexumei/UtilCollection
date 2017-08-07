package com.zhiyou100.crm.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.Range;

// 注意为了让保存在Session中的模型数据比较稳定，需要支持序列化
public class User implements Serializable  {
	private static final long serialVersionUID = 1L;
	int userId;
	String username;
	String password;
	Boolean isAdmin;
	Boolean isSystem;
	int departmentId;
	String departmentName;
	int roleId;
	String roleName;
	Boolean isMale;
	String mobile;
	String address;
	@Range(min=1,max=100,message="{age.error}")
	int age;
	String tel;
	String idNum;
	String email;
	String qq;
	String hobby;
	int education;
	String cardNum;
	String nation;
	int marry;
	int status;
	String remark;
	Timestamp createTime;
	int creater;
	Timestamp updateTime;
	int updater;
	
	// 重写toString方法，便于打印日志观察对象内部的数据
	@Override
	public String toString() {
		return "{" +
				"userId:" + this.userId + ", " +
				"username:" + this.username + ", " +
				"password:" + this.password + ", " +
				"isAdmin:" + this.isAdmin + ", " + 
				"isSystem:" + this.isSystem + ", " + 
				"departmentId:" + this.departmentId + ", " + 
				"departmentName:" + this.departmentName + ", " + 
				"roleId:" + this.roleId + ", " + 
				"roleName:" + this.roleName + ", " + 
				"isMale:" + this.isMale + ", " + 
				"mobile:" + this.mobile + ", " + 
				"address:" + this.address + ", " + 
				"age:" + this.age + ", " + 
				"tel:" + this.tel + ", " + 
				"idNum:" + this.idNum + ", " + 
				"email:" + this.email + ", " + 
				"qq:" + this.qq + ", " + 
				"hobby:" + this.hobby + ", " + 
				"education:" + this.education + ", " + 
				"cardNum:" + this.cardNum + ", " + 
				"nation:" + this.nation + ", " + 
				"marry:" + this.marry + ", " + 
				"status:" + this.status + ", " +
				"remark:" + this.remark + "," +
 				"createTime:" + this.createTime + ", " +
				"creater:" + this.creater + ", " +
				"updateTime:" + this.updateTime + ", " +
				"updater:" + this.updater +
				"}";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getRoleId() {
		return roleId;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Boolean getIsMale() {
		return isMale;
	}

	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public int getEducation() {
		return education;
	}

	public void setEducation(int education) {
		this.education = education;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public int getMarry() {
		return marry;
	}

	public void setMarry(int marry) {
		this.marry = marry;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getCreater() {
		return creater;
	}

	public void setCreater(int creater) {
		this.creater = creater;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public int getUpdater() {
		return updater;
	}

	public void setUpdater(int updater) {
		this.updater = updater;
	}
	
	
	
}
