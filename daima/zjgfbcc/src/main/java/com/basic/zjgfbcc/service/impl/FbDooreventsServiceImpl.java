package com.basic.zjgfbcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.dao.mysql.FbDooreventsDao;
import com.basic.zjgfbcc.entity.FbDoorevents;
import com.basic.zjgfbcc.service.FbDooreventsService;




@Service("fbDooreventsService")
@Transactional
public class FbDooreventsServiceImpl implements FbDooreventsService {
	@Autowired
	private FbDooreventsDao fbDooreventsDao;

	@Override
	public List<FbDoorevents> getList(Map<String, Object> map){
		return fbDooreventsDao.getList(map);
	}

	@Override
	public List<FbDoorevents> getQueryList(Map<String, Object> map)
	{
	    return fbDooreventsDao.getQueryList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return fbDooreventsDao.getCount(map);
	}

	@Override
	public void save(FbDoorevents fbDoorevents){
		fbDooreventsDao.save(fbDoorevents);
	}

	@Override
	public void update(FbDoorevents fbDoorevents){
		fbDooreventsDao.update(fbDoorevents);
	}

	@Override
	public void deleteBatch(String[] rowGuids){
		fbDooreventsDao.deleteBatch(rowGuids);
	}

    @Override
    public FbDoorevents getDetailByGuid(String rowGuid) {
        return fbDooreventsDao.getDetailByGuid(rowGuid);
    }

    @Override
    public FbDoorevents getDetailByPara(String para,String value) {
        return fbDooreventsDao.getDetailByPara(para,value);
    }

	@Override
	public void deleteAll() {
		fbDooreventsDao.deleteAll();
	}

	@Override
	public void insertAll(List<FbDoorevents> list) {
		fbDooreventsDao.insertAll(list);
	}

	@Override
	public void deleteNowDays() {
		fbDooreventsDao.deleteNowDays();
	}

	@Override
	public List<Map<String, String>> Statistics(String str,String str2) {
		// TODO Auto-generated method stub
		return fbDooreventsDao.Statistics(str,str2);
	}

	@Override
	public List<Map<String, String>> StatisticsCBS(String string) {
		// TODO Auto-generated method stub
		return fbDooreventsDao.StatisticsCBS(string);
	}

    @Override
    public List<FbDoorevents> getEventListByRu(String areaName, String isCBS, String isVTZ) {
		return fbDooreventsDao.getEventListByRu(areaName,isCBS,isVTZ);
    }

    @Override
    public List<FbDoorevents> getEventListByShiJiChu(String areaName, String isCBS, String isVTZ) {

		return fbDooreventsDao.getEventListByShiJiChu(areaName,isCBS,isVTZ);
    }

}
