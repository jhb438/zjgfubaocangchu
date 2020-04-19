package com.basic.zjgfbcc.thread;

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
	
	@Async("myAsync")
	public Future<String> personList() throws InterruptedException{
		logger.info("GetSpotsThread started---");
		long start = System.currentTimeMillis();

		String resStr = hkApiService.personList();
		JSONObject obj = JSONObject.parseObject(resStr);
		if(obj != null && "0".equals(obj.getString("code")) && "SUCCESS".equals(obj.getString("msg"))){
			JSONArray arr = obj.getJSONObject("data").getJSONArray("list");
			//删除用户 再插用户
			
			
			
			long end = System.currentTimeMillis();
	        logger.info("GetSpotsThread finished, time elapsed: {} ms.",end-start);
	        Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.ok()));
	        return res;
		}else{
			Future<String> res = new AsyncResult<>(JSONObject.toJSONString(R.error("获取用户异常")));
			return res;
		}
		
	}
}
