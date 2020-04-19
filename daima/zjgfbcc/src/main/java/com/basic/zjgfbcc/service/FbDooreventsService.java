package com.basic.zjgfbcc.service;

import com.basic.zjgfbcc.entity.FbDoorevents;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 13:17:50
 */
public interface FbDooreventsService {
	
	List<FbDoorevents> getList(Map<String, Object> map);

    List<FbDoorevents> getQueryList(Map<String, Object> map);
	
	int getCount(Map<String, Object> map);
	
	void save(FbDoorevents fbDoorevents);
	
	void update(FbDoorevents fbDoorevents);
	
	void deleteBatch(String[] rowGuids);
	FbDoorevents getDetailByGuid(String rowGuid);

    /**
     * 根据指定参数和值查询一条记录
     * @param para  参数
     * @param value  参数值
     * @return
	 */
     FbDoorevents getDetailByPara(String para,String value);
}
