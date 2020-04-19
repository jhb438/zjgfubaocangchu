package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: JtDeviceRelation</p>
 * <p>Description:</p>
 * @author 
 */
public class JtDeviceRelation extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**deptCode**/
	private String deptCode;
	/**loginId**/
	private String loginId;
	/**roleGuid**/
	private String roleGuid;

	/**
	 * 设置：deptGuid
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	/**
	 * 获取：deptGuid
	 */
	public String getDeptCode() {
		return deptCode;
	}
	/**
	 * 设置：loginId
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
	 * 获取：loginId
	 */
	public String getLoginId() {
		return loginId;
	}
	/**
	 * 设置：roleGuid
	 */
	public void setRoleGuid(String roleGuid) {
		this.roleGuid = roleGuid;
	}
	/**
	 * 获取：roleGuid
	 */
	public String getRoleGuid() {
		return roleGuid;
	}
}
