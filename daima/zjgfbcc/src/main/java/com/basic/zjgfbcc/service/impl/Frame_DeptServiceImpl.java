package com.basic.zjgfbcc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.dao.mysql.Frame_DeptDao;
import com.basic.zjgfbcc.entity.Frame_CodeValue;
import com.basic.zjgfbcc.entity.Frame_Dept;
import com.basic.zjgfbcc.entity.Frame_User;
import com.basic.zjgfbcc.service.Frame_DeptService;

import com.google.gson.JsonArray;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理 IMPL服务层
 *
 * @author wzl
 * @date 2019-03-06
 */
@Service("deptService")
public class Frame_DeptServiceImpl implements Frame_DeptService {

    @Autowired
    private Frame_DeptDao frame_deptDao;


    /**
     * 查询部门管理信息
     *
     * @param rowId 部门管理ID
     * @return 部门管理信息
     */
    @Override
    public Frame_Dept selectFrameDeptById(Integer rowId) {
        return frame_deptDao.selectFrameDeptById(rowId);
    }

    /**
     * 查询部门名字
     *
     * @param name 部门管理信息
     * @return 部门管理集合
     */
    @Override
    public Frame_Dept selectFrameDeptByName(String name) {
        return frame_deptDao.selectFrameDeptByName(name);
    }

    /**
     * 查询部门管理列表
     *
     * @param params 部门管理信息
     * @return 部门管理集合
     */
    @Override
    public List<Frame_Dept> selectFrameDeptList(Map<String, Object> params) {
        return frame_deptDao.selectFrameDeptList(params);
    }


    /**
     * 增加部门
     *
     * @param frameDept 部门管理信息
     * @return 部门管理集合
     */
    @Override
    public void insertFrameDept(Frame_Dept frameDept) {
         frame_deptDao.insertFrameDept(frameDept);
    }

    /**
     * 更新部门
     *
     * @param frameDept 部门管理信息
     * @return 部门管理集合
     */
    @Override
    public int updateFrameDept(Frame_Dept frameDept) {
       return   frame_deptDao.updateFrameDept(frameDept);
    }


    /**
     * 删除部门
     *
     * @param ids 部门id
     * @return 部门管理集合
     */
    @Override
    public void deleteFrameDeptById(String [] rowGuids) {
        frame_deptDao.deleteFrameDeptById(rowGuids);
    }
    
    //递归获取
    public Integer[] deleteFrameDeptById2(Integer rowId,Integer[] ids){
    	List<Integer> list = new ArrayList<>(Arrays.asList(ids));
    	Frame_Dept dept = frame_deptDao.selectFrameDeptById(rowId);
    	List<Frame_Dept> deptList = frame_deptDao.getByPdeptCode(dept.getDeptCode());
    	if (deptList != null &&deptList.size() != 0) {
			for (int i = 0; i < deptList.size(); i++) {
				list.add(deptList.get(i).getRowId());
				ids=new Integer[list.size()];
		    	list.toArray(ids);//3,5   //3,5,1   
		    	ids = deleteFrameDeptById2(deptList.get(i).getRowId(),ids);
			}
		}
    	return ids;
    }

    /**
     * 批量删除部门
     *
     * @param ids 部门id
     * @return 部门管理集合
     */
    @Override
    public void deleteFrameDeptByIds(Integer[] ids) {
        frame_deptDao.deleteFrameDeptByIds(ids);
    }
    @Override
    public void deleteFrameDept(){frame_deptDao.deleteFrameDept();}

	@Override
	public int getCount(Map<String, Object> params) {
		return frame_deptDao.getCount(params);
	}
	
	@Override
	public JSONArray findTopDepts() {
		
		//查询所有顶级部门
		List<Frame_Dept> deptTopTrees = frame_deptDao.findTopDepts();
		//递归获取子部门
		return getChildDepts(deptTopTrees);
	}
	/*
	获取指定部门下面的子部门
	 */
	@Override
    public List<Frame_Dept> getDeptTreeDeptCode(String deptcode)
    {
        return frame_deptDao.getDeptTreeDeptCode(deptcode);
    }
    @Override
    public  List<Frame_Dept> getAllDeptTreeByDeptCode(String deptcode)
    {
        return frame_deptDao.getAllDeptTreeByDeptCode(deptcode);
    }

	
	@Override
	public List<Frame_Dept> findNewTopDepts(String tag) {
		// TODO Auto-generated method stub
		//查询所有顶级部门
		List<Frame_Dept> deptTopTrees = frame_deptDao.findNewTopDepts(tag);
		return deptTopTrees;
	}
	
    @Override
    public Frame_Dept getByDeptCode(String deptCode) {
        return frame_deptDao.getByDeptCode(deptCode);
    }

