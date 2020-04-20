package com.basic.zjgfbcc.common.ScheduledService;

import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.exception.MyException;
import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.controller.BaseController;
import com.basic.zjgfbcc.service.RedisService;
import com.basic.zjgfbcc.thread.HkThread;

import io.swagger.annotations.ApiOperation;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * @ClassName: ScheduledTask
 * @Description: 定时任务
 *
 */
@Component
public class ScheduledTask extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
	HkThread HkThread;
    
    @Autowired
    RedisService redisService;
    

//   @Scheduled(cron = "*/30  *  *  *  *  ?")//代表每30秒执行一次
//    @Scheduled(cron = "0 0 23 * * ?")   //每天23点执行
//    @Scheduled(cron = "*/10  *  *  *  *  ?")
    public void getDeviceLoaction() {
    	String lastTime = (String) redisService.get("fbcc_nowDate");
    	if(lastTime == null){
    		throw new MyException("获取上次时间异常");
    	}
    	//获取上次时间作开始
        try {
        	Future<String> res = null;
    		try {
    			Date time = DateUtil.timeCalculation(DateUtil.changeStrToTime(lastTime),Calendar.SECOND,+10);
    			redisService.set("fbcc_nowDate", DateUtil.getYmdhms(time));
    			res = HkThread.doorEvents(DateUtil.getS(DateUtil.changeStrToTime(lastTime))+"+08:00",DateUtil.getS(time)+"+08:00");
    			JSONObject obj = JSONObject.parseObject(res.get());
    			if(obj.getIntValue("code") == 0){
    				logger.info("获取当天门禁进出成功");
    			}else{
    				throw new MyException("获取门禁进出异常");
    			}
    			
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        	
        	

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }





}
