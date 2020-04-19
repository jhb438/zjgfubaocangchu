package com.basic.zjgfbcc.dao.mysql;

import com.basic.zjgfbcc.entity.Frame_Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Frame_ModuleDao {

	default List<Frame_Module> selectFrameModuleList() {
		return selectFrameModuleList();
	}

	List<Frame_Module> selectFrameModuleList(Map<String, Object> params);

	void insertFrameModule(Frame_Module module);

	void updateFrameModule(Frame_Module module);

	void deleteFrameModuleById(String [] rowGuids);

	void deleteAllChild(String moduleCode);

	int getCount(Map<String, Object> params);

	List<Frame_Module> findModules();

	List<Frame_Module> getByPmoduleCode(String moduleCode);

	/**
	 * 通过模块编号查询上级模块
	 * @param moduleCode
	 * @return
	 */
	String getByModuleCode(String moduleCode);

    List<Frame_Module> GetSubMenu(@Param("pModuleCode")String pModuleCode, @Param("userGuid")String userGuid);

	List<Frame_Module> getSubModules(String modulecode);

	Frame_Module getDetailByGuid(String rowGuid);

	Frame_Module getDetailByMenuName(String menuName);
}
