package com.basic.zjgfbcc.service;

import com.basic.zjgfbcc.entity.FbAreainfo;
import com.basic.zjgfbcc.entity.Frame_CodeValue;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 14:40:28
 */
public interface FbAreainfoService {
	
	List<FbAreainfo> getList(Map<String, Object> map);

    List<FbAreainfo> getQueryList(Map<String, Object> map);
	
	int getCount(Map<String, Object> map);
	
	void save(FbAreainfo fbAreainfo);
	
	void update(FbAreainfo fbAreainfo);
	
	void deleteBatch(String[] rowGuids);
	FbAreainfo getDetailByGuid(String rowGuid);

    /**
     * 根据指定参数和值查询一条记录
     * @param para  参数
     * @param value  参数值
     * @return
	 */
     FbAreainfo getDetailByPara(String para,String value);

    List<Frame_CodeValue> getAreaList();
}
