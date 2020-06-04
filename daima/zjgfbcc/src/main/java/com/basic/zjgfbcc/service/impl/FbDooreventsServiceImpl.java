package com.basic.zjgfbcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.common.utils.StringUtil;
import com.basic.zjgfbcc.dao.mysql.FbDooreventsDao;
import com.basic.zjgfbcc.dao.postSql.hikKuDao;
import com.basic.zjgfbcc.entity.FbDoorevents;
import com.basic.zjgfbcc.service.FbDooreventsService;




@Service("fbDooreventsService")
@Transactional
public class FbDooreventsServiceImpl implements FbDooreventsService {
	@Autowired
	private FbDooreventsDao fbDooreventsDao;
	
	@Autowired
	com.basic.zjgfbcc.service.RedisService RedisService;
	
	@Autowired
	hikKuDao hik;

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
    public List<FbDoorevents> getEventListByRu(String areaName, String isCBS, String isVTZ,String isKCSJ,String isFK,String personName,String orgName) {
		return fbDooreventsDao.getEventListByRu(areaName,isCBS,isVTZ,isKCSJ,isFK,personName,orgName);
    }

    @Override
    public List<FbDoorevents> getEventListByShiJiChu(String areaName, String isCBS, String isVTZ,String isKCSJ,String isFK,String personName,String orgName) {

		return fbDooreventsDao.getEventListByShiJiChu(areaName,isCBS,isVTZ,isKCSJ,isFK,personName,orgName);
    }

    @Override
    public FbDoorevents getLastDataById(String personId) {

		return fbDooreventsDao.getLastDataById(personId);
    }

    @Override
	public void deleteSevenBe() {
		fbDooreventsDao.deleteSevenBe();
	}

	@Override
	public FbDoorevents getlastData() {
		return fbDooreventsDao.getlastData();
	}

	@Override
	public void deleteByTowDayBefore() {
		fbDooreventsDao.deleteByTowDayBefore();
	}
	
	
	
	@Override
	public void getCurrentDay() {
    	//删除今天的数据
    	RedisService.set("timeFlag", "false");
//    	FbDooreventsService.deleteByTowDayBefore();
    	deleteNowDays();
    	
    	List<FbDoorevents> list = hik.getEventsNow();
    	if(list.size() > 0){
    		FbDoorevents model = null;
    		FbDoorevents obj = null;
    		Date beginDate = null;
    		Date endDate = null;
    		for(int i=0;i<list.size();i++){
    			obj =list.get(i);
				//首先找出该人员最新的一条记录
				if(obj.getPersonId()!=null&&!obj.getPersonId().equals("")) {
					model = getLastDataById(obj.getPersonId());
					if(model!=null && !StringUtil.isNullOrEmpty(model.getEventTime()) && !StringUtil.isNullOrEmpty(obj.getEventTime())) {
						beginDate = DateUtil.changeStrToTime(model.getEventTime());
						endDate = DateUtil.changeStrToTime(obj.getEventTime());
						if (DateUtil.calculatetimeGapSecond(beginDate, endDate) > 5) {
							save(obj);
						}
					}
					else
					{
						save(obj);
					}

				}
    			
    		}
    	}
    	RedisService.set("timeFlag", "true");
		
	}

	@Override
	public void getYestDay() {
		//第一次运行 获取昨天到至今的所有数据（success数据）
    	//删除昨天到现在的数据
    	RedisService.set("timeFlag", "false");
    	deleteYestDay();
    	
    	List<FbDoorevents> list = hik.getEventsYestDay();
    	if(list.size() > 0){
    		FbDoorevents model = null;
    		FbDoorevents obj = null;
    		Date beginDate = null;
    		Date endDate = null;
    		for(int i=0;i<list.size();i++){
    			obj =list.get(i);
				//首先找出该人员最新的一条记录
				if(obj.getPersonId()!=null&&!obj.getPersonId().equals("")) {
					model = getLastDataById(obj.getPersonId());
					if(model!=null && !StringUtil.isNullOrEmpty(model.getEventTime()) && !StringUtil.isNullOrEmpty(obj.getEventTime())) {
						beginDate = DateUtil.changeStrToTime(model.getEventTime());
						endDate = DateUtil.changeStrToTime(obj.getEventTime());
						if (DateUtil.calculatetimeGapSecond(beginDate, endDate) > 5) {
							save(obj);
						}
					}
					else
					{
						save(obj);
					}

				}
    			
    		}
    	}
    	RedisService.set("timeFlag", "true");
	}

	private void deleteYestDay() {
		fbDooreventsDao.deleteYestDay();
	}

}
