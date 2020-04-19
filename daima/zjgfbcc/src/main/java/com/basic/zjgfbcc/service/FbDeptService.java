package com.basic.zjgfbcc.service;

import com.basic.zjgfbcc.entity.FbDept;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 12:51:01
 */
public interface FbDeptService {
	
	List<FbDept> getList(Map<String, Object> map);

    List<FbDept> getQueryList(Map<String, Object> map);
	
	int getCount(Map<String, Object> map);
	
	void save(FbDept fbDept);
	
	void update(FbDept fbDept);
	
	void deleteBatch(String[] rowGuids);
	FbDept getDetailByGuid(String rowGuid);

    /**
     * 根据指定参数和值查询一条记录
     * @param para  参数
     * @param value  参数值
     * @return
	 */
     FbDept getDetailByPara(String para,String value);
}
