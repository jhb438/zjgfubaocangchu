package com.basic.zjgfbcc.dao.mysql;

import java.util.List;

import com.basic.zjgfbcc.entity.FbMenjindian;
import com.basic.zjgfbcc.entity.Frame_CodeValue;

import java.util.List;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 13:17:31
 */
public interface FbMenjindianDao extends BaseDao<FbMenjindian> {

    List<Frame_CodeValue> getMenJinList();

	void deleteAll();

	void insertAll(List<FbMenjindian> list);
	
}
