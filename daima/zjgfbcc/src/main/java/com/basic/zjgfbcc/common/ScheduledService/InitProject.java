package com.basic.zjgfbcc.common.ScheduledService;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.alibaba.druid.filter.AutoLoad;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.exception.MyException;
import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.service.RedisService;
import com.basic.zjgfbcc.thread.HkThread;

@Component
public class InitProject implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
	
	@Autowired
	HkThread HkThread;
	
	@Autowired
	RedisService RedisService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	
    	System.out.println(DateUtil.getS(new Date()));

    	//第一次运行 获取当天至现在时间的
    	Future<String> res = null;
		try {
			//存储当前时间
			Date now = new Date();
			RedisService.set("fbcc_nowDate", DateUtil.getYmdhms(now));
			logger.info("程序开始执行，第一次时间为："+DateUtil.getYmdhms(now));
			res = HkThread.doorEventsDel(DateUtil.getY_m_d(new Date())+"T00:00:00+08:00",DateUtil.getS(now)+"+08:00");
			JSONObject obj = JSONObject.parseObject(res.get());
			if(obj.getIntValue("code") == 0){
				logger.info("获取当天门禁进出成功");
			}else{
				throw new MyException("获取门禁进出异常");
			}

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    	
    }
}

