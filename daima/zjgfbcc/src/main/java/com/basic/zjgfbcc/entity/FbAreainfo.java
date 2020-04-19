package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: FbAreainfo</p>
 * <p>Description:</p>
 * @author 
 */
public class FbAreainfo extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**区域名称**/
	private String areaName;

	/**
	 * 设置：区域名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取：区域名称
	 */
	public String getAreaName() {
		return areaName;
	}
}
