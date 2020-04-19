package com.basic.zjgfbcc.service;

import com.alibaba.fastjson.JSONArray;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.entity.Frame_CodeValue;
import com.basic.zjgfbcc.entity.Frame_Dept;
import com.basic.zjgfbcc.entity.Frame_User;
import com.basic.zjgfbcc.entity.JtDeviceinfo;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 部门管理 服务层
 *
 * @author wzl
 * @date 2019-03-06
 */
public interface Frame_DeptService {

    /**
     * 查询部门管理信息
     *
     * @param rowId 部门管理ID
     * @return 部门管理信息
     */
    Frame_Dept selectFrameDeptById(Integer rowId);

    /**
     * 查询部门管理信息
     *
     * @param name 部门名字
     * @return 部门管理信息
     */
    Frame_Dept selectFrameDeptByName(String name);

    /**
     * 查询部门管理列表
     *
     * @param frame_dept 部门管理信息
     * @return 部门管理集合
     */
     List<Frame_Dept> selectFrameDeptList(Map<String, Object> params);

    /**
     * 新增部门管理
     *
     * @param frameDept 部门管理信息
     * @return 结果
     */
    void insertFrameDept(Frame_Dept frameDept);

    /**
     * 修改部门管理
     *
     * @param frameDept 部门管理信息
     * @return 结果
     */
    int updateFrameDept(Frame_Dept frameDept);

    /**
     * 删除部门管理信息
     *
     * @param rowGuids 需要删除的数据rowGuids
     * @return 结果
     */
    void deleteFrameDeptById(String[] rowGuids);

    /**
     * 批量删除部门管理信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    void deleteFrameDeptByIds(Integer[] ids);

    void deleteFrameDept();

	int getCount(Map<String, Object> params);

	JSONArray findTopDepts();

    List<Frame_Dept> getDeptTreeDeptCode(String deptcode);
    List<Frame_Dept> getAllDeptTreeByDeptCode(String deptcode);

    Frame_Dept getByDeptCode(String deptCode);

	<T> int checkDept(T t);

	//通过部门行号获取名称
	void getDeptNameByGuid(String rowGuid);

    List<Frame_Dept> findDepts(String deptcode,String isShowXTGLB);

    List<Frame_Dept> getChildDepts(String deptCode);

    Frame_Dept getDetailByGuid(String value);

    void updatePDetCode(String oldDeptCode, String newDeptCode);
    
	List<Frame_User> getDeviceByDeptGuid(String deptGuid);
	
	List<Frame_User> getUsersByDeptGuid(String deptGuid);

	List<Frame_Dept> findNewTopDepts(String tag);

	String getDeptGuidByName(String name);

    List<Frame_CodeValue> getDeptList();

    //通过接口保存部门
    void insertDeptByApi(JSONArray deptObj,String pdeptCode);

    void updateDeptByName(String unit_no,String unit_name);
    /**
     * 根据指定参数和值查询一条记录
     * @param para  参数
     * @param value  参数值
     * @return
     */
    Frame_Dept getDetailByPara(String para, String value);

    /**
     * 根据oucode获取上级部门对应的所有子部门
     * @param oucode
     * @return
     */
    List<Frame_Dept> getChildDeptByOucode(String oucode);

    List<Frame_Dept> getQueryList(Map<String, Object> map);

    int getFirstLevel(String loginId);

    List<Frame_Dept> findDeptsByLevel(String loginId,String codeLevel);

	int updateByDeptCode(Frame_Dept dept);

	int updateDeptByDeptName(Frame_Dept dept);



}
