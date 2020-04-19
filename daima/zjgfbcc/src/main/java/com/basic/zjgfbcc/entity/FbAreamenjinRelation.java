package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: FbAreamenjinRelation</p>
 * <p>Description:</p>
 * @author 
 */
public class FbAreamenjinRelation extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**区域Guid**/
	private String areaGuid;
	/**门禁点名称**/
	private String doorName;
	/**门禁点唯一标识**/
	private String doorIndexCode;
	/**门禁功能**/
	private String menJinGN;

	/**
	 * 设置：区域Guid
	 */
	public void setAreaGuid(String areaGuid) {
		this.areaGuid = areaGuid;
	}
	/**
	 * 获取：区域Guid
	 */
	public String getAreaGuid() {
		return areaGuid;
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
	 * 设置：门禁功能
	 */
	public void setMenJinGN(String menJinGN) {
		this.menJinGN = menJinGN;
	}
	/**
	 * 获取：门禁功能
	 */
	public String getMenJinGN() {
		return menJinGN;
	}
}
