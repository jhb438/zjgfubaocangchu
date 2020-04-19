package com.basic.zjgfbcc.service;

import com.basic.zjgfbcc.entity.FbMenjindian;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 13:17:31
 */
public interface FbMenjindianService {
	
	List<FbMenjindian> getList(Map<String, Object> map);

    List<FbMenjindian> getQueryList(Map<String, Object> map);
	
	int getCount(Map<String, Object> map);
	
	void save(FbMenjindian fbMenjindian);
	
	void update(FbMenjindian fbMenjindian);
	
	void deleteBatch(String[] rowGuids);
	FbMenjindian getDetailByGuid(String rowGuid);

    /**
     * 根据指定参数和值查询一条记录
     * @param para  参数
     * @param value  参数值
     * @return
	 */
     FbMenjindian getDetailByPara(String para,String value);
}
