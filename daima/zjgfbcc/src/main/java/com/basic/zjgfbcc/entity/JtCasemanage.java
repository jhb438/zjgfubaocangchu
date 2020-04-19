package com.basic.zjgfbcc.entity;

import com.basic.zjgfbcc.common.utils.SpringContextUtils;
import com.basic.zjgfbcc.service.Frame_CodeValueService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: JtCasemanage</p>
 * <p>Description:</p>
 * @author 
 */
public class JtCasemanage extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	 Frame_CodeValueService codeValueService=(Frame_CodeValueService) SpringContextUtils.getBean("codeValueService");
	/**案件编号**/
	private String caseBH;
	/**案件名称**/
	private String caseName;
	/**案件类型**/
	private String caseType;
	/**所属部门**/
	private String deptName;
	/**部门编号**/
	private String deptGuid;
	/**案件创建时间**/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date caseBeginDate;
	/**案件结束时间**/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date caseEndDate;
	/**创建人**/
	private String createUser;
	/**创建人guid**/
	private String createUserGuid;
	/**案件状态**/
	private String caseStatus;
	private String caseLocation;
	private String caseDescribe;
	 private String caseTypeText;
	 private String caseStatusText;

	/**
	 * 设置：案件编号
	 */
	public void setCaseBH(String caseBH) {
		this.caseBH = caseBH;
	}
	/**
	 * 获取：案件编号
	 */
	public String getCaseBH() {
		return caseBH;
	}
	/**
	 * 设置：案件名称
	 */
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	/**
	 * 获取：案件名称
	 */
	public String getCaseName() {
		return caseName;
	}
	/**
	 * 设置：案件类型
	 */
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	/**
	 * 获取：案件类型
	 */
	public String getCaseType() {
		return caseType;
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
	 * 设置：部门编号
	 */
	public void setDeptGuid(String deptGuid) {
		this.deptGuid = deptGuid;
	}
	/**
	 * 获取：部门编号
	 */
	public String getDeptGuid() {
		return deptGuid;
	}
	/**
	 * 设置：案件创建时间
	 */
	public void setCaseBeginDate(Date caseBeginDate) {
		this.caseBeginDate = caseBeginDate;
	}
	/**
	 * 获取：案件创建时间
	 */
	public Date getCaseBeginDate() {
		return caseBeginDate;
	}
	/**
	 * 设置：案件结束时间
	 */
	public void setCaseEndDate(Date caseEndDate) {
		this.caseEndDate = caseEndDate;
	}
	/**
	 * 获取：案件结束时间
	 */
	public Date getCaseEndDate() {
		return caseEndDate;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：创建人guid
	 */
	public void setCreateUserGuid(String createUserGuid) {
		this.createUserGuid = createUserGuid;
	}
	/**
	 * 获取：创建人guid
	 */
	public String getCreateUserGuid() {
		return createUserGuid;
	}
	/**
	 * 设置：案件状态
	 */
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	/**
	 * 获取：案件状态
	 */
	public String getCaseStatus() {
		return caseStatus;
	}
	 public String getCaseLocation() {
		 return caseLocation;
	 }

	 public void setCaseLocation(String caseLocation) {
		 this.caseLocation = caseLocation;
	 }

	 public String getCaseDescribe() {
		 return caseDescribe;
	 }

	 public void setCaseDescribe(String caseDescribe) {
		 this.caseDescribe = caseDescribe;
	 }
	 public String getCaseTypeText() {
		 if (caseType!= null) {
			 return codeValueService.getCodeByNameAndValue("案件类型", caseType);
		 }else
			 return "";
	 }

	 public void setCaseTypeText(String caseTypeText) {
		 this.caseTypeText = caseTypeText;
	 }

	 public String getCaseStatusText() {
		 if (caseStatus!= null) {
			 return codeValueService.getCodeByNameAndValue("案件状态", caseStatus);
		 }else
			 return "";
	 }

	 public void setCaseStatusText(String caseStatusText) {
		 this.caseStatusText = caseStatusText;
	 }
}
