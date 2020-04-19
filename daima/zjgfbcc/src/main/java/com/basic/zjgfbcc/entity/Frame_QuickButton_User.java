package com.basic.zjgfbcc.entity;

/**
 * <p>Title: Frame_QuickButton_User</p>
 * <p>Description: 系统参数实体</p>
 * @author wzl
 */
public class Frame_QuickButton_User {
    //行标识
    private Integer Row_ID;
    //记录唯一标识
    private String RowGuid;
    //快捷按钮名称
    private String QuickName;
    //快捷按钮Url
    private String QuickUrl;
    //快捷按钮图标
    private String QuickIcon;
    //目标框架
    private String Target;
    //排序号
    private int SortSQ;
    //用户唯一标识
    private String UserGuid;

    public Integer getRow_ID() {
        return Row_ID;
    }

    public void setRow_ID(Integer row_ID) {
        Row_ID = row_ID;
    }

    public String getRowGuid() {
        return RowGuid;
    }

    public void setRowGuid(String rowGuid) {
        RowGuid = rowGuid;
    }

    public String getQuickName() {
        return QuickName;
    }

    public void setQuickName(String quickName) {
        QuickName = quickName;
    }

    public String getQuickUrl() {
        return QuickUrl;
    }

    public void setQuickUrl(String quickUrl) {
        QuickUrl = quickUrl;
    }

    public String getQuickIcon() {
        return QuickIcon;
    }

    public void setQuickIcon(String quickIcon) {
        QuickIcon = quickIcon;
    }

    public String getTarget() {
        return Target;
    }

    public void setTarget(String target) {
        Target = target;
    }

    public int getSortSQ() {
        return SortSQ;
    }

    public void setSortSQ(int sortSQ) {
        SortSQ = sortSQ;
    }

    public String getUserGuid() {
        return UserGuid;
    }

    public void setUserGuid(String userGuid) {
        UserGuid = userGuid;
    }

    @Override
    public String toString() {
        return "Frame_QuickButton_User{" +
                "Row_ID=" + Row_ID +
                ", RowGuid='" + RowGuid + '\'' +
                ", QuickName='" + QuickName + '\'' +
                ", QuickUrl='" + QuickUrl + '\'' +
                ", QuickIcon='" + QuickIcon + '\'' +
                ", Target='" + Target + '\'' +
                ", SortSQ=" + SortSQ +
                ", UserGuid='" + UserGuid + '\'' +
                '}';
    }
}
