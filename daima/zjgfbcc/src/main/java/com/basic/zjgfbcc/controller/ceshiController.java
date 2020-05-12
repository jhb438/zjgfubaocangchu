package com.basic.zjgfbcc.controller;


import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.customclass.RedisCacheable;
import com.basic.zjgfbcc.common.utils.LayuiUtil;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.dao.postSql.ceshiDao;
import com.basic.zjgfbcc.entity.Frame_Role;
import com.basic.zjgfbcc.entity.Frame_User;
import com.basic.zjgfbcc.service.Frame_UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api(value = "测试控制器")
@RestController
@RequestMapping("sys/ceshi")
public class ceshiController extends BaseController {

    @Autowired
    private Frame_UserService userService;
    
    @Autowired
    private ceshiDao ceshiDao;

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
    @ApiOperation(value = "获取所有正")
    @ResponseBody
    @RequestMapping(value = "/getAllUser", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getAllUserList() {
         List<Frame_User> list = userService.ceshi();
         return R.ok().put("data", list);
    }
     
     /**
      * 测试
      * @return
      */
     @PassToken
     @ApiOperation(value = "测试")
     @ResponseBody
     @RequestMapping(value = "/ceshi", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
     public void ceshi() {
    	 System.out.println("11111");
         System.out.println(JSONObject.toJSONString(ceshiDao.ceshi()));
     }
     
    
}
