package com.basic.zjgfbcc.service.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.controller.api.BaseApiController;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

/**
 * http://221.224.212.36:81 警翼接口
 * @author hero
 *
 */
@Service("HkApiService")
public class HkApiService extends BaseApiController{

	/**
	 * 请根据技术支持提供的实际的平台IP/端口和API网关中的合作方信息更换static静态块中的三个参数.
	 * [1 host]
	 * 		host格式为IP：Port，如10.0.0.1:443
	 * 		当使用https协议调用接口时，IP是平台（nginx）IP，Port是https协议的端口；
	 *     当使用http协议调用接口时，IP是artemis服务的IP，Port是artemis服务的端口（默认9016）。
	 * [2 appKey和appSecret]
	 * 		请按照技术支持提供的合作方Key和合作方Secret修改
	 * 	    appKey：合作方Key
	 * 	    appSecret：合作方Secret
	 * 调用前看清接口传入的是什么，是传入json就用doPostStringArtemis方法，是表单提交就用doPostFromArtemis方法
	 *
	 */
	static {
		ArtemisConfig.host = hkHsot; // 平台/nginx的IP和端口（https端口默认为443）
		ArtemisConfig.appKey = hkKey; // 合作方Key
		ArtemisConfig.appSecret = hkSecret;// 合作方Secret
	}
	

	/**
	 * API网关的后端服务上下文为：/artemis
	 */
	private static final String ARTEMIS_PATH = "/artemis";
	
	//分页获取人员列表
	public String personList(){
		String camerasDataApi = ARTEMIS_PATH +"/api/resource/v1/person/personList";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",camerasDataApi);
			}
		};
		Map<String, String> params = new HashMap<>();
		params.put("pageNo", "1");
		params.put("pageSize", "1000000");
		String body=JSONObject.toJSONString(params);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
		return result;
	}
	
	
}
