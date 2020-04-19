package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: FbRenyuaninfo</p>
 * <p>Description:</p>
 * @author 
 */
public class FbRenyuaninfo extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**人员ID**/
	private String personId;
	/**姓名**/
	private String personName;
	/**性别**/
	private String gender;
	/**所属组织路径**/
	private String orgPath;
	/**所属组织名称**/
	private String orgIndexCode;
	/**所属组织名称**/
	private String orgName;
	/**证件类型**/
	private String certificateType;
	/**证件号码**/
	private String certificateNo;
	/**联系电话**/
	private String phoneNo;
	/**联系地址**/
	private String address;

	/**
	 * 设置：人员ID
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	/**
	 * 获取：人员ID
	 */
	public String getPersonId() {
		return personId;
	}
	/**
	 * 设置：姓名
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	/**
	 * 获取：姓名
	 */
	public String getPersonName() {
		return personName;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置：所属组织路径
	 */
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	/**
	 * 获取：所属组织路径
	 */
	public String getOrgPath() {
		return orgPath;
	}
	/**
	 * 设置：所属组织名称
	 */
	public void setOrgIndexCode(String orgIndexCode) {
		this.orgIndexCode = orgIndexCode;
	}
	/**
	 * 获取：所属组织名称
	 */
	public String getOrgIndexCode() {
		return orgIndexCode;
	}
	/**
	 * 设置：所属组织名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：所属组织名称
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：证件类型
	 */
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	/**
	 * 获取：证件类型
	 */
	public String getCertificateType() {
		return certificateType;
	}
	/**
	 * 设置：证件号码
	 */
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	/**
	 * 获取：证件号码
	 */
	public String getCertificateNo() {
		return certificateNo;
	}
	/**
	 * 设置：联系电话
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * 设置：联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：联系地址
	 */
	public String getAddress() {
		return address;
	}
}
