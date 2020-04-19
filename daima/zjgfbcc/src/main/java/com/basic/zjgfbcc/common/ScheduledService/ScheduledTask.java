package com.basic.zjgfbcc.common.ScheduledService;

import com.basic.zjgfbcc.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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




//   @Scheduled(cron = "*/30  *  *  *  *  ?")//代表每30秒执行一次
//    @Scheduled(cron = "0 0 23 * * ?")   //每天23点执行
//    @Scheduled(cron = "*/10  *  *  *  *  ?")
    public void getDeviceLoaction() {
        try {
            
        	System.out.println("xxxxx");
        	
        	
        	

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }





}
