package com.basic.zjgfbcc.service.impl;

import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.entity.TplMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.dao.mysql.Frame_ModuleDao;
import com.basic.zjgfbcc.entity.Frame_Dept;
import com.basic.zjgfbcc.entity.Frame_Module;
import com.basic.zjgfbcc.service.Frame_ModuleService;

@Service("moduleService")
public class Frame_ModuleServiceImpl implements Frame_ModuleService{
	
	@Autowired
	private Frame_ModuleDao frame_moduleDao;
	 /**
     * 查询模块管理列表
     *
     * @param params 模块管理信息
     * @return 模块管理集合
     */
    @Override
    public List<Frame_Module> selectFrameModuleList(Map<String, Object> params) {
        return frame_moduleDao.selectFrameModuleList(params);
    }


    /**
     * 增加模块
     *
     * @param module 模块管理信息
     * @return 模块管理集合
     */
    @Override
    public void insertFrameModule(Frame_Module module) {
    	frame_moduleDao.insertFrameModule(module);
    }

    /**
     * 更新模块
     *
     * @param module 模块管理信息
     * @return 模块管理集合
     */
    @Override
    public void updateFrameModule(Frame_Module module) {
    	frame_moduleDao.updateFrameModule(module);
    }


    /**
     * 删除模块
     *
     * @param ids 模块id
     * @return 模块管理集合
     */
    @Override
    public void deleteFrameModuleById(String[] rowGuids) {
    	frame_moduleDao.deleteFrameModuleById(rowGuids);
    }

    @Override
	public 	void deleteAllChild(String moduleCode)
	{
		frame_moduleDao.deleteAllChild(moduleCode);
	}

    
    @Override
	public int getCount(Map<String, Object> params) {
		return frame_moduleDao.getCount(params);
	}
	
	@Override
	public JSONArray findModules() {

		//查询所有顶级模块
		List<Frame_Module> deptTopTrees = frame_moduleDao.findModules();
		//递归获取子模块
		return getChildModules(deptTopTrees);
	}

	public JSONArray getChildModules(List<Frame_Module> deptTopTrees){
		JSONArray array = new JSONArray();
		for (Frame_Module frame_Module : deptTopTrees) {
			JSONObject json = new JSONObject();
			json.put("title", frame_Module.getModuleName());
			json.put("pModuleCode", frame_Module.getPmoduleCode());
			json.put("value", frame_Module.getRowGuid());
			json.put("url", frame_Module.getModuleAddr());
			json.put("smallIcon", frame_Module.getSmallIcon());
			//获取子模块
			List<Frame_Module> childDept = frame_moduleDao.getByPmoduleCode(frame_Module.getModuleCode());
			json.put("data", getChildModules(childDept));
			array.add(json);
		}
		return array;
	}


	@Override
	public JSONArray getTrees() {
		//查询所有顶级模块
		List<Frame_Module> deptTopTrees = frame_moduleDao.findModules();
		return getModuleTrees(deptTopTrees);
	}

	@Override
	public String getByModuleCode(String moduleCode) {
		return frame_moduleDao.getByModuleCode(moduleCode);
	}
	@Override
	public List<Frame_Module> GetSubMenu(String pModuleCode, String userGuid) {
		return frame_moduleDao.GetSubMenu(pModuleCode ,userGuid);
	}

	@Override
	public List<Frame_Module> getSubModules(String modulecode) {
		return frame_moduleDao.getSubModules(modulecode) ;
	}

	@Override
	public Frame_Module getDetailByGuid(String rowGuid) {
		return frame_moduleDao.getDetailByGuid(rowGuid);
	}


	private JSONArray getModuleTrees(List<Frame_Module> deptTopTrees) {
		JSONArray array = new JSONArray();
		for (Frame_Module module : deptTopTrees) {
			JSONObject json = new JSONObject();
			json.put("name", module.getModuleName());
			json.put("moduleCode", module.getModuleCode());
			//获取子模块
			List<Frame_Module> childDept = frame_moduleDao.getByPmoduleCode(module.getModuleCode());
			json.put("children", getModuleTrees(childDept));
			array.add(json);
		}
		return array;
	}


	@Override
	public Frame_Module getDetailByMenuName(String menuName) {
		// TODO Auto-generated method stub
		return frame_moduleDao.getDetailByMenuName(menuName);
	}
}
