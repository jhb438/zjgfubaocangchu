package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: FbMenjindian</p>
 * <p>Description:</p>
 * @author 
 */
public class FbMenjindian extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**门禁点唯一标识**/
	private String doorIndexCode;
	/**门禁点名称**/
	private String doorName;
	/**门禁点序号**/
	private String doorNo;
	/**所属门禁设备唯一标识**/
	private String acsDevIndexCode;
	/**所属区域唯一标识**/
	private String regionIndexCode;
	/**通道类型**/
	private String channelType;
	/**通道号**/
	private String channelNo;
	/**安装位置**/
	private String installLocation;

	/**
	 * 设置：门禁点唯一标识
	 */
	public void setDoorIndexCode(String doorIndexCode) {
		this.doorIndexCode = doorIndexCode;
	}
	/**
	 * 获取：门禁点唯一标识
	 */
	public String getDoorIndexCode() {
		return doorIndexCode;
	}
	/**
	 * 设置：门禁点名称
	 */
	public void setDoorName(String doorName) {
		this.doorName = doorName;
	}
	/**
	 * 获取：门禁点名称
	 */
	public String getDoorName() {
		return doorName;
	}
	/**
	 * 设置：门禁点序号
	 */
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	/**
	 * 获取：门禁点序号
	 */
	public String getDoorNo() {
		return doorNo;
	}
	/**
	 * 设置：所属门禁设备唯一标识
	 */
	public void setAcsDevIndexCode(String acsDevIndexCode) {
		this.acsDevIndexCode = acsDevIndexCode;
	}
	/**
	 * 获取：所属门禁设备唯一标识
	 */
	public String getAcsDevIndexCode() {
		return acsDevIndexCode;
	}
	/**
	 * 设置：所属区域唯一标识
	 */
	public void setRegionIndexCode(String regionIndexCode) {
		this.regionIndexCode = regionIndexCode;
	}
	/**
	 * 获取：所属区域唯一标识
	 */
	public String getRegionIndexCode() {
		return regionIndexCode;
	}
	/**
	 * 设置：通道类型
	 */
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	/**
	 * 获取：通道类型
	 */
	public String getChannelType() {
		return channelType;
	}
	/**
	 * 设置：通道号
	 */
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	/**
	 * 获取：通道号
	 */
	public String getChannelNo() {
		return channelNo;
	}
	/**
	 * 设置：安装位置
	 */
	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}
	/**
	 * 获取：安装位置
	 */
	public String getInstallLocation() {
		return installLocation;
	}
}
