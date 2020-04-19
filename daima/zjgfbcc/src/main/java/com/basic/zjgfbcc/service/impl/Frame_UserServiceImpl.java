package com.basic.zjgfbcc.service.impl;

import com.basic.zjgfbcc.dao.mysql.Frame_DeptDao;
import com.basic.zjgfbcc.dao.mysql.Frame_UserDao;
import com.basic.zjgfbcc.entity.Frame_Dept;
import com.basic.zjgfbcc.entity.Frame_Role;
import com.basic.zjgfbcc.entity.Frame_User;
import com.basic.zjgfbcc.service.Frame_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: UserService</p>
 * <p>Description: 用户服务层</p>
 *
 * @author my
 */
@Service("userService")
public class Frame_UserServiceImpl implements Frame_UserService {


    @Autowired
    private Frame_UserDao frame_userDao;
    
//    @Autowired
//    private com.basic.zjgfbcc.dao.sqlServer.UserDao frame_UserDao2;

    @Autowired
    private Frame_DeptDao frame_deptDao;

    //添加
    @Override
    public void insert(Frame_User user) {
        frame_userDao.insert(user);
    }
    
    @Override
	public List<Frame_User> ceshi() {
		// TODO Auto-generated method stub
		return frame_userDao.getAllUserList();
	}

    //修改
    @Override
    public void update(Frame_User user) {
        frame_userDao.update(user);
    }

    @Override
    public void updateAdmin(Frame_User user) {
        frame_userDao.updateAdmin(user);
    }

    @Override
    public void updateLoginTime(Frame_User frameUser) {
        frame_userDao.updateLoginTime(frameUser);
    }

    @Override
    public void delete(Integer[] ids) {

    }

    //删除
//    @Override
//    public void delete(Integer[] ids) {
//        frame_userDao.deleteUserById(ids);
//    }

    //批量删除
    @Override
    public void deleteUserById(String[] rowGuid) {
        frame_userDao.deleteUserById(rowGuid);
    }
    //删除特定用户
    @Override
    public  void deleteUsers(){frame_userDao.deleteUsers();}

    //启用用户
    @Override
    public void enableUserById(String[] rowGuids) {
        frame_userDao.enableUserById(rowGuids);
    }

    //禁用用户
    @Override
    public void forbidUserById(String[] rowGuids) {
        frame_userDao.forbidUserById(rowGuids);
    }

    @Override
    public void resetPasswordById(String password, String[] rowGuids) {
        frame_userDao.resetPasswordById(password,rowGuids);
    }
    //保存排序号
    @Override
    public void saveSortSq(Integer[] rowId,Integer[] sortSq) {
        frame_userDao.saveSortSq(rowId,sortSq);
    }

//    //通过id查询
//    @Override
//    public User getById(int id) {
//        return frame_userDao.getById(id);
//    }

    //通过姓名查询
    @Override
    public Frame_User getFrameUserByLoginId(String loginId) {
        return frame_userDao.getFrameUserByLoginId(loginId);
    }

    //获取所有列表信息
    @Override
    public List<Frame_User> getUser(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return frame_userDao.getUser(params);
    }

    @Override
    public List<Frame_User> getUserFromRole(String roleGuid) {
        return frame_userDao.getUserFromRole(roleGuid);
    }

    @Override
    public List<Frame_User> getUserFromRoleName(String roleName) {

        return frame_userDao.getUserFromRoleName(roleName);
    }


    @Override
    public int getCount(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return frame_userDao.getCount(params);
    }

	@Override
	public Frame_User findUserByGuid(String guid) {
		// TODO Auto-generated method stub
		return frame_userDao.findUserByGuid(guid);
	}

    @Override
    public <T> int checkUser(T t) {
        return frame_userDao.checkUser(t);
    }

    @Override
    public Map<String, Object> getDeptByGuid(String rowGuid) {
        String deptGuid= frame_userDao.getDeptByGuid(rowGuid);
        String deptName = frame_deptDao.getDeptNameByGuid(deptGuid);
        Map<String, Object> map = new HashMap<>();
        map.put("deptGuid", deptGuid);
        map.put("deptName", deptName);
        return map;
    }

    @Override
    public String checkOldPassword(String rowGuid) {
        return frame_userDao.checkOldPassword(rowGuid);
    }

    @Override
    public void updateNewPassword(String rowGuid,String password) {
         frame_userDao.updateNewPassword(rowGuid,password);
    }

    @Override
    public List<Frame_User> getAllUserList() {

        return frame_userDao.getAllUserList();
    }

	@Override
	public List<Frame_User> getUserListByDeptGuid(String deptGuid) {
		// TODO Auto-generated method stub
		return frame_userDao.getUserListByDeptGuid(deptGuid);
	}

    @Override
    public Frame_User getDetailByGuid(String rowGuid) {
        return frame_userDao.getDetailByGuid(rowGuid);
    }


    @Override
    public int getOnLineUserCount()
    {
        return frame_userDao.getOnLineUserCount();
    }

    @Override
    public  List<Map<String,Object>> getAllDeviceList(){return frame_userDao.getAllDeviceList();}



    @Override
    public List<Frame_User> getDeviceListByDeptGuid(String deptGuid,String status)
    {
        ArrayList<String> deptGuids=new ArrayList<>();
        Frame_Dept model=frame_deptDao.getDetailByGuid(deptGuid);
        List<Frame_Dept> list=frame_deptDao.getAllDeptTreeByDeptCode(model.getDeptCode());
        if(list.size()>0)
        {
            for(int i=0;i<list.size();i++) {
                deptGuids.add(list.get(i).getRowGuid());
            }
            deptGuids.add(deptGuid);
        }
        else
        {
            deptGuids.add(deptGuid);
        }
        return frame_userDao.getDeviceListByDeptGuid(deptGuids,status);
    }

    @Override
    public  Frame_User  getDetailByNameAndDeptGuid(String userName,String deptGuid)
    {
        return  frame_userDao.getDetailByNameAndDeptGuid(userName,deptGuid);
    }

    @Override
    public Frame_User getDetailByPara(String para, String value) {
        return frame_userDao.getDetailByPara(para,value);
    }

	@Override
	public List<Frame_Role> getRolesByUserGuid(String rowGuid) {
		// TODO Auto-generated method stub
		return frame_userDao.getRolesByUserGuid(rowGuid);
	}

    @Override
    public List<Frame_User> getUserByRoleName(Map<String, Object> params) {
        return frame_userDao.getUserByRoleName(params);
    }
}


