package com.basic.zjgfbcc.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.common.utils.R;
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
//    	
//    	List<Map<String,String>> mapList = fbDooreventsService.Statistics("办公区域","入");
//    	JSONArray arr = new JSONArray();
//    	
//    	if(mapList == null || mapList.size() == 0){
//    		return R.error("获取失败");
//    	}
//    	
//    	for (Map<String, String> item : mapList) {
//    		arr.add(item);
//		}
//    	
//    	List<Map<String,String>> list = fbDooreventsService.Statistics("办公区域","出");
//    	
//    	if(list == null){
//    		return R.error("获取失败");
//    	}else if(list.size() == 0){
//    		return R.ok().put("data", arr);
//    	}
//    	
//    	for (Map<String, String> item : list) {
//    		for(int i=0;i<arr.size();i++){
//    			JSONObject obj = arr.getJSONObject(i);
//    			if(item.get("userName").equals(obj.getString("userName"))){
//    				//出时间必须大于入时间
//    				DateUtil.compare_dateTime(item.get("eventTime"), obj.getString("eventTime"), 2);
//    				
//    				
//					arr.remove(i);
//    			}
//    		}
//		}
////    	
////    	JSONObject obj = new JSONObject();
////    	obj.put("total", arr.size());
////    	obj.put("list", arr);
    	
    	return R.ok();
    }
    
    
   
}
