package com.basic.zjgfbcc.service;

import com.basic.zjgfbcc.entity.FbAreamenjinRelation;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 14:41:28
 */
public interface FbAreamenjinRelationService {
	
	List<FbAreamenjinRelation> getList(Map<String, Object> map);

    List<FbAreamenjinRelation> getQueryList(Map<String, Object> map);
	
	int getCount(Map<String, Object> map);
	
	void save(FbAreamenjinRelation fbAreamenjinRelation);
	
	void update(FbAreamenjinRelation fbAreamenjinRelation);
	
	void deleteBatch(String[] rowGuids);
	FbAreamenjinRelation getDetailByGuid(String rowGuid);

    /**
     * 根据指定参数和值查询一条记录
     * @param para  参数
     * @param value  参数值
     * @return
	 */
     FbAreamenjinRelation getDetailByPara(String para,String value);
}
