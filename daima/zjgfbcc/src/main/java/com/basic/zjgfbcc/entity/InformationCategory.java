package com.basic.zjgfbcc.entity;

import com.basic.zjgfbcc.common.utils.SpringContextUtils;
import com.basic.zjgfbcc.service.Frame_CodeValueService;

import java.io.Serializable;



 /**
 * <p>Title: InformationCategory</p>
 * <p>Description:</p>
 * @author 
 */
public class InformationCategory extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	 Frame_CodeValueService codeValueService=(Frame_CodeValueService) SpringContextUtils.getBean("codeValueService");

	/**栏目编号**/
	private String categoryCode;
	/**栏目名称**/
	private String categoryName;
	/**是否单条信息**/
	private Integer isSingle;
	/**是否需要审核**/
	private Integer isNeedAudit;
	/**描述**/
	private String description;
	/**父栏目编号**/
	private String pcategoryCode;

	 private String isSingleText;



	 /**
	 * 设置：栏目编号
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	/**
	 * 获取：栏目编号
	 */
	public String getCategoryCode() {
		return categoryCode;
	}
	/**
	 * 设置：栏目名称
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * 获取：栏目名称
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * 设置：是否单条信息
	 */
	public void setIsSingle(Integer isSingle) {
		this.isSingle = isSingle;
	}
	/**
	 * 获取：是否单条信息
	 */
	public Integer getIsSingle() {
		return isSingle;
	}
	/**
	 * 设置：是否需要审核
	 */
	public void setIsNeedAudit(Integer isNeedAudit) {
		this.isNeedAudit = isNeedAudit;
	}
	/**
	 * 获取：是否需要审核
	 */
	public Integer getIsNeedAudit() {
		return isNeedAudit;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：父栏目编号
	 */
	public void setPcategoryCode(String pcategoryCode) {
		this.pcategoryCode = pcategoryCode;
	}
	/**
	 * 获取：父栏目编号
	 */
	public String getPcategoryCode() {
		return pcategoryCode;
	}

	 public String getIsSingleText() {
		 if (isSingle!= null) {
			 return codeValueService.getCodeByNameAndValue("是否", String.valueOf(isSingle));
		 }else
			 return "";
	 }

	 public void setIsSingleText(String isSingleText) {
		 this.isSingleText = isSingleText;
	 }
}
