package com.basic.zjgfbcc.entity;

/**
 * <p>Title: Frame_Codes</p>
 * <p>Description: 代码项值实体</p>
 * @author wzl
 */
public class Frame_CodeValue extends BaseModel{
    //代码项值
    private String itemValue;

    //代码项文本
    private String itemText;

    //代码项唯一标识
    private String codeGuid;

    private String parentGuid;


    private String codeLevel;

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public String getCodeGuid() {
        return codeGuid;
    }

    public void setCodeGuid(String codeGuid) {
        this.codeGuid = codeGuid;
    }

    public String getParentGuid() {
        return parentGuid;
    }

    public void setParentGuid(String parentGuid) {
        this.parentGuid = parentGuid;
    }
    public String getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(String codeLevel) {
        this.codeLevel = codeLevel;
    }
}
