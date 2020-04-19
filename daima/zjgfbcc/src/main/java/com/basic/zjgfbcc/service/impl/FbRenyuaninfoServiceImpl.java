package com.basic.zjgfbcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.dao.mysql.FbRenyuaninfoDao;
import com.basic.zjgfbcc.entity.FbRenyuaninfo;
import com.basic.zjgfbcc.service.FbRenyuaninfoService;




@Service("fbRenyuaninfoService")
@Transactional
public class FbRenyuaninfoServiceImpl implements FbRenyuaninfoService {
	@Autowired
	private FbRenyuaninfoDao fbRenyuaninfoDao;

	@Override
	public List<FbRenyuaninfo> getList(Map<String, Object> map){
		return fbRenyuaninfoDao.getList(map);
	}

	@Override
	public List<FbRenyuaninfo> getQueryList(Map<String, Object> map)
	{
	    return fbRenyuaninfoDao.getQueryList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return fbRenyuaninfoDao.getCount(map);
	}

	@Override
	public void save(FbRenyuaninfo fbRenyuaninfo){
		fbRenyuaninfoDao.save(fbRenyuaninfo);
	}

	@Override
	public void update(FbRenyuaninfo fbRenyuaninfo){
		fbRenyuaninfoDao.update(fbRenyuaninfo);
	}

	@Override
	public void deleteBatch(String[] rowGuids){
		fbRenyuaninfoDao.deleteBatch(rowGuids);
	}

    @Override
    public FbRenyuaninfo getDetailByGuid(String rowGuid) {
        return fbRenyuaninfoDao.getDetailByGuid(rowGuid);
    }

    @Override
    public FbRenyuaninfo getDetailByPara(String para,String value) {
        return fbRenyuaninfoDao.getDetailByPara(para,value);
    }

	@Override
	public void deleteAll() {
		fbRenyuaninfoDao.deleteAll();
	}

	@Override
	public void insertAll(List<FbRenyuaninfo> renList) {
		// TODO Auto-generated method stub
		fbRenyuaninfoDao.insertAll(renList);
	}
	
}
