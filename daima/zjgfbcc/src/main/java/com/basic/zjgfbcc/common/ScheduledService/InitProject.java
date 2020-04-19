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

import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.exception.MyException;
import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.thread.HkThread;

@Component
public class InitProject implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
	
	@Autowired
	HkThread HkThread;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	//第一次运行 获取当天所有门禁事件
    	Future<String> res = null;
		try {
			res = HkThread.doorEventsDel(DateUtil.getY_m_d(new Date())+"T00:00:00+08:00",DateUtil.getY_m_d(new Date())+"T23:59:59+08:00");
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

