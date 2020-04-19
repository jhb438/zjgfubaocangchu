package com.basic.zjgfbcc.service;

import com.basic.zjgfbcc.entity.InformationCategory;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @date 2019-04-09 11:30:38
 */
public interface InformationCategoryService {

	List<InformationCategory> findTopTrees(String pcategoryCode);

	List<InformationCategory> getList(Map<String, Object> map);
	
	int getCount(Map<String, Object> map);
	
	void save(InformationCategory informationCategory);
	
	void update(InformationCategory informationCategory);
	
	void deleteBatch(String[] rowGuids);

	List<InformationCategory> getAllList();

	List<InformationCategory> getCategoryByGuid(String categoryGuid);


	String getNextCateGoryCode(String pcategoryCode);

	InformationCategory getDetailByGuid(String rowGuid);

	List<InformationCategory> findTopTreesByGuid(String rowGuid);
}
