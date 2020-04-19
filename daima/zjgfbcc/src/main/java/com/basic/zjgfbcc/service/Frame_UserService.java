package com.basic.zjgfbcc.service;
import com.basic.zjgfbcc.entity.Frame_Role;
import com.basic.zjgfbcc.entity.Frame_Role_User;
import com.basic.zjgfbcc.entity.Frame_User;
import com.basic.zjgfbcc.entity.JtTempperson;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: UserService</p>
 * <p>Description: 用户服务层</p>
 * @author my
 */
public interface Frame_UserService {
    //新增
    void insert(Frame_User user);

    //编辑
    void update(Frame_User user);
    void updateAdmin(Frame_User user);

    //更新登录时间
    void updateLoginTime(Frame_User frameUser);

    //删除
    void delete(Integer[] ids);

    //批量删除
    void deleteUserById(String[] rowGuid);
    //删除特定用户
    void deleteUsers();

    //启用用户
    void enableUserById(String[] rowGuids);

    //禁用用户
    void forbidUserById(String[] rowGuids);

    //重置密码
    void resetPasswordById(String password,String[] rowGuids);

    //修改排序号
    void saveSortSq(Integer[] rowId,Integer[] sortSq);

//    //获取id
//    User getById(int id);

    //通过姓名查询
    Frame_User getFrameUserByLoginId(String loginId);

    //获取所有启用用户
    List<Frame_User> getUser(Map<String, Object> params);

    //点击角色获取用户
    List<Frame_User> getUserFromRole(String roleGuid);
    //根据角色名称获取所有用户
    List<Frame_User> getUserFromRoleName(String roleName);

	int getCount(Map<String, Object> params);

	Frame_User findUserByGuid(String guid);

	//用户名重复检测
    <T> int checkUser(T t);

    //通过用户行号获取部门guid和名称
    Map<String, Object> getDeptByGuid(String rowGuid);

    //验证旧密码
    String checkOldPassword(String rowGuid);

    //更新新密码
    void updateNewPassword(String rowGuid,String password);
    //获取所有正常用户
    List<Frame_User> getAllUserList();
    //通过deptGuid获取所有用户
    List<Frame_User> getUserListByDeptGuid(String deptGuid);

    Frame_User getDetailByGuid(String rowGuid);

	List<Frame_User> ceshi();

	int getOnLineUserCount();
    List<Map<String,Object>> getAllDeviceList();


    List<Frame_User> getDeviceListByDeptGuid(String deptGuid,String status);
    Frame_User  getDetailByNameAndDeptGuid(String userName,String deptGuid);

    Frame_User getDetailByPara(String para,String value);

	List<Frame_Role> getRolesByUserGuid(String rowGuid);

    List<Frame_User> getUserByRoleName(Map<String, Object> params);
}
