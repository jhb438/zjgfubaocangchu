package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: JtTempperson</p>
 * <p>Description:</p>
 * @author 
 */
public class JtTempperson extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	 /**频道Guid**/
	private String channelGuid;
	/**人员姓名**/
	private String userName;
	/**人员编号**/
	private String loginId;
	/**所属部门**/
	private String deptName;
	/**在线状态**/
	private String status;
	private String pid;
	private String sn;


	/**
	 * 设置：人员姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：人员姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：人员编号
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
	 * 获取：人员编号
	 */
	public String getLoginId() {
		return loginId;
	}
	/**
	 * 设置：所属部门
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * 获取：所属部门
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * 设置：在线状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：在线状态
	 */
	public String getStatus() {
		return status;
	}

	 public String getChannelGuid() {
		 return channelGuid;
	 }

	 public void setChannelGuid(String channelGuid) {
		 this.channelGuid = channelGuid;
	 }

	 public String getPid() {
		 return pid;
	 }

	 public void setPid(String pid) {
		 this.pid = pid;
	 }

	 public String getSn() {
		 return sn;
	 }

	 public void setSn(String sn) {
		 this.sn = sn;
	 }

 }
