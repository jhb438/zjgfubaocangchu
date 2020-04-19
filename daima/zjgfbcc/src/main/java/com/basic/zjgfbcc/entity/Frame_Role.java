package com.basic.zjgfbcc.entity;

/**
 * <p>Title: Frame_Role</p>
 * <p>Description: 角色管理实体</p>
 * @author wzl
 */
public class Frame_Role extends BaseModel{

    //角色名称
    private String roleName;
    //角色类型
    private String roleType;
    //首页地址
    private String mainIndex;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getMainIndex() {
        return mainIndex;
    }

    public void setMainIndex(String mainIndex) {
       this.mainIndex = mainIndex;
    }




}
