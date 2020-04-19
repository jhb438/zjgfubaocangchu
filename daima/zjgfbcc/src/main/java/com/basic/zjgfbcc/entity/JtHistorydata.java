package com.basic.zjgfbcc.entity;

import com.basic.zjgfbcc.common.utils.SpringContextUtils;
import com.basic.zjgfbcc.service.Frame_CodeValueService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



 /**
 * <p>Title: JtHistorydata</p>
 * <p>Description:</p>
 * @author 
 */
public class JtHistorydata extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	 Frame_CodeValueService codeValueService=(Frame_CodeValueService) SpringContextUtils.getBean("codeValueService");
	/**文件id**/
	private String dataId;
	/**创建时间**/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date captureDate;
	/**记录仪编号**/
	private String deviceNo;
	/**上传时间**/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date creationDate;
	/**文件名**/
	private String mediaName;
	/**记录仪持有人编号**/
	private String policeNo;
	/**组织机构编号**/
	private String orgNo;
	 /**组织机构编号**/
	 private String orgName;
	/**文件类型**/
	private String fileType;
	/**视频的播放时间长度(ms)**/
	private String fileDuration;
	/**缩略图地址**/
	private String thumburl;
	/**播放地址**/
	private String playurl;
	private String policeName;
	 private String userGuid;

	 /**是否关联案件**/
	 private String isRelated;
	 private String fileTypeText;
	 private String isRelatedText;

	/**
	 * 设置：文件id
	 */
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	/**
	 * 获取：文件id
	 */
	public String getDataId() {
		return dataId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCaptureDate(Date captureDate) {
		this.captureDate = captureDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCaptureDate() {
		return captureDate;
	}
	/**
	 * 设置：记录仪编号
	 */
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	/**
	 * 获取：记录仪编号
	 */
	public String getDeviceNo() {
		return deviceNo;
	}
	/**
	 * 设置：上传时间
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * 获取：上传时间
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * 设置：文件名
	 */
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	/**
	 * 获取：文件名
	 */
	public String getMediaName() {
		return mediaName;
	}
	/**
	 * 设置：记录仪持有人编号
	 */
	public void setPoliceNo(String policeNo) {
		this.policeNo = policeNo;
	}
	/**
	 * 获取：记录仪持有人编号
	 */
	public String getPoliceNo() {
		return policeNo;
	}
	/**
	 * 设置：组织机构编号
	 */
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	/**
	 * 获取：组织机构编号
	 */
	public String getOrgNo() {
		return orgNo;
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
	 * 设置：视频的播放时间长度(ms)
	 */
	public void setFileDuration(String fileDuration) {
		this.fileDuration = fileDuration;
	}
	/**
	 * 获取：视频的播放时间长度(ms)
	 */
	public String getFileDuration() {
		return fileDuration;
	}
	/**
	 * 设置：缩略图地址
	 */
	public void setThumburl(String thumburl) {
		this.thumburl = thumburl;
	}
	/**
	 * 获取：缩略图地址
	 */
	public String getThumburl() {
		return thumburl;
	}
	/**
	 * 设置：播放地址
	 */
	public void setPlayurl(String playurl) {
		this.playurl = playurl;
	}
	/**
	 * 获取：播放地址
	 */
	public String getPlayurl() {
		return playurl;
	}
	 public String getOrgName() {
		 return orgName;
	 }

	 public void setOrgName(String orgName) {
		 this.orgName = orgName;
	 }
	 public String getPoliceName() {
		 return policeName;
	 }

	 public void setPoliceName(String policeName) {
		 this.policeName = policeName;
	 }
	 public String getUserGuid() {
		 return userGuid;
	 }

	 public void setUserGuid(String userGuid) {
		 this.userGuid = userGuid;
	 }
	 public String getFileTypeText() {
		 if (fileType!= null) {
			 return codeValueService.getCodeByNameAndValue("文件类型", fileType);
		 }else
			 return "";
	 }

	 public void setFileTypeText(String fileTypeText) {
		 this.fileTypeText = fileTypeText;
	 }

	 public String getIsRelated() {
		 return isRelated;
	 }

	 public void setIsRelated(String isRelated) {
		 this.isRelated = isRelated;
	 }
	 public String getIsRelatedText() {
		 return isRelatedText;
	 }

	 public void setIsRelatedText(String isRelatedText) {
		 this.isRelatedText = isRelatedText;
	 }
}
