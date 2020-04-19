package com.basic.zjgfbcc.dao.mysql;

import java.util.List;

import com.basic.zjgfbcc.entity.FbDept;

import java.util.List;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 12:51:01
 */
public interface FbDeptDao extends BaseDao<FbDept> {

    List<FbDept> findDepts(String orgIndexCode);

    List<FbDept> getChildDepts(String orgIndexCode);

	void deleteAll();

	void insertAll(List<FbDept> list);
	
}
