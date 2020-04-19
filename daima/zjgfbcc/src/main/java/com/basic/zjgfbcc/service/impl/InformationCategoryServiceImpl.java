package com.basic.zjgfbcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.dao.mysql.InformationCategoryDao;
import com.basic.zjgfbcc.entity.Frame_Dept;
import com.basic.zjgfbcc.entity.InformationCategory;
import com.basic.zjgfbcc.service.InformationCategoryService;




@Service("informationCategoryService")
@Transactional
public class InformationCategoryServiceImpl implements InformationCategoryService {
	@Autowired
	private InformationCategoryDao informationCategoryDao;

	@Override
	public List<InformationCategory> findTopTrees(String pcategoryCode) {
		return informationCategoryDao.findTopTrees(pcategoryCode);
	}

	@Override
	public List<InformationCategory> getList(Map<String, Object> map){
		return informationCategoryDao.getList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return informationCategoryDao.getCount(map);
	}

	@Override
	public void save(InformationCategory informationCategory){
		informationCategoryDao.save(informationCategory);
	}

	@Override
	public void update(InformationCategory informationCategory){
		informationCategoryDao.update(informationCategory);
	}

	@Override
	public void deleteBatch(String[] rowIds){
		informationCategoryDao.deleteBatch(rowIds);
	}




	@Override
	public List<InformationCategory> getAllList() {
		// TODO Auto-generated method stub
		return informationCategoryDao.getAllList();
	}

	@Override
	public List<InformationCategory> getCategoryByGuid(String categoryGuid) {
		// TODO Auto-generated method stub
		return informationCategoryDao.getCategoryByGuid(categoryGuid);
	}

	@Override
	public String getNextCateGoryCode(String pcategoryCode) {
		return informationCategoryDao.getNextCateGoryCode(pcategoryCode);
	}

	@Override
	public InformationCategory getDetailByGuid(String rowGuid) {
		return informationCategoryDao.getDetailByGuid(rowGuid);
	}

	@Override
	public List<InformationCategory> findTopTreesByGuid(String rowGuid) {
		return informationCategoryDao.findTopTreesByGuid(rowGuid);
	}

}
