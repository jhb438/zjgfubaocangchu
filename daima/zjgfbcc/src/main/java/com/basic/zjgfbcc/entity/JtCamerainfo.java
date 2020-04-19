package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: JtCamerainfo</p>
 * <p>Description:</p>
 * @author 
 */
public class JtCamerainfo extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**摄像头名称**/
	private String name;
	/**摄像头编号**/
	private String number;
	/**经度**/
	private String la;
	/**纬度**/
	private String lon;
	/**位置**/
	private String location;
	/**备注**/
	private String remark;

	/**
	 * 设置：摄像头名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：摄像头名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：摄像头编号
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * 获取：摄像头编号
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * 设置：经度
	 */
	public void setLa(String la) {
		this.la = la;
	}
	/**
	 * 获取：经度
	 */
	public String getLa() {
		return la;
	}
	/**
	 * 设置：纬度
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}
	/**
	 * 获取：纬度
	 */
	public String getLon() {
		return lon;
	}
	/**
	 * 设置：位置
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * 获取：位置
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
