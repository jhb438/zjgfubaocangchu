package com.basic.zjgfbcc.entity;

import com.basic.zjgfbcc.common.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BaseModel {

    public BaseModel()
    {
        delFlag=0;
        createTime= DateUtil.changeDate(new Date());
        rowGuid=java.util.UUID.randomUUID().toString();
        sortSq=0;
    }
    private Integer rowId;
    private String rowGuid;
    /****/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;
    /****/
    private Integer delFlag;
    /****/
    private Integer sortSq;

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }
    /**
     * 获取：
     */
    public Integer getRowId() {
        return rowId;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }
    /**
     * 获取：
     */
    public String getRowGuid() {
        return rowGuid;
    }
    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置：
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
    /**
     * 获取：
     */
    public Integer getDelFlag() {
        return delFlag;
    }
    /**
     * 设置：
     */
    public void setSortSq(Integer sortSq) {
        this.sortSq = sortSq;
    }
    /**
     * 获取：
     */
    public Integer getSortSq() {
        return sortSq;
    }

}
