package com.basic.zjgfbcc.entity;

import com.basic.zjgfbcc.common.utils.SpringContextUtils;
import com.basic.zjgfbcc.service.Frame_CodeValueService;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: JtDeviceinfo</p>
 * <p>Description:</p>
 * @author 
 */
public class JtDeviceinfo extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	 Frame_CodeValueService codeValueService=(Frame_CodeValueService) SpringContextUtils.getBean("codeValueService");
	/**设备编号**/
	private String sn;
	/**pid**/
	private String pid;
	/**接收到的经度**/
	private String la;
	/**接收到的纬度**/
	private String lon;
	/**转化后的经度**/
	private String lng;
	/**转化后的纬度**/
	private String lat;
	/**在线状态**/
	private String status;
	/**终端uid**/
	private String uid;
	/**是否在固定频道**/
	private String isInGDPD;
	/**部门guid**/
	private String deptGuid;
	/**对应人员姓名**/
	private String userName;
	/**对应人员loginID**/
	private String loginId;
	 /**部门名称**/
	private String deptName;
	 /**组织机构编号**/
	 private String oucode;
	 /**人员编号**/
	 private String gongHao;


	/**
	 * 设置：设备编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * 获取：设备编号
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * 设置：pid
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 获取：pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 设置：接收到的经度
	 */
	public void setLa(String la) {
		this.la = la;
	}
	/**
	 * 获取：接收到的经度
	 */
	public String getLa() {
		return la;
	}
	/**
	 * 设置：接收到的纬度
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}
	/**
	 * 获取：接收到的纬度
	 */
	public String getLon() {
		return lon;
	}
	/**
	 * 设置：转化后的经度
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}
	/**
	 * 获取：转化后的经度
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * 设置：转化后的纬度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	/**
	 * 获取：转化后的纬度
	 */
	public String getLat() {
		return lat;
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
	/**
	 * 设置：终端uid
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 获取：终端uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * 设置：是否在固定频道
	 */
	public void setIsInGDPD(String isInGDPD) {
		this.isInGDPD = isInGDPD;
	}
	/**
	 * 获取：是否在固定频道
	 */
	public String getIsInGDPD() {
		return isInGDPD;
	}
	/**
	 * 设置：部门guid
	 */
	public void setDeptGuid(String deptGuid) {
		this.deptGuid = deptGuid;
	}
	/**
	 * 获取：部门guid
	 */
	public String getDeptGuid() {
		return deptGuid;
	}
	/**
	 * 设置：对应人员姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：对应人员姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：对应人员loginID
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
	 * 获取：对应人员loginID
	 */
	public String getLoginId() {
		return loginId;
	}

	 public String getDeptName() {
		 return deptName;
	 }

	 public void setDeptName(String deptName) {
		 this.deptName = deptName;
	 }
	 public String getOucode() {
		 return oucode;
	 }

	 public void setOucode(String oucode) {
		 this.oucode = oucode;
	 }

	 public String getGongHao() {
		 return gongHao;
	 }

	 public void setGongHao(String gongHao) {
		 this.gongHao = gongHao;
	 }



 }
