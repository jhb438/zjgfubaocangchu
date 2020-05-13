package com.basic.zjgfbcc.common.ScheduledService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.common.utils.StringUtil;
import com.basic.zjgfbcc.dao.postSql.hikKuDao;
import com.basic.zjgfbcc.entity.FbDoorevents;
import com.basic.zjgfbcc.service.FbDooreventsService;
import com.basic.zjgfbcc.service.RedisService;
import com.basic.zjgfbcc.thread.HkThread;

@Component
public class InitProject implements ApplicationRunner {
//	private static Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
	
	@Autowired
	HkThread HkThread;
	
	@Autowired
	RedisService RedisService;
	
	@Autowired
	hikKuDao hik;
	
	@Autowired
	FbDooreventsService FbDooreventsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	
//    	System.out.println(DateUtil.getS(new Date()));
//
//    	//第一次运行 获取当天至现在时间的
//    	Future<String> res = null;
//		try {
//			//存储当前时间
//			Date now = new Date();
//			RedisService.set("fbcc_nowDate", DateUtil.getYmdhms(now));
//			logger.info("程序开始执行，第一次时间为："+DateUtil.getYmdhms(now));
//			res = HkThread.doorEventsDel(DateUtil.getY_m_d(new Date())+"T00:00:00+08:00",DateUtil.getS(now)+"+08:00");
//			JSONObject obj = JSONObject.parseObject(res.get());
//			if(obj.getIntValue("code") == 0){
//				logger.info("获取当天门禁进出成功");
//			}else{
//				throw new MyException("获取门禁进出异常");
//			}
//			
//		} catch (InterruptedException | ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    	
    	//第一次运行 获取昨天到至今的所有数据（success数据）
    	//删除昨天到现在的数据
    	RedisService.set("timeFlag", "false");
    	FbDooreventsService.deleteByTowDayBefore();
    	
    	List<FbDoorevents> list = hik.getEvents();
    	if(list.size() > 0){
    		FbDoorevents model = null;
    		FbDoorevents obj = null;
    		Date beginDate = null;
    		Date endDate = null;
    		for(int i=0;i<list.size();i++){
    			obj =list.get(i);
				//首先找出该人员最新的一条记录
				if(obj.getPersonId()!=null&&!obj.getPersonId().equals("")) {
					model = FbDooreventsService.getLastDataById(obj.getPersonId());
					if(model!=null && !StringUtil.isNullOrEmpty(model.getEventTime()) && !StringUtil.isNullOrEmpty(obj.getEventTime())) {
						beginDate = DateUtil.changeStrToTime(model.getEventTime());
						endDate = DateUtil.changeStrToTime(obj.getEventTime());
						if (DateUtil.calculatetimeGapSecond(beginDate, endDate) > 5) {
							FbDooreventsService.save(obj);
						}
					}
					else
					{
						FbDooreventsService.save(obj);
					}

				}
    			
    		}
    		RedisService.set("timeFlag", "true");
    	}
    	 
    }
}

