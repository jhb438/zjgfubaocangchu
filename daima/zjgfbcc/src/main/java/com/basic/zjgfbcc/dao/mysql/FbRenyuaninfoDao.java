package com.basic.zjgfbcc.dao.mysql;

import java.util.List;

import com.basic.zjgfbcc.entity.FbRenyuaninfo;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 12:51:51
 */
public interface FbRenyuaninfoDao extends BaseDao<FbRenyuaninfo> {

	void deleteAll();

	void insertAll(List<FbRenyuaninfo> renList);
	
}
