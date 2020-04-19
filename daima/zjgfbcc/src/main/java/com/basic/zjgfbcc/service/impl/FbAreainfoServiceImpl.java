package com.basic.zjgfbcc.service.impl;

import com.basic.zjgfbcc.entity.Frame_CodeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.dao.mysql.FbAreainfoDao;
import com.basic.zjgfbcc.entity.FbAreainfo;
import com.basic.zjgfbcc.service.FbAreainfoService;




@Service("fbAreainfoService")
@Transactional
public class FbAreainfoServiceImpl implements FbAreainfoService {
	@Autowired
	private FbAreainfoDao fbAreainfoDao;

	@Override
	public List<FbAreainfo> getList(Map<String, Object> map){
		return fbAreainfoDao.getList(map);
	}

	@Override
	public List<FbAreainfo> getQueryList(Map<String, Object> map)
	{
	    return fbAreainfoDao.getQueryList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return fbAreainfoDao.getCount(map);
	}

	@Override
	public void save(FbAreainfo fbAreainfo){
		fbAreainfoDao.save(fbAreainfo);
	}

	@Override
	public void update(FbAreainfo fbAreainfo){
		fbAreainfoDao.update(fbAreainfo);
	}

	@Override
	public void deleteBatch(String[] rowGuids){
		fbAreainfoDao.deleteBatch(rowGuids);
	}

    @Override
    public FbAreainfo getDetailByGuid(String rowGuid) {
        return fbAreainfoDao.getDetailByGuid(rowGuid);
    }

    @Override
    public FbAreainfo getDetailByPara(String para,String value) {
        return fbAreainfoDao.getDetailByPara(para,value);
    }

	@Override
	public List<Frame_CodeValue> getAreaList() {
		return fbAreainfoDao.getAreaList();
	}
	
}
