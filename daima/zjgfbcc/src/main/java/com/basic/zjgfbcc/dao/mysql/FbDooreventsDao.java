package com.basic.zjgfbcc.dao.mysql;

import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.entity.FbDoorevents;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 13:17:50
 */
public interface FbDooreventsDao extends BaseDao<FbDoorevents> {

	void deleteAll();

	void insertAll(List<FbDoorevents> list);

	void deleteNowDays();

	List<Map<String, String>> Statistics(String str, String str2);

	List<Map<String, String>> StatisticsCBS(String string);
	
}
