package com.basic.zjgfbcc.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.alibaba.fastjson.JSON;
import com.basic.zjgfbcc.common.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.controller.FbDeptController;
import com.basic.zjgfbcc.entity.FbDept;
import com.basic.zjgfbcc.entity.FbDoorevents;
import com.basic.zjgfbcc.entity.FbMenjindian;
import com.basic.zjgfbcc.entity.FbRenyuaninfo;
import com.basic.zjgfbcc.service.FbDeptService;
import com.basic.zjgfbcc.service.FbDooreventsService;
import com.basic.zjgfbcc.service.FbMenjindianService;
import com.basic.zjgfbcc.service.FbRenyuaninfoService;
import com.basic.zjgfbcc.service.api.HkApiService;


/**
 * fbcc 
 * @author hero
 *
 */
@Component
public class HkThread {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	HkApiService hkApiService;
	
	@Autowired
	FbRenyuaninfoService FbRenyuaninfoService;
	
	@Autowired
	FbDeptService FbDeptService;
	
	@Autowired
	FbMenjindianService FbMenjindianService;
	
	@Autowired
	FbDooreventsService FbDooreventsService;
	
	@Async("myAsync")
	public Future<String> personList() throws InterruptedException{
		logger.info("personList started---");
		long start = System.currentTimeMillis();

		String resStr = hkApiService.personList();
		JSONObject obj = JSONObject.parseObject(resStr);
		if(obj != null && "0".equals(obj.getString("code")) && "SUCCESS".equals(obj.getString("msg"))){
			JSONArray arr = obj.getJSONObject("data").getJSONArray("list");
			//删除用户 再插用户
			FbRenyuaninfoService.deleteAll();
			List<FbRenyuaninfo> list = JSONObject.parseArray(arr.toJSONString(), FbRenyuaninfo.class);
			if(list.size() != 0){
				FbRenyuaninfoService.insertAll(list);
			}
			
			
			long end = System.currentTimeMillis();
	        logger.info("personList finished, time elapsed: {} ms.",end-start);
	        Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.ok()));
	        return res;
		}else{
			Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.error("获取用户异常")));
			return res;
		}
		
	}
	
	@Async("myAsync")
	public Future<String> orgList() {
		logger.info("orgList started---");
		long start = System.currentTimeMillis();

		String resStr = hkApiService.orgList();
		JSONObject obj = JSONObject.parseObject(resStr);
		if(obj != null && "0".equals(obj.getString("code")) && "SUCCESS".equals(obj.getString("msg"))){
			JSONArray arr = obj.getJSONObject("data").getJSONArray("list");
			//删除再新增
			FbDeptService.deleteAll();
			List<FbDept> list = JSONObject.parseArray(arr.toJSONString(), FbDept.class);
			if(list.size() != 0){
				FbDeptService.insertAll(list);
			}
			
			
			long end = System.currentTimeMillis();
	        logger.info("orgList finished, time elapsed: {} ms.",end-start);
	        Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.ok()));
	        return res;
		}else{
			Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.error("获取部门异常")));
			return res;
		}
	}
	
	@Async("myAsync")
	public Future<String> acsDoorList() {
		logger.info("acsDoorList started---");
		long start = System.currentTimeMillis();

		String resStr = hkApiService.acsDoorList();
		JSONObject obj = JSONObject.parseObject(resStr);
		if(obj != null && "0".equals(obj.getString("code")) && "SUCCESS".equals(obj.getString("msg"))){
			JSONArray arr = obj.getJSONObject("data").getJSONArray("list");
			//删除再新增
			FbMenjindianService.deleteAll();
			List<FbMenjindian> list = JSONObject.parseArray(arr.toJSONString(), FbMenjindian.class);
			if(list.size() != 0){
				FbMenjindianService.insertAll(list);
			}
			
			long end = System.currentTimeMillis();
	        logger.info("acsDoorList finished, time elapsed: {} ms.",end-start);
	        Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.ok()));
	        return res;
		}else{
			Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.error("获取门禁点异常")));
			return res;
		}
	}
	
	//第一次运行项目执行该方法  删除当天
	@Async("myAsync")
	public Future<String> doorEventsDel(String startTime, String endTime) {
		logger.info("doorEvents started---");
		long start = System.currentTimeMillis();

		JSONArray arr = hkApiService.doorEvents(startTime,endTime);
		//删除当天记录
		FbDooreventsService.deleteNowDays();
		//List<FbDoorevents> list = JSONObject.parseArray(arr.toJSONString(), FbDoorevents.class);
		if(arr.size() != 0){
			//FbDooreventsService.insertAll(list);
			for(int i=0;i<arr.size();i++)
			{
				JSONObject obj=arr.getJSONObject(i);
				//首先找出该人员最新的一条记录
				if(obj.get("personId")!=null&&!obj.get("personId").equals("")) {
					FbDoorevents model = FbDooreventsService.getLastDataById(obj.getString("personId"));
					if(model!=null) {
						Date beginDate = DateUtil.changeStrToTime(model.getEventTime().replace("T", " ").replace("+08:00", ""));
						Date endDate = DateUtil.changeStrToTime(obj.getString("eventTime").replace("T", " ").replace("+08:00", ""));
						if (DateUtil.calculatetimeGapSecond(beginDate, endDate) > 5) {
							FbDoorevents event = JSONObject.parseObject(obj.toJSONString(), FbDoorevents.class);
							FbDooreventsService.save(event);
						}
					}
					else
					{
						FbDoorevents event = JSONObject.parseObject(obj.toJSONString(), FbDoorevents.class);
						FbDooreventsService.save(event);
					}

				}
			}
		}
		long end = System.currentTimeMillis();
        logger.info("doorEvents finished, time elapsed: {} ms.",end-start);
        Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.ok()));
        return res;
	}
	
	@Async("myAsync")
	public Future<String> doorEvents(String startTime, String endTime) {
		logger.info("doorEvents started---");
		long start = System.currentTimeMillis();
		
		//删除3天之前的记录
		FbDooreventsService.deleteSevenBe();
		
		JSONArray arr = hkApiService.doorEvents(startTime,endTime);
		//List<FbDoorevents> list = JSONObject.parseArray(arr.toJSONString(), FbDoorevents.class);
		if(arr.size() != 0){
			//FbDooreventsService.insertAll(list);
			for(int i=0;i<arr.size();i++)
			{
				JSONObject obj=arr.getJSONObject(i);
				//首先找出该人员最新的一条记录
				if(obj.get("personId")!=null&&!obj.get("personId").equals("")) {
					FbDoorevents model = FbDooreventsService.getLastDataById(obj.getString("personId"));
					if(model!=null) {
						Date beginDate = DateUtil.changeStrToTime(model.getEventTime().replace("T", " ").replace("+08:00", ""));
						Date endDate = DateUtil.changeStrToTime(obj.getString("eventTime").replace("T", " ").replace("+08:00", ""));
						if (DateUtil.calculatetimeGapSecond(beginDate, endDate) > 5) {
							FbDoorevents event = JSONObject.parseObject(obj.toJSONString(), FbDoorevents.class);
							FbDooreventsService.save(event);
						}
					}
					else
					{
						FbDoorevents event = JSONObject.parseObject(obj.toJSONString(), FbDoorevents.class);
						FbDooreventsService.save(event);
					}

				}
			}
		}
		long end = System.currentTimeMillis();
        logger.info("doorEvents finished, time elapsed: {} ms.",end-start);
        Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.ok()));
        return res;
	}
}