    public JSONArray getChildDepts(List<Frame_Dept> deptTopTrees){
		JSONArray array = new JSONArray();
		for (Frame_Dept frame_Dept : deptTopTrees) {
			JSONObject json = new JSONObject();
			json.put("deptName", frame_Dept.getDeptName());
            json.put("name", frame_Dept.getDeptName());
			json.put("deptCode", frame_Dept.getDeptCode());
			json.put("rowGuid", frame_Dept.getRowGuid());
			//获取子部门
			List<Frame_Dept> childDept = frame_deptDao.getByPdeptCode(frame_Dept.getDeptCode());
			json.put("children", getChildDepts(childDept));
			array.add(json);
		}
		return array;
	}

	@Override
	public <T> int checkDept(T t) {
		// TODO Auto-generated method stub
		return frame_deptDao.checkDept(t);
	}

    @Override
    public void getDeptNameByGuid(String rowGuid) {
        frame_deptDao.getDeptNameByGuid(rowGuid);
    }

    @Override
    public List<Frame_Dept> findDepts(String deptcode,String isShowXTGLB) {
        return frame_deptDao.findDepts(deptcode,isShowXTGLB);
    }

    @Override
    public List<Frame_Dept> getChildDepts(String deptCode) {
        return frame_deptDao.getChildDepts(deptCode);
    }

    @Override
    public Frame_Dept getDetailByGuid(String value) {
        return frame_deptDao.getDetailByGuid(value);
    }

    @Override
    public void updatePDetCode(String oldDeptCode, String newDeptCode) {
        frame_deptDao.updatePDetCode(oldDeptCode,newDeptCode);
    }

	@Override
	public List<Frame_User> getDeviceByDeptGuid(String deptGuid) {
		// TODO Auto-generated method stub
		return frame_deptDao.getDeviceByDeptGuid(deptGuid);
	}
	
	@Override
	public List<Frame_User> getUsersByDeptGuid(String deptGuid) {
		// TODO Auto-generated method stub
		return frame_deptDao.getUsersByDeptGuid(deptGuid);
	}

	@Override
	public String getDeptGuidByName(String name) {
		// TODO Auto-generated method stub
		return frame_deptDao.getDeptGuidByName(name);
	}
    @Override
    public List<Frame_CodeValue> getDeptList(){
	    return  frame_deptDao.getDeptList();
    }

    /*
    通过接口保存部门
     */
    @Override
    public void insertDeptByApi(JSONArray deptObj,String pdeptCode)
    {
        if(deptObj.size()>0)
        {
            Frame_Dept model=new Frame_Dept();
            for(int i=0;i<deptObj.size();i++) {
                JSONObject firstObj=deptObj.getJSONObject(i);
                model.setRowGuid(firstObj.getString("member"));
                model.setRowId(Integer.parseInt(firstObj.getString("id")));
                model.setDeptName(firstObj.getString("dept_name"));
                model.setDeptCode(firstObj.getString("id"));
                model.setOucode("");
                model.setPdeptCode(pdeptCode);
                this.insertFrameDept(model);

                //更新deptCode
                String DeptCode="";
                if(model.getPdeptCode()==null||model.getPdeptCode().equals(""))
                {
                    DeptCode=model.getRowId().toString();
                }
                else
                {
                    DeptCode=model.getPdeptCode()+"."+model.getRowId().toString();
                }
                model.setDeptCode(DeptCode);
                this.updateFrameDept(model);

                JSONArray secondArray=firstObj.getJSONArray("children");
                if(secondArray.size()>0)
                {
                    this.insertDeptByApi(secondArray,pdeptCode+"."+firstObj.getString("id"));
                }
            }
        }
    }
    @Override
    public void updateDeptByName(String unit_no,String unit_name)
    {
         frame_deptDao.updateDeptByName(unit_no,unit_name);
    }

    @Override
    public Frame_Dept getDetailByPara(String para, String value) {
        return frame_deptDao.getDetailByPara(para,value);
    }

    @Override
    public List<Frame_Dept> getChildDeptByOucode(String oucode) {
       Frame_Dept model=this.getDetailByPara("oucode",oucode);
       if(!model.getPdeptCode().equals("2"))
       {
           return  this.getChildDepts(model.getPdeptCode());
       }
       else
       {
           return  this.getChildDepts(model.getDeptCode());
       }
    }

    @Override
    public List<Frame_Dept> getQueryList(Map<String, Object> map) {
        return frame_deptDao.getQueryList(map);
    }

    @Override
    public int getFirstLevel(String loginId) {
        return frame_deptDao.getFirstLevel(loginId);
    }

    @Override
    public List<Frame_Dept> findDeptsByLevel(String loginId, String codeLevel) {
        return frame_deptDao.findDeptsByLevel(loginId,codeLevel);
    }

	@Override
	public int updateByDeptCode(Frame_Dept dept) {
		// TODO Auto-generated method stub
		return frame_deptDao.updateByDeptCode(dept);
	}

    @Override
    public int updateDeptByDeptName(Frame_Dept dept) {
        return frame_deptDao.updateDeptByDeptName(dept);
    }

}
