package com.basic.zjgfbcc.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.PassToken;
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
	@PassToken
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
	
	
	//获取部门列表
	@PassToken
	@RequestMapping(value="/orgList",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public R orgList(){
		Future<String> res = null;
		try {
			res = HkThread.orgList();
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
	
	
	//获取门禁点列表
	@PassToken
	@RequestMapping(value="/acsDoorList",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public R acsDoorList(){
		Future<String> res = null;
		try {
			res = HkThread.acsDoorList();
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
	
	
	@PassToken
	@RequestMapping(value="/doorEvents",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	@ResponseBody
	public R doorEvents(@RequestBody Map<String,String> params){
		
		Future<String> res = null;
		try {
			res = HkThread.doorEvents(params.get("startTime"),params.get("endTime"));
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



