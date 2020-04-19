package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



 /**
 * <p>Title: JtWarninginfo</p>
 * <p>Description:</p>
 * @author 
 */
public class JtWarninginfo extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**告警编号**/
	private String id;
	/**部门id**/
	private String deptid;
	/**告警内容**/
	private String context;
	/**告警标题**/
	private String title;
	/**告警等级**/
	private String lv;
	/**人员编号**/
	private String pno;

	/**
	 * 设置：告警编号
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：告警编号
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：部门id
	 */
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	/**
	 * 获取：部门id
	 */
	public String getDeptid() {
		return deptid;
	}
	/**
	 * 设置：告警内容
	 */
	public void setContext(String context) {
		this.context = context;
	}
	/**
	 * 获取：告警内容
	 */
	public String getContext() {
		return context;
	}
	/**
	 * 设置：告警标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：告警标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：告警等级
	 */
	public void setLv(String lv) {
		this.lv = lv;
	}
	/**
	 * 获取：告警等级
	 */
	public String getLv() {
		return lv;
	}

	/**
	 * 设置：人员编号
	 */
	public void setPno(String pno) {
		this.pno = pno;
	}
	/**
	 * 获取：人员编号
	 */
	public String getPno() {
		return pno;
	}
}
