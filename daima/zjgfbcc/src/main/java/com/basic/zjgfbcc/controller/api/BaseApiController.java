package com.basic.zjgfbcc.controller.api;

import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.exception.MyException;
import com.basic.zjgfbcc.common.utils.Base64Util;
import com.basic.zjgfbcc.common.utils.HttpUtil;


@CrossOrigin
@RestController
@RequestMapping("sys/api/base")
public class BaseApiController {
	
	//定义一个全局的记录器，通过LoggerFactory获取
    public Logger logger = LoggerFactory.getLogger(BaseApiController.class);
    
    @Value(value = "${upload.file.path}")
    public String filePath;
    
    @Value(value = "${interface.DZZJUrl}")
	public String InterFace_DZZJUrl;
    
    @Value(value="${interface.Url}")
    public String InterFace_Url;
    
    @Value(value = "${interface.authKey}")
	public String interface_AuthKey;
    
    @Value(value="${interface.unitcode}")
    public String unitcode;
    
	/**
	 * 验证参数
	 * <p>Title: checkParams</p>  
	 * <p>Description: </p>
	 * @author hero  
	 * @return
	 */
	public void checkParams(Map<String, String> params,String param){
		if (params.get(param) == null || "".equals(params.get(param))) {
			throw new MyException("缺失参数"+param);
		}
	}
	
	/**
	 * 登录
	 * @return
	 * @throws Exception 
	 */
	public String login() throws Exception{
		Map map=new HashMap();
        String para=Base64Util.encryptBASE64("{\"username\": \"cs01\",  \"password\": \"123456\"}");
        map.put("para",para);
        String loginUrl=InterFace_Url+"/api/login";
        String ReturnData=new HttpUtil().sendPost(loginUrl,map);
		return ReturnData;
	}
   	
}
