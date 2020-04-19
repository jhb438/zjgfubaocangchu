package com.basic.zjgfbcc.dao.mysql;

import com.basic.zjgfbcc.entity.Frame_ModuleRight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Frame_ModuleRightDao {

	//批量新增
	void insertModuleRightBatch(List<Frame_ModuleRight> frameModuleRights);

	//批量删除
	void deleteModuleRightBatch(@Param("roleGuid")String roleGuid);

	void insertFrameModuleRight(Frame_ModuleRight moduleRight);

	void updateFrameModuleRight(Frame_ModuleRight moduleRight);

	void deleteFrameModuleRightById(Integer[] ids);

	void deleteModuleRoleByGuid(@Param("rowGuid")String[] rowGuid);

	default List<Frame_ModuleRight> selectModuleByRoleGuid() {
		return selectModuleByRoleGuid();
	}

	List<Frame_ModuleRight> selectModuleByRoleGuid(@Param("roleGuid")String roleGuid);

}
