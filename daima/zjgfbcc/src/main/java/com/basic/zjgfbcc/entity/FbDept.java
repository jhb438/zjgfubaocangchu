package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: FbDept</p>
 * <p>Description:</p>
 * @author 
 */
public class FbDept extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**组织唯一标识码**/
	private String orgIndexCode;
	/**组织编号**/
	private String orgNo;
	/**组织名称**/
	private String orgName;
	/**组织路径**/
	private String orgPath;
	/**父组织唯一标识码**/
	private String parentOrgIndexCode;
	/**父组织名称**/
	private String parentOrgName;

	/**
	 * 设置：组织唯一标识码
	 */
	public void setOrgIndexCode(String orgIndexCode) {
		this.orgIndexCode = orgIndexCode;
	}
	/**
	 * 获取：组织唯一标识码
	 */
	public String getOrgIndexCode() {
		return orgIndexCode;
	}
	/**
	 * 设置：组织编号
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	/**
	 * 获取：组织编号
	 */
	public String getOrgNo() {
		return orgNo;
	}
	/**
	 * 设置：组织名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：组织名称
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：组织路径
	 */
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	/**
	 * 获取：组织路径
	 */
	public String getOrgPath() {
		return orgPath;
	}
	/**
	 * 设置：父组织唯一标识码
	 */
	public void setParentOrgIndexCode(String parentOrgIndexCode) {
		this.parentOrgIndexCode = parentOrgIndexCode;
	}
	/**
	 * 获取：父组织唯一标识码
	 */
	public String getParentOrgIndexCode() {
		return parentOrgIndexCode;
	}
	/**
	 * 设置：父组织名称
	 */
	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	/**
	 * 获取：父组织名称
	 */
	public String getParentOrgName() {
		return parentOrgName;
	}
}
