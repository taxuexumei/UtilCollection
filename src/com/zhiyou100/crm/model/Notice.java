package com.zhiyou100.crm.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Notice implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	int noticeId;
	int receiveId;
	String subject;
	String text;
	Timestamp pubTime;
	Timestamp expireTime;
	int status;
	String remark;
	Timestamp createTime;
	int creater;
	String createrName;
	Timestamp updateTime;
	int updater;
	
	@Override
	public String toString() {
		return "{" +
				"noticeId:" + this.noticeId + ", " +
				"receiveId:" + this.receiveId + ", " +
				"subject:" + this.subject + ", " +
				"text:" + this.text + ", " + 
				"pubTime:" + this.pubTime + ", " +
				"createTime:" + this.createTime + ", " +
				"expireTime:" + this.expireTime + ", " +
				"status:" + this.status + ", " +
				"remark:" + this.remark + ", " +
				"createTime:" + this.createTime + ", " +
				"creater:" + this.creater + ", " +
				"createrName:" + this.createrName + ", " +
				"updateTime:" + this.updateTime + ", " +
				"updater:" + this.updater +
				"}";
	}
	
	
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	public int getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(int receiveId) {
		this.receiveId = receiveId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getPubTime() {
		return pubTime;
	}
	public void setPubTime(Timestamp pubTime) {
		this.pubTime = pubTime;
	}
	public Timestamp getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Timestamp expireTime) {
		this.expireTime = expireTime;
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
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
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
