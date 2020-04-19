package com.basic.zjgfbcc.entity;

/**
 * <p>Title: Frame_Config</p>
 * <p>Description: 系统参数实体</p>
 * @author wzl
 */
public class Frame_Config extends BaseModel{

    //分类唯一标识
    private String categoryGuid;
    //参数名称
    private String configName;
    //参数值
    private String configValue;
    //参数描述
    private String description;

    public String getCategoryGuid() {
        return categoryGuid;
    }

    public void setCategoryGuid(String categoryGuid) {
        this.categoryGuid = categoryGuid;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
