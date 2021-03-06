package com.basic.zjgfbcc.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.*;
import com.basic.zjgfbcc.entity.FbAreainfo;
import com.basic.zjgfbcc.entity.FbDoorevents;
import com.basic.zjgfbcc.entity.Frame_User;
import com.basic.zjgfbcc.service.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
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
        List<FbDoorevents> ruList=getQYRS(areaName,"","");
    	return R.ok().put("data", ruList);
    }

    /**
     * 获取区域人数
     * @param areaName
     * @return
     */
    public List<FbDoorevents> getQYRS(String areaName,String personName,String orgName)
    {
        List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "", "","","",personName,orgName);
        System.out.println(JSONObject.toJSONString(ruList));

        //获取额外的出
        List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "", "","","",personName,orgName);
        System.out.println(JSONObject.toJSONString(chuList));


        for (FbDoorevents chu : chuList) {
            for(int i=0;i<ruList.size();i++){
                FbDoorevents f = ruList.get(i);
                if(chu.getPersonId().equals(f.getPersonId())){
                    ruList.remove(i);
                    break;
                }
            }
        }

        //因为入里面有重复的，所以还得去重
        List<FbDoorevents> realRuList=new ArrayList<FbDoorevents>();
        for(FbDoorevents ru:ruList)
        {
            if(realRuList.size()==0)
            {
                realRuList.add(ru);
            }
            else
            {
                int num=0;
                for(int i=0;i<realRuList.size();i++)
                {

                    FbDoorevents f = realRuList.get(i);
                    if(ru.getPersonId().equals(f.getPersonId()))
                    {
                        break;
                    }
                    else
                    {
                        if((!ru.getPersonId().equals(f.getPersonId()))&&(num==realRuList.size()-1)) {
                            realRuList.add(ru);
                            break;
                        }
                    }
                   num=num+1;
                }
            }
        }
        return realRuList;
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
        List<FbDoorevents> ruList=getCBSList(areaName,"","");
        return R.ok().put("data", ruList);
    }

    /**
     * 统计区域卡车司机人数
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @param areaName
     * @return
     * @author
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/StatisticsKCSJZRS", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R StatisticsKCSJZRS(String areaName) {
        List<FbDoorevents> ruList=getKCSJList(areaName,"","");
        return R.ok().put("data", ruList);
    }


    public List<FbDoorevents> getCBSList(String areaName,String personName,String orgName) {
        //生产区域
        //获取今天与昨天的所有入
        List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "1", "","","",personName,orgName);

        //获取额外的出
        List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "1", "","","",personName,orgName);

        for (FbDoorevents chu : chuList) {
            for(int i=0;i<ruList.size();i++){
                FbDoorevents f = ruList.get(i);
                if(chu.getPersonId().equals(f.getPersonId())){
                    ruList.remove(i);
                    break;
                }
            }
        }

        //因为入里面有重复的，所以还得去重
        List<FbDoorevents> realRuList=new ArrayList<FbDoorevents>();
        for(FbDoorevents ru:ruList)
        {
            if(realRuList.size()==0)
            {
                realRuList.add(ru);
            }
            else
            {
                int num=0;
                for(int i=0;i<realRuList.size();i++)
                {

                    FbDoorevents f = realRuList.get(i);
                    if(ru.getPersonId().equals(f.getPersonId()))
                    {
                        break;
                    }
                    else
                    {
                        if((!ru.getPersonId().equals(f.getPersonId()))&&(num==realRuList.size()-1)) {
                            realRuList.add(ru);
                            break;
                        }
                    }
                    num=num+1;
                }
            }
        }

        //有这样一种情况，由于刷卡原因，导致两个区域都有同一个人，其实这个人只在一个区域中

        List<FbDoorevents> areaRuList=new ArrayList<FbDoorevents>();
        if(areaName.equals("办公区域"))
        {
            areaRuList=getAreaCBSList("生产区域",personName,orgName);
        }
        else
        {
            areaRuList=getAreaCBSList("办公区域",personName,orgName);
        }

        for (int j=0;j<realRuList.size();j++) {
            FbDoorevents real = realRuList.get(j);
            for(int i=0;i<areaRuList.size();i++){
                FbDoorevents f = areaRuList.get(i);
                if(real.getPersonId().equals(f.getPersonId())){
                    if(DateUtil.changeStrToTime(real.getEventTime()).getTime()<DateUtil.changeStrToTime(f.getEventTime()).getTime()) {
                        realRuList.remove(j);
                        break;
                    }
                }
            }
        }
        return realRuList;
    }

    public List<FbDoorevents> getAreaCBSList(String areaName,String personName,String orgName) {
        //生产区域
        //获取今天与昨天的所有入
        List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "1", "","","",personName,orgName);

        //获取额外的出
        List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "1", "","","",personName,orgName);

        for (FbDoorevents chu : chuList) {
            for(int i=0;i<ruList.size();i++){
                FbDoorevents f = ruList.get(i);
                if(chu.getPersonId().equals(f.getPersonId())){
                    ruList.remove(i);
                    break;
                }
            }
        }

        //因为入里面有重复的，所以还得去重
        List<FbDoorevents> realRuList=new ArrayList<FbDoorevents>();
        for(FbDoorevents ru:ruList)
        {
            if(realRuList.size()==0)
            {
                realRuList.add(ru);
            }
            else
            {
                int num=0;
                for(int i=0;i<realRuList.size();i++)
                {

                    FbDoorevents f = realRuList.get(i);
                    if(ru.getPersonId().equals(f.getPersonId()))
                    {
                        break;
                    }
                    else
                    {
                        if((!ru.getPersonId().equals(f.getPersonId()))&&(num==realRuList.size()-1)) {
                            realRuList.add(ru);
                            break;
                        }
                    }
                    num=num+1;
                }
            }
        }
        return realRuList;
    }

    public List<FbDoorevents> getKCSJList(String areaName,String personName,String orgName) {
        //生产区域
        //获取今天与昨天的所有入
        List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "", "","1","",personName,orgName);

        //获取额外的出
        List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "", "","1","",personName,orgName);

        for (FbDoorevents chu : chuList) {
            for(int i=0;i<ruList.size();i++){
                FbDoorevents f = ruList.get(i);
                if(chu.getPersonId().equals(f.getPersonId())){
                    ruList.remove(i);
                    break;
                }
            }
        }


        //因为入里面有重复的，所以还得去重
        List<FbDoorevents> realRuList=new ArrayList<FbDoorevents>();
        for(FbDoorevents ru:ruList)
        {
            if(realRuList.size()==0)
            {
                realRuList.add(ru);
            }
            else
            {
                int num=0;
                for(int i=0;i<realRuList.size();i++)
                {

                    FbDoorevents f = realRuList.get(i);
                    if(ru.getPersonId().equals(f.getPersonId()))
                    {
                        break;
                    }
                    else
                    {
                        if((!ru.getPersonId().equals(f.getPersonId()))&&(num==realRuList.size()-1)) {
                            realRuList.add(ru);
                            break;
                        }
                    }
                    num=num+1;
                }
            }
        }
        return realRuList;
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
            List<FbDoorevents> list=getCBSList(model.getAreaName(),"","");
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
    public R StatisticsVTZ(String areaName) {

        List<FbDoorevents> list=getVTZList(areaName,"","");
        return R.ok().put("data", list);

    }

    public List<FbDoorevents> getVTZList(String areaName,String personName,String orgName) {
        //生产区域
        //获取今天与昨天的所有入
        List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "", "1","","",personName,orgName);

        //获取额外的出
        List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "", "1","","",personName,orgName);

        for (FbDoorevents chu : chuList) {
            for(int i=0;i<ruList.size();i++){
                FbDoorevents f = ruList.get(i);
                if(chu.getPersonId().equals(f.getPersonId())){
                    ruList.remove(i);
                    break;
                }
            }
        }

        //因为入里面有重复的，所以还得去重
        List<FbDoorevents> realRuList=new ArrayList<FbDoorevents>();
        for(FbDoorevents ru:ruList)
        {
            if(realRuList.size()==0)
            {
                realRuList.add(ru);
            }
            else
            {
                int num=0;
                for(int i=0;i<realRuList.size();i++)
                {

                    FbDoorevents f = realRuList.get(i);
                    if(ru.getPersonId().equals(f.getPersonId()))
                    {
                        break;
                    }
                    else
                    {
                        if((!ru.getPersonId().equals(f.getPersonId()))&&(num==realRuList.size()-1)) {
                            realRuList.add(ru);
                            break;
                        }
                    }
                    num=num+1;
                }
            }
        }

        //有这样一种情况，由于刷卡原因，导致两个区域都有同一个人，其实这个人只在一个区域中

        List<FbDoorevents> areaRuList=new ArrayList<FbDoorevents>();
        if(areaName.equals("办公区域"))
        {
            areaRuList=getAreaVTZList("生产区域",personName,orgName);
        }
        else
        {
            areaRuList=getAreaVTZList("办公区域",personName,orgName);
        }

        for (int j=0;j<realRuList.size();j++) {
            FbDoorevents real = realRuList.get(j);
            for(int i=0;i<areaRuList.size();i++){
                FbDoorevents f = areaRuList.get(i);
                if(real.getPersonId().equals(f.getPersonId())){
                    if(DateUtil.changeStrToTime(real.getEventTime()).getTime()<DateUtil.changeStrToTime(f.getEventTime()).getTime()) {
                        realRuList.remove(j);
                        break;
                    }
                }
            }
        }

        return realRuList;
    }

    public List<FbDoorevents> getAreaVTZList(String areaName,String personName,String orgName) {
        //生产区域
        //获取今天与昨天的所有入
        List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "", "1","","",personName,orgName);

        //获取额外的出
        List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "", "1","","",personName,orgName);

        for (FbDoorevents chu : chuList) {
            for(int i=0;i<ruList.size();i++){
                FbDoorevents f = ruList.get(i);
                if(chu.getPersonId().equals(f.getPersonId())){
                    ruList.remove(i);
                    break;
                }
            }
        }

        //因为入里面有重复的，所以还得去重
        List<FbDoorevents> realRuList=new ArrayList<FbDoorevents>();
        for(FbDoorevents ru:ruList)
        {
            if(realRuList.size()==0)
            {
                realRuList.add(ru);
            }
            else
            {
                int num=0;
                for(int i=0;i<realRuList.size();i++)
                {

                    FbDoorevents f = realRuList.get(i);
                    if(ru.getPersonId().equals(f.getPersonId()))
                    {
                        break;
                    }
                    else
                    {
                        if((!ru.getPersonId().equals(f.getPersonId()))&&(num==realRuList.size()-1)) {
                            realRuList.add(ru);
                            break;
                        }
                    }
                    num=num+1;
                }
            }
        }
        return realRuList;
    }

    /**
     * 统计访客人数
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @return
     * @author
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/StatisticsFKZRS", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R StatisticsFKZRS(String areaName)
    {
        List<FbDoorevents> list=getFKList(areaName,"","");
        return R.ok().put("data", list);
    }

    public List<FbDoorevents> getFKList(String areaName,String personName,String orgName) {
        //生产区域
        //获取今天与昨天的所有入
        List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "", "","","1",personName,orgName);

        //获取额外的出
        List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "", "","","1",personName,orgName);

        for (FbDoorevents chu : chuList) {
            for(int i=0;i<ruList.size();i++){
                FbDoorevents f = ruList.get(i);
                if(chu.getPersonId().equals(f.getPersonId())){
                    ruList.remove(i);
                    break;
                }
            }
        }

        //因为入里面有重复的，所以还得去重
        List<FbDoorevents> realRuList=new ArrayList<FbDoorevents>();
        for(FbDoorevents ru:ruList)
        {
            if(realRuList.size()==0)
            {
                realRuList.add(ru);
            }
            else
            {
                int num=0;
                for(int i=0;i<realRuList.size();i++)
                {

                    FbDoorevents f = realRuList.get(i);
                    if(ru.getPersonId().equals(f.getPersonId()))
                    {
                        break;
                    }
                    else
                    {
                        if((!ru.getPersonId().equals(f.getPersonId()))&&(num==realRuList.size()-1)) {
                            realRuList.add(ru);
                            break;
                        }
                    }
                    num=num+1;
                }
            }
        }

        //有这样一种情况，由于刷卡原因，导致两个区域都有同一个人，其实这个人只在一个区域中

        List<FbDoorevents> areaRuList=new ArrayList<FbDoorevents>();
        if(areaName.equals("办公区域"))
        {
            areaRuList=getAreaFKList("生产区域",personName,orgName);
        }
        else
        {
            areaRuList=getAreaFKList("办公区域",personName,orgName);
        }

        for (int j=0;j<realRuList.size();j++) {
            FbDoorevents real = realRuList.get(j);
            for(int i=0;i<areaRuList.size();i++){
                FbDoorevents f = areaRuList.get(i);
                if(real.getPersonId().equals(f.getPersonId())){
                    if(DateUtil.changeStrToTime(real.getEventTime()).getTime()<DateUtil.changeStrToTime(f.getEventTime()).getTime()) {
                        realRuList.remove(j);
                        break;
                    }
                }
            }
        }
        return realRuList;
    }

    public List<FbDoorevents> getAreaFKList(String areaName,String personName,String orgName) {
        //生产区域
        //获取今天与昨天的所有入
        List<FbDoorevents> ruList = fbDooreventsService.getEventListByRu(areaName, "", "","","1",personName,orgName);

        //获取额外的出
        List<FbDoorevents> chuList = fbDooreventsService.getEventListByShiJiChu(areaName, "", "","","1",personName,orgName);

        for (FbDoorevents chu : chuList) {
            for(int i=0;i<ruList.size();i++){
                FbDoorevents f = ruList.get(i);
                if(chu.getPersonId().equals(f.getPersonId())){
                    ruList.remove(i);
                    break;
                }
            }
        }

        //因为入里面有重复的，所以还得去重
        List<FbDoorevents> realRuList=new ArrayList<FbDoorevents>();
        for(FbDoorevents ru:ruList)
        {
            if(realRuList.size()==0)
            {
                realRuList.add(ru);
            }
            else
            {
                int num=0;
                for(int i=0;i<realRuList.size();i++)
                {

                    FbDoorevents f = realRuList.get(i);
                    if(ru.getPersonId().equals(f.getPersonId()))
                    {
                        break;
                    }
                    else
                    {
                        if((!ru.getPersonId().equals(f.getPersonId()))&&(num==realRuList.size()-1)) {
                            realRuList.add(ru);
                            break;
                        }
                    }
                    num=num+1;
                }
            }
        }
        return realRuList;
    }


    /**
     * 统计总人数
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @return
     * @author
     */
    @PassToken
    @ResponseBody
    @RequestMapping(value = "/StatisticsTotalRS", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R StatisticsTotalRS(String areaName)
    {
        List<FbDoorevents> allList=new ArrayList<>();

        Map map=new HashMap();
        List<FbAreainfo> areaList=fbAreainfoService.getQueryList(map);
        //获取所有的VTZ
        for(FbAreainfo model: areaList)
        {
            List<FbDoorevents> list=getVTZList(model.getAreaName(),"","");
            allList.addAll(list);
        }
         //获取所有的承包商
        for(FbAreainfo model: areaList)
        {
            List<FbDoorevents> list=getCBSList(model.getAreaName(),"","");
            allList.addAll(list);
        }
        //获取所有的访客
        for(FbAreainfo model: areaList)
        {
            List<FbDoorevents> list=getFKList(model.getAreaName(),"","");
            allList.addAll(list);
        }

        List<FbDoorevents> list=getKCSJList(areaName,"","");
        allList.addAll(list);
        return R.ok().put("data", allList);
    }

    /**
     * 列表数据
     */
    @PassToken
    @ApiOperation(value="")
    @ResponseBody
    @RequestMapping(value="/listData",produces="application/json;charset=utf-8",method=RequestMethod.GET)
    public R listData(@RequestParam Map<String, Object> params){
        //查询列表数据
        String type=params.get("type").toString();
        String areaName=params.get("areaName").toString();
        String personName=params.get("personName").toString();
        String orgName=params.get("orgName").toString();
        List<FbDoorevents> ruList = new ArrayList<>();
        if(type.equals("qy_VTZ"))
        {
            ruList=getVTZList(areaName,personName,orgName);
        }
        else if(type.equals("qy_cbs"))
        {
            ruList=getCBSList(areaName,personName,orgName);
        }
        else if(type.equals("kcsjrs"))
        {
            ruList=getKCSJList(areaName,personName,orgName);
        }
        else if(type.equals("zrs"))
        {
            Map map=new HashMap();
            List<FbAreainfo> areaList=fbAreainfoService.getQueryList(map);
            //获取所有的VTZ
            for(FbAreainfo model: areaList)
            {
                List<FbDoorevents> list=getVTZList(model.getAreaName(),personName,orgName);
                ruList.addAll(list);
            }
            //获取所有的承包商
            for(FbAreainfo model: areaList)
            {
                List<FbDoorevents> list=getCBSList(model.getAreaName(),personName,orgName);
                ruList.addAll(list);
            }
            //获取所有的访客
            for(FbAreainfo model: areaList)
            {
                List<FbDoorevents> list=getFKList(model.getAreaName(),personName,orgName);
                ruList.addAll(list);
            }

            List<FbDoorevents> list=getKCSJList(areaName,personName,orgName);
            ruList.addAll(list);
        }
        else
        {
            ruList=getFKList(areaName,personName,orgName);
        }



        return R.ok().put("data", ruList);
    }


    /**
     * excel导出
     *
     * @param @param request
     * @param @param response    设定文件
     * @return void    返回类型
     * @throws
     * @Title: exportExcel
     * @Description: excel导出
     */
    @PassToken
    @ApiOperation(value = "人员信息导出Excel")
    @RequestMapping(value = "/userInfoToExcel")
    public void userInfoToExcel(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        try {

            String type=params.get("type").toString();
            String areaName=params.get("areaName").toString();
            String personName=params.get("personName").toString();
            String orgName=params.get("orgName").toString();
            List<FbDoorevents> ruList = new ArrayList<>();
            if(type.equals("qy_VTZ"))
            {
                ruList=getVTZList(areaName,personName,orgName);
            }
            else if(type.equals("qy_cbs"))
            {
                ruList=getCBSList(areaName,personName,orgName);
            }
            else if(type.equals("kcsjrs"))
            {
                ruList=getKCSJList(areaName,personName,orgName);
            }
            else if(type.equals("zrs"))
            {
                Map map=new HashMap();
                List<FbAreainfo> areaList=fbAreainfoService.getQueryList(map);
                //获取所有的VTZ
                for(FbAreainfo model: areaList)
                {
                    List<FbDoorevents> list=getVTZList(model.getAreaName(),personName,orgName);
                    ruList.addAll(list);
                }
                //获取所有的承包商
                for(FbAreainfo model: areaList)
                {
                    List<FbDoorevents> list=getCBSList(model.getAreaName(),personName,orgName);
                    ruList.addAll(list);
                }
                //获取所有的访客
                for(FbAreainfo model: areaList)
                {
                    List<FbDoorevents> list=getFKList(model.getAreaName(),personName,orgName);
                    ruList.addAll(list);
                }

                List<FbDoorevents> list=getKCSJList(areaName,personName,orgName);
                ruList.addAll(list);
            }
            else
            {
                ruList=getFKList(areaName,personName,orgName);
            }



            Map<String, Object> map1 = new HashMap<>();
            map1.put("data", ruList);
            ExcelUtil.exportExcel("人员信息表", "人员信息", map1, response, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
