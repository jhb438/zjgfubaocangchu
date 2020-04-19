package com.basic.zjgfbcc.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.service.api.HkApiService;
import com.basic.zjgfbcc.thread.HkThread;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

@RestController
@RequestMapping(value="hk")
public class HkApiController extends BaseApiController{
	
	@Autowired
	HkThread HkThread;
	
	//获取人员列表
	@RequestMapping(value="/personList",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public R personList(){
		Future<String> res = null;
		try {
			res = HkThread.personList();
			JSONObject obj = JSONObject.parseObject(res.get());
			if(obj.getIntValue("code") == 0){
				return R.ok();
			}else{
				return R.error(obj.getString("msg"));
			}
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return R.error();
	}
	

}


