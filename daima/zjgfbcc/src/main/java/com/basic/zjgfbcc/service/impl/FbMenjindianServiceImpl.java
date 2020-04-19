package com.basic.zjgfbcc.service.impl;

import com.basic.zjgfbcc.entity.Frame_CodeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.dao.mysql.FbMenjindianDao;
import com.basic.zjgfbcc.entity.FbMenjindian;
import com.basic.zjgfbcc.service.FbMenjindianService;




@Service("fbMenjindianService")
@Transactional
public class FbMenjindianServiceImpl implements FbMenjindianService {


	@Autowired
	private FbMenjindianDao fbMenjindianDao;

	@Override
	public List<FbMenjindian> getList(Map<String, Object> map){
		return fbMenjindianDao.getList(map);
	}

	@Override
	public List<FbMenjindian> getQueryList(Map<String, Object> map)
	{
	    return fbMenjindianDao.getQueryList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return fbMenjindianDao.getCount(map);
	}

	@Override
	public void save(FbMenjindian fbMenjindian){
		fbMenjindianDao.save(fbMenjindian);
	}

	@Override
	public void update(FbMenjindian fbMenjindian){
		fbMenjindianDao.update(fbMenjindian);
	}

	@Override
	public void deleteBatch(String[] rowGuids){
		fbMenjindianDao.deleteBatch(rowGuids);
	}

    @Override
    public FbMenjindian getDetailByGuid(String rowGuid) {
        return fbMenjindianDao.getDetailByGuid(rowGuid);
    }

    @Override
    public FbMenjindian getDetailByPara(String para,String value) {
        return fbMenjindianDao.getDetailByPara(para,value);
    }

	@Override
	public List<Frame_CodeValue> getMenJinList() {
		return fbMenjindianDao.getMenJinList();
	}

	@Override
	public void deleteAll() {
		fbMenjindianDao.deleteAll();
	}

	@Override
	public void insertAll(List<FbMenjindian> list) {
		fbMenjindianDao.insertAll(list);
	}
	
}
