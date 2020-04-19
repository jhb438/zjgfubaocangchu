package com.basic.zjgfbcc.dao.mysql;

import com.basic.zjgfbcc.entity.Frame_CodeValue;
import com.basic.zjgfbcc.entity.Frame_Dept;
import com.basic.zjgfbcc.entity.Frame_User;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 部门管理 Dao层
 *
 * @author wzl
 * @date 2019-03-06
 */

public interface Frame_DeptDao {
	
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
    int insertFrameDept(Frame_Dept frameDept);

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
     * @param rowGuids 需要删除的数据ID
     * @return 结果
     */
    void deleteFrameDeptById(String [] rowGuids);

    void deleteFrameDept();

    /**
     * 批量删除部门管理信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteFrameDeptByIds(Integer[] ids);

	int getCount(Map<String, Object> params);

    /**
     * 查询所有顶级部门
     * <p>Title: findTopDepts</p>
     * <p>Description: 查询所有顶级部门</p>
     *
     * @param tag
     * @return
     * @author hero
     */
    default List<Frame_Dept> findTopDepts() {
        return null;
    }

    List<Frame_Dept> getByPdeptCode(String deptCode);

    /**
     * 通过部门编号查询上级部门
     * @param deptCode
     */
    Frame_Dept getByDeptCode(String deptCode);

	<T> int checkDept(T t);

    /**
     * 通过guid 获取部门名
     */
    String getDeptNameByGuid(String rowGuid);

    List<Frame_Dept> findDepts(@Param("deptcode") String deptcode,@Param("isShowXTGLB")String isShowXTGLB);
    /**
     * 获取指定部门下面的子部门
     */
    List<Frame_Dept> getDeptTreeDeptCode(@Param("deptcode") String deptcode);

    List<Frame_Dept> getAllDeptTreeByDeptCode(@Param("deptcode") String deptcode);

    List<Frame_Dept> getChildDepts(String deptCode);

    Frame_Dept getDetailByGuid(String value);

    void updatePDetCode(@Param("oldPDeptCode") String oldPDeptCode, @Param("newPDeptCode") String newPDeptCode);

	List<Frame_User> getDeviceByDeptGuid(String deptGuid);

	List<Frame_User> getUsersByDeptGuid(String deptGuid);

	List<Frame_Dept> findNewTopDepts(String tag);

	String getDeptGuidByName(String name);

    List<Frame_CodeValue> getDeptList();

    void updateDeptByName(@Param("unit_no")String unit_no,@Param("unit_name")String unit_name);

    Frame_Dept getDetailByPara(@Param("para") String para, @Param("value") String value);

    List<Frame_Dept> getQueryList(Map<String, Object> map);


    int getFirstLevel(String loginId);

    List<Frame_Dept> findDeptsByLevel(@Param("loginId") String loginId,@Param("codeLevel") String codeLevel);

	int updateByDeptCode(Frame_Dept dept);

    int updateDeptByDeptName(Frame_Dept dept);
}
