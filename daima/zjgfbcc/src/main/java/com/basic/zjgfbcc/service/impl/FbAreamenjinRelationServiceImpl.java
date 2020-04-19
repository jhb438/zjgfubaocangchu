package com.basic.zjgfbcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.dao.mysql.FbAreamenjinRelationDao;
import com.basic.zjgfbcc.entity.FbAreamenjinRelation;
import com.basic.zjgfbcc.service.FbAreamenjinRelationService;




@Service("fbAreamenjinRelationService")
@Transactional
public class FbAreamenjinRelationServiceImpl implements FbAreamenjinRelationService {
	@Autowired
	private FbAreamenjinRelationDao fbAreamenjinRelationDao;

	@Override
	public List<FbAreamenjinRelation> getList(Map<String, Object> map){
		return fbAreamenjinRelationDao.getList(map);
	}

	@Override
	public List<FbAreamenjinRelation> getQueryList(Map<String, Object> map)
	{
	    return fbAreamenjinRelationDao.getQueryList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return fbAreamenjinRelationDao.getCount(map);
	}

	@Override
	public void save(FbAreamenjinRelation fbAreamenjinRelation){
		fbAreamenjinRelationDao.save(fbAreamenjinRelation);
	}

	@Override
	public void update(FbAreamenjinRelation fbAreamenjinRelation){
		fbAreamenjinRelationDao.update(fbAreamenjinRelation);
	}

	@Override
	public void deleteBatch(String[] rowGuids){
		fbAreamenjinRelationDao.deleteBatch(rowGuids);
	}

    @Override
    public FbAreamenjinRelation getDetailByGuid(String rowGuid) {
        return fbAreamenjinRelationDao.getDetailByGuid(rowGuid);
    }

    @Override
    public FbAreamenjinRelation getDetailByPara(String para,String value) {
        return fbAreamenjinRelationDao.getDetailByPara(para,value);
    }
	
}
