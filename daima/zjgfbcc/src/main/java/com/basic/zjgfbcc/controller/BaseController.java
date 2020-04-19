package com.basic.zjgfbcc.controller;

import io.swagger.annotations.Api;

import java.util.Base64;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basic.zjgfbcc.common.exception.MyException;
import sun.misc.BASE64Encoder;


@CrossOrigin
@Api(value = "基础控制器")
@RestController
@RequestMapping("sys/base")
public class BaseController {
	
	//定义一个全局的记录器，通过LoggerFactory获取
    public Logger logger = LoggerFactory.getLogger(BaseController.class);
    
    @Value(value = "${upload.file.path}")
    public String filePath;
    
    @Value(value = "${spring.datasource.mysql.driver-class-name}")
   	public String driver;   
   	
   	@Value(value = "${spring.datasource.mysql.url}")
   	public String url;   
   	
   	@Value(value = "${spring.datasource.mysql.username}")
   	public String userName;
   	
   	@Value(value = "${spring.datasource.mysql.password}")
   	public String password;
   	
   	@Value(value = "${upload.file.url}")
   	public String fileUrl;

	@Value(value = "${configSourcePath}")
   	public String configSourcePath;

	@Value(value = "${jpush.APP_KEY}")
	public String APP_KEY;

	@Value(value = "${jpush.MASTER_SECRET}")
	public String MASTER_SECRET;

	@Value(value = "${interface.Url}")
	public String InterFace_Url;

	@Value(value = "${interface.DZZJUrl}")
	public String InterFace_DZZJUrl;

	@Value(value = "${interface.authKey}")
	public String interface_AuthKey;
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
   	
}
