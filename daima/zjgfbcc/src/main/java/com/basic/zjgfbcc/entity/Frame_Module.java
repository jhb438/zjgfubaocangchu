package com.basic.zjgfbcc.entity;

/**
 * <p>Title: Frame_Module</p>
 * <p>Description: 模块管理实体</p>
 * @author wzl
 */
public class Frame_Module extends BaseModel{

	public Frame_Module()
	{
		isVisible=1;
	}

    //模块名称
    private String moduleName;
    //模块编号
    private String moduleCode;
    //上级模块编号
    private String pmoduleCode;
    //子模块数量
    private int hasChild;
    //模块地址
    private String moduleAddr;
    //小图标
    private String smallIcon;
    //大图标
    private String bigIcon;
    //目标框架
    private String target;
    //是否隐藏
    private int isVisible;

    
    private String pmoduleName;
	public String getPmoduleName() {
		return pmoduleName;
	}
	public void setPmoduleName(String pmoduleName) {
		this.pmoduleName = pmoduleName;
	}

	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	
	public String getPmoduleCode() {
		return pmoduleCode;
	}
	public void setPmoduleCode(String pmoduleCode) {
		this.pmoduleCode = pmoduleCode;
	}
	public int getHasChild() {
		return hasChild;
	}
	public void setHasChild(int hasChild) {
		this.hasChild = hasChild;
	}
	public String getModuleAddr() {
		return moduleAddr;
	}
	public void setModuleAddr(String moduleAddr) {
		this.moduleAddr = moduleAddr;
	}
	public String getSmallIcon() {
		return smallIcon;
	}
	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}
	public String getBigIcon() {
		return bigIcon;
	}
	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(int isVisible) {
		this.isVisible = isVisible;
	}


   
}
