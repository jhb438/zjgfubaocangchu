package com.basic.zjgfbcc.thread;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.utils.SpringContextUtils;


/**
 * 开启线程
* <p>Title: InsertOaUsersThread</p>  
* <p>Description: </p>  
* @author hero
 */
public class DxThread extends Thread{
	Map<String, String> params;
	
	private static final Logger logger = LoggerFactory.getLogger(DxThread.class);

	
	public DxThread(Map<String, String> params) {
		this.params = params;
	}

	public void run(){
		logger.info("短信 task start ------");
        //dxApiService.SendEx(params);
        JSONObject obj = new JSONObject();
		logger.info("短信 task end ------");
	}
}
