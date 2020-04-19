package com.basic.zjgfbcc.service;

import com.alibaba.fastjson.JSONArray;
import com.basic.zjgfbcc.entity.Frame_Module;

import java.util.List;
import java.util.Map;

public interface Frame_ModuleService {
	/**
     * 查询部门管理列表
     *
     * @param params 部门管理信息
     * @return 部门管理集合
     */
     List<Frame_Module> selectFrameModuleList(Map<String, Object> params);

    /**
     * 新增部门管理
     *
     * @param module 部门管理信息
     * @return 结果
     */
    void insertFrameModule(Frame_Module module);

    /**
     * 修改部门管理
     *
     * @param module 部门管理信息
     * @return 结果
     */
    void updateFrameModule(Frame_Module module);
    
    /**
     * 删除部门管理信息
     *
     * @param rowGuids 需要删除的数据ID
     * @return 结果
     */
    void deleteFrameModuleById(String[] rowGuids);

    void deleteAllChild(String moduleCode);
   

	int getCount(Map<String, Object> params);

	JSONArray findModules();

	JSONArray getTrees();

	String getByModuleCode(String moduleCode);

    List<Frame_Module> GetSubMenu(String pModuleCode, String userGuid);

    List<Frame_Module> getSubModules(String modulecode);

    Frame_Module getDetailByGuid(String rowGuid);

	Frame_Module getDetailByMenuName(String menuName);
}
