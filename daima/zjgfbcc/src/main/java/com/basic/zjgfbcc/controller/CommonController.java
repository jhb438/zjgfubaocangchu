package com.basic.zjgfbcc.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.entity.FbDoorevents;
import com.basic.zjgfbcc.entity.Frame_User;
import com.basic.zjgfbcc.service.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@CrossOrigin
@Api(value = "公共控制器")
@RestController
@RequestMapping("sys/common")
public class CommonController extends BaseController {

    @Autowired
    private Frame_UserService userService;
    
    @Autowired
	private FbDooreventsService fbDooreventsService;
    

    /**
     * 获取所有正常用户
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @param params
     * @return
     * @author
     */
    @PassToken
    @ApiOperation(value = "获取所有正常用户")
    @ResponseBody
    @RequestMapping(value = "/getAllUser", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getAllUserList() {
        List<Frame_User> list = userService.getAllUserList();
        return R.ok().put("data", list);
    }
    
    
    /**
     * 统计办公区域
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @param params
     * @return
     * @author
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/StatisticsBG", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R StatisticsBG() {
    	
    	//获取今天与昨天的所有入
    	List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu("办公区域", "", "");
    	System.out.println(JSONObject.toJSONString(ruList));
    	
    	//获取额外的出
    	List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu("办公区域", "", "");
    	System.out.println(JSONObject.toJSONString(chuList));
    	
    	if(ruList.size() == 0){
    		return R.error("异常");
    	}
    	for (FbDoorevents chu : chuList) {
    		for(int i=0;i<ruList.size();i++){
    			FbDoorevents f = ruList.get(i);
    			if(chu.getPersonId().equals(f.getPersonId())){
    				ruList.remove(i);
    				continue;
    			}
    		}
		}
    	
    	System.out.println(JSONObject.toJSONString(ruList));
    	
    	
    	return R.ok();
    }
    
    /**
     * 统计办公区域承包商
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @param params
     * @return
     * @author
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/StatisticsBGCBS", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R StatisticsBGCBS() {
    	
    	//获取今天与昨天的所有入
    	List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu("办公区域", "1", "");
    	System.out.println(JSONObject.toJSONString(ruList));
    	
    	//获取额外的出
    	List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu("办公区域", "1", "");
    	System.out.println(JSONObject.toJSONString(chuList));
    	
    	if(ruList.size() == 0){
    		return R.error("异常");
    	}
    	for (FbDoorevents chu : chuList) {
    		for(int i=0;i<ruList.size();i++){
    			FbDoorevents f = ruList.get(i);
    			if(chu.getPersonId().equals(f.getPersonId())){
    				ruList.remove(i);
    				continue;
    			}
    		}
		}
    	
    	System.out.println(JSONObject.toJSONString(ruList));
    	
    	
    	return R.ok();
    }
    
    /**
     * 统计生产区域
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @param params
     * @return
     * @author
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/StatisticsSC", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R StatisticsSC() {
    	
    	//获取今天与昨天的所有入
    	List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu("生产区域（含码头）", "", "");
    	System.out.println(JSONObject.toJSONString(ruList));
    	
    	//获取额外的出
    	List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu("生产区域（含码头）", "", "");
    	System.out.println(JSONObject.toJSONString(chuList));
    	
    	if(ruList.size() == 0){
    		return R.error("异常");
    	}
    	for (FbDoorevents chu : chuList) {
    		for(int i=0;i<ruList.size();i++){
    			FbDoorevents f = ruList.get(i);
    			if(chu.getPersonId().equals(f.getPersonId())){
    				ruList.remove(i);
    				continue;
    			}
    		}
		}
    	
    	System.out.println(JSONObject.toJSONString(ruList));
    	
    	
    	return R.ok();
    }
    
    /**
     * 统计生产区域承包商
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @param params
     * @return
     * @author
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/StatisticsSCCBS", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R StatisticsSCCBS() {
    	
    	//获取今天与昨天的所有入
    	List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu("生产区域（含码头）", "1", "");
    	System.out.println(JSONObject.toJSONString(ruList));
    	
    	//获取额外的出
    	List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu("生产区域（含码头）", "1", "");
    	System.out.println(JSONObject.toJSONString(chuList));
    	
    	if(ruList.size() == 0){
    		return R.error("异常");
    	}
    	for (FbDoorevents chu : chuList) {
    		for(int i=0;i<ruList.size();i++){
    			FbDoorevents f = ruList.get(i);
    			if(chu.getPersonId().equals(f.getPersonId())){
    				ruList.remove(i);
    				continue;
    			}
    		}
		}
    	
    	System.out.println(JSONObject.toJSONString(ruList));
    	
    	
    	return R.ok();
    }
    

    
    
   
}
