package com.basic.zjgfbcc.entity;

import com.basic.zjgfbcc.common.utils.SpringContextUtils;
import com.basic.zjgfbcc.service.Frame_DeptService;

/**
 * <p>Title: Frame_Dept</p>
 * <p>Description: 部门管理实体</p>
 * @author wzl
 */
public class Frame_Dept extends BaseModel {
	Frame_DeptService deptService=(Frame_DeptService) SpringContextUtils.getBean("deptService");
    //部门编号
    private String deptCode;
    //上级部门编号
    private String pdeptCode;
    //子部门数量
    private int hasChild;
    //部门名称
    private String deptName;
    //部门简称
    private String shortName;
    //部门编码
    private String oucode;

    //电话
    private String tel;
    //传真
    private String fax;
    //地址
    private String address;
    //描述
    private String description;
    private String pdeptName;
	private String dkey;
	private Integer codeLevel;

	public String getPdeptName() {
		return pdeptName;
	}

	public void setPdeptName(String pdeptName) {
		this.pdeptName = pdeptName;
	}

	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getPdeptCode() {
		return pdeptCode;
	}
	public void setPdeptCode(String pdeptCode) {
		this.pdeptCode = pdeptCode;
	}
	public int getHasChild() {
		return hasChild;
	}
	public void setHasChild(int hasChild) {
		this.hasChild = hasChild;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getOucode() {
		return oucode;
	}
	public void setOucode(String oucode) {
		this.oucode = oucode;
	}

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDkey() {
		return dkey;
	}

	public void setDkey(String dkey) {
		this.dkey = dkey;
	}
	public Integer getCodeLevel() {
		return codeLevel;
	}

	public void setCodeLevel(Integer codeLevel) {
		this.codeLevel = codeLevel;
	}

   
}
