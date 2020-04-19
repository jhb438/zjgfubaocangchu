package com.basic.zjgfbcc.dao.mysql;

import java.util.List;

import com.basic.zjgfbcc.entity.FbMenjindian;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 13:17:31
 */
public interface FbMenjindianDao extends BaseDao<FbMenjindian> {

	void deleteAll();

	void insertAll(List<FbMenjindian> list);
	
}
