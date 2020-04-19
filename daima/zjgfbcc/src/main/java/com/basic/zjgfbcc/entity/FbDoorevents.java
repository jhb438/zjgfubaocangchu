package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: FbDoorevents</p>
 * <p>Description:</p>
 * @author 
 */
public class FbDoorevents extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**姓名**/
	private String personName;
	/**工号**/
	private String jobNo;
	/**卡号**/
	private String cardNo;
	/**组织编码**/
	private String orgIndexCode;
	/**组织名称**/
	private String orgName;
	/**事件ID**/
	private String eventId;
	/**事件名称**/
	private String eventName;
	/**事件产生时间**/
	private String eventTime;
	/**人员唯一编码**/
	private String personId;
	/**门禁点编码**/
	private String doorIndexCode;
	/**门禁点名称**/
	private String doorName;

	/**
	 * 设置：姓名
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	/**
	 * 获取：姓名
	 */
	public String getPersonName() {
		return personName;
	}
	/**
	 * 设置：工号
	 */
	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	/**
	 * 获取：工号
	 */
	public String getJobNo() {
		return jobNo;
	}
	/**
	 * 设置：卡号
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * 获取：卡号
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * 设置：组织编码
	 */
	public void setOrgIndexCode(String orgIndexCode) {
		this.orgIndexCode = orgIndexCode;
	}
	/**
	 * 获取：组织编码
	 */
	public String getOrgIndexCode() {
		return orgIndexCode;
	}
	/**
	 * 设置：组织名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 获取：组织名称
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * 设置：事件ID
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	/**
	 * 获取：事件ID
	 */
	public String getEventId() {
		return eventId;
	}
	/**
	 * 设置：事件名称
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * 获取：事件名称
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * 设置：事件产生时间
	 */
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	/**
	 * 获取：事件产生时间
	 */
	public String getEventTime() {
		return eventTime;
	}
	/**
	 * 设置：人员唯一编码
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	/**
	 * 获取：人员唯一编码
	 */
	public String getPersonId() {
		return personId;
	}
	/**
	 * 设置：门禁点编码
	 */
	public void setDoorIndexCode(String doorIndexCode) {
		this.doorIndexCode = doorIndexCode;
	}
	/**
	 * 获取：门禁点编码
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
}
