package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: JtTempchannel</p>
 * <p>Description:</p>
 * @author 
 */
public class JtTempchannel extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**频道名称**/
	private String channelName;
	 /**频道号**/
	private String channelId;

	/**
	 * 设置：频道名称
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	/**
	 * 获取：频道名称
	 */
	public String getChannelName() {
		return channelName;
	}

	 public String getChannelId() {
		 return channelId;
	 }

	 public void setChannelId(String channelId) {
		 this.channelId = channelId;
	 }
}
