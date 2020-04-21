package com.basic.zjgfbcc.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.entity.FbAreainfo;
import com.basic.zjgfbcc.entity.FbDoorevents;
import com.basic.zjgfbcc.entity.Frame_User;
import com.basic.zjgfbcc.service.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    private FbAreainfoService fbAreainfoService;
    

    /**
     * 获取所有正常用户
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
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
     * 统计区域人数
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @param areaName
     * @return
     * @author
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/StatisticsQYRS", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R StatisticsQYRS(String areaName) {
    	
    	//获取今天与昨天的所有入
    	List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "", "");
    	System.out.println(JSONObject.toJSONString(ruList));
    	
    	//获取额外的出
    	List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "", "");
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
    	
    	
    	return R.ok().put("data", ruList);
    }


    /**
     * 统计区域承包商人数
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @param areaName
     * @return
     * @author
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/StatisticsCBSRS", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R StatisticsCBSRS(String areaName) {
        //生产区域
        //获取今天与昨天的所有入
        List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "1", "");

        //获取额外的出
        List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "1", "");

        for (FbDoorevents chu : chuList) {
            for(int i=0;i<ruList.size();i++){
                FbDoorevents f = ruList.get(i);
                if(chu.getPersonId().equals(f.getPersonId())){
                    ruList.remove(i);
                    continue;
                }
            }
        }
        return R.ok().put("data", ruList);
    }

    @PassToken
    public List<FbDoorevents> getCBSList(String areaName) {
        //生产区域
        //获取今天与昨天的所有入
        List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "1", "");

        //获取额外的出
        List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "1", "");

        for (FbDoorevents chu : chuList) {
            for(int i=0;i<ruList.size();i++){
                FbDoorevents f = ruList.get(i);
                if(chu.getPersonId().equals(f.getPersonId())){
                    ruList.remove(i);
                    continue;
                }
            }
        }
        return ruList;
    }

    public List<FbDoorevents> getVTZList(String areaName) {
        //生产区域
        //获取今天与昨天的所有入
        List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "", "1");

        //获取额外的出
        List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "", "1");

        for (FbDoorevents chu : chuList) {
            for(int i=0;i<ruList.size();i++){
                FbDoorevents f = ruList.get(i);
                if(chu.getPersonId().equals(f.getPersonId())){
                    ruList.remove(i);
                    continue;
                }
            }
        }
        return ruList;
    }


    /**
     * 统计所有承包商总和
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @return
     * @author
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/StatisticsCBSRSHJ", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R StatisticsCBSRSHJ() {

        List<FbDoorevents> allList=new ArrayList<>();

        Map map=new HashMap();
        List<FbAreainfo> areaList=fbAreainfoService.getQueryList(map);
        for(FbAreainfo model: areaList)
        {
            List<FbDoorevents> list=getCBSList(model.getAreaName());
            allList.addAll(list);
        }

    	return R.ok().put("data", allList);
    }


    /**
     * 统计VTZ人数
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @return
     * @author
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/StatisticsVTZ", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R StatisticsVTZ() {

        List<FbDoorevents> allList=new ArrayList<>();

        Map map=new HashMap();
        List<FbAreainfo> areaList=fbAreainfoService.getQueryList(map);
        for(FbAreainfo model: areaList)
        {
            List<FbDoorevents> list=getVTZList(model.getAreaName());
            allList.addAll(list);
        }

        return R.ok().put("data", allList);

    }





}
