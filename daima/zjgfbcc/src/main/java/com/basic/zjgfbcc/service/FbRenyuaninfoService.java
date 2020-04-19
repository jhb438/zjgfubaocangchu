package com.basic.zjgfbcc.service;

import com.basic.zjgfbcc.entity.FbRenyuaninfo;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 12:51:51
 */
public interface FbRenyuaninfoService {
	
	List<FbRenyuaninfo> getList(Map<String, Object> map);

    List<FbRenyuaninfo> getQueryList(Map<String, Object> map);
	
	int getCount(Map<String, Object> map);
	
	void save(FbRenyuaninfo fbRenyuaninfo);
	
	void update(FbRenyuaninfo fbRenyuaninfo);
	
	void deleteBatch(String[] rowGuids);
	FbRenyuaninfo getDetailByGuid(String rowGuid);

    /**
     * 根据指定参数和值查询一条记录
     * @param para  参数
     * @param value  参数值
     * @return
	 */
     FbRenyuaninfo getDetailByPara(String para,String value);
     /**
      * 删除所有
      */
	void deleteAll();

	void insertAll(List<FbRenyuaninfo> renList);
}
