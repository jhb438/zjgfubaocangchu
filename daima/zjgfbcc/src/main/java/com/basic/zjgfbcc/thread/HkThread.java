package com.basic.zjgfbcc.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

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
import com.basic.zjgfbcc.entity.FbMenjindian;
import com.basic.zjgfbcc.entity.FbRenyuaninfo;
import com.basic.zjgfbcc.service.FbDeptService;
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
			FbRenyuaninfoService.insertAll(list);
			
			
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
			FbDeptService.insertAll(list);
			
			
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
			FbMenjindianService.insertAll(list);
			
			
			long end = System.currentTimeMillis();
	        logger.info("acsDoorList finished, time elapsed: {} ms.",end-start);
	        Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.ok()));
	        return res;
		}else{
			Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.error("获取门禁点异常")));
			return res;
		}
	}
}
