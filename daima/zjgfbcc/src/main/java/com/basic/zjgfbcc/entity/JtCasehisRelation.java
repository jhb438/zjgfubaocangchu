package com.basic.zjgfbcc.entity;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: JtCasehisRelation</p>
 * <p>Description:</p>
 * @author 
 */
public class JtCasehisRelation extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**案件Guid**/
	private String caseGuid;
	/**文件ID**/
	private String dataID;
	/**文件类型**/
	private String fileType;
	/**文件地址**/
	private String fileUrl;

	/**
	 * 设置：案件Guid
	 */
	public void setCaseGuid(String caseGuid) {
		this.caseGuid = caseGuid;
	}
	/**
	 * 获取：案件Guid
	 */
	public String getCaseGuid() {
		return caseGuid;
	}
	/**
	 * 设置：文件ID
	 */
	public void setDataID(String dataID) {
		this.dataID = dataID;
	}
	/**
	 * 获取：文件ID
	 */
	public String getDataID() {
		return dataID;
	}
	/**
	 * 设置：文件类型
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * 获取：文件类型
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * 设置：文件地址
	 */
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	/**
	 * 获取：文件地址
	 */
	public String getFileUrl() {
		return fileUrl;
	}
}
