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
	
}
