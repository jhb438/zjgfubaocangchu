//package com.basic.zjgfbcc.dao.sqlServer;
//
//import com.basic.zjgfbcc.entity.Frame_User;
//import org.apache.ibatis.annotations.Param;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//import java.util.Map;
///**
// * <p>Title: UserService</p>
// * <p>Description: 用户接口层</p>
// * @author my
// */
//public interface UserDao {
//
//	/**
//	 * 删除用户管理信息
//	 *
//	 * @param ids 需要删除的数据ID
//	 * @return 结果
//	 */
//	int delete(Integer[] ids);
//
//	/**
//	 * 批量删除用户管理信息
//	 *
//	 * @param rowGuid 需要删除的数据ID
//	 * @return 结果
//	 */
//	int deleteUserById(String[] rowGuid);
//
//	/**
//	 * 新增用户信息
//	 *
//	 * @param user 用户管理信息
//	 * @return 结果
//	 */
//	int insert(Frame_User user);
//
//	/**
//	 * 修改用户管理
//	 *
//	 * @param user 用户管理信息
//	 * @return 结果
//	 */
//	int update(Frame_User user);
//	int updateAdmin(Frame_User user);
//
//	/**
//	 * 获取登录时间
//	 * @param frame_user
//	 * @return
//	 */
//	int updateLoginTime(Frame_User frame_user);
//
//	/**
//	 * 重置用户密码
//	 *
//	 * @param password
//	 *用户管理信息
//	 * @return 结果
//	 */
//	void resetPasswordById(@Param("password") String password,@Param("rowId") Integer[] rowId);
//
//	/**
//	 * 启用用户
//	 *
//	 * @param ids 需要启用的数据ID
//	 * @return 结果
//	 */
//	int enableUserById(Integer[] ids);
//
//	/**
//	 * 禁用用户
//	 *
//	 * @param ids 需要禁用的数据ID
//	 * @return 结果
//	 */
//	int forbidUserById(Integer[] ids);
//
//	/**
//	 * 修改排序号
//	 *
//	 * @param sortSq 排序信息
//	 * @return 结果
//	 */
//	void saveSortSq(@Param("rowId")Integer[] rowId,@Param("sortSq") Integer[] sortSq);
//
////	/**
////	 * 查询用户信息
////	 *
////	 * @param id 用户ID
////	 * @return 用户信息
////	 */
////	User getById(Integer id);
//
//	/**
//	 * 查询用户信息
//	 *
//	 * @param loginId 用户登录名
//	 * @return 部门管理信息
//	 */
//	Frame_User getFrameUserByLoginId(@Param("loginId")String loginId);
//
//	/**
//	 * 点击角色获取用户
//	 * @param roleGuid
//	 * @return
//	 */
//	List<Frame_User> getUserFromRole(@Param("roleGuid")String roleGuid);
//
//	/**
//	 * 查询用户管理列表
//	 *
//	 * @param params 用户管理信息
//	 * @return 用户集合
//	 */
//	List<Frame_User> getUser(Map<String, Object> params);
//
//	int getCount(Map<String, Object> params);
//
//	Frame_User findUserByGuid(@Param("guid")String guid);
//
//	/**
//	 * 用户登录名重复检测
//	 *
//	 * @param t
//	 * @return
//	 */
//	<T> int checkUser(T t);
//
//	String getDeptByGuid(@Param("rowGuid")String rowGuid);
//
//	/**
//	 * 验证旧密码
//	 * @param rowGuid
//	 * @return
//	 */
//	String checkOldPassword(@Param("rowGuid") String rowGuid);
//
//	/**
//	 * 更新新密码
//	 * @param rowGuid
//	 * @return
//	 */
//	void updateNewPassword(@Param("rowGuid") String rowGuid,@Param("password")String password);
//     //获取所有正常用户
//	 List<Frame_User> getAllUserList();
//
//    List<Frame_User> getUserFromRoleName(String roleName);
//
//	List<Frame_User> getUserListByDeptGuid(String deptGuid);
//
//	Frame_User getDetailByGuid(@Param("rowGuid") String rowGuid);
//
//	List<Frame_User> ceshi();
//}
