package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: JtLocationHist</p>
 * <p>Description:</p>
 * @author 
 */
public class JtLocationHist extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**记录仪编号**/
	private String uid;
	/**上传时间**/
	private Date submitDate;
	/**经度**/
	private String la;
	/**纬度**/
	private String lon;
	
	private String lng;
	
	private String lat;
	
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * 设置：记录仪编号
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 获取：记录仪编号
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * 设置：上传时间
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	/**
	 * 获取：上传时间
	 */
	public Date getSubmitDate() {
		return submitDate;
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
}
