package com.basic.zjgfbcc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.common.utils.*;
import com.basic.zjgfbcc.entity.Frame_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.entity.FbRenyuaninfo;
import com.basic.zjgfbcc.service.FbRenyuaninfoService;


/**
 * 
 * 
 * @author 
 * @date 2020-04-19 12:51:51
 */
@Api(value = "")
@RestController
@CrossOrigin
@RequestMapping("sys/fbrenyuaninfo")
public class FbRenyuaninfoController {
	@Autowired
	private FbRenyuaninfoService fbRenyuaninfoService;
	
	/**
	 * 列表数据
	 */
	@PassToken
	@ApiOperation(value="")
    @ResponseBody
	@RequestMapping(value="/listData",produces="application/json;charset=utf-8",method=RequestMethod.GET)
	public LayuiUtil listData(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FbRenyuaninfo> fbRenyuaninfoList = fbRenyuaninfoService.getList(query);
		int total = fbRenyuaninfoService.getCount(query);
		PageUtils pageUtil = new PageUtils(fbRenyuaninfoList, total, query.getLimit(), query.getPage());
		return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
	}

    /**
     * 新增
     **/
    @ApiOperation(value="")
    @ResponseBody
    @RequestMapping(value="/add",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R add(FbRenyuaninfo fbRenyuaninfo){
		fbRenyuaninfoService.save(fbRenyuaninfo);
        return R.ok();  
    }

	/**
	 * 修改
	 */
	@ApiOperation(value="")
    @ResponseBody
	@RequestMapping(value="/update", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	public R update(FbRenyuaninfo fbRenyuaninfo){
		fbRenyuaninfoService.update(fbRenyuaninfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
    @ApiOperation(value="")
	@ResponseBody
	@RequestMapping(value="/delete",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R delete(@RequestParam(value="rowGuids[]") String[] rowGuids){
		fbRenyuaninfoService.deleteBatch(rowGuids);
		return R.ok();
	}

    /**
     * 通过rowGuid获取一条记录
     * @param rowGuid
     * @return
     */
    @PassToken
    @ApiOperation(value="通过rowGuid获取一条记录")
    @ResponseBody
    @RequestMapping(value="/getDetailByGuid",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R getDetailByGuid(@RequestParam String rowGuid){
		FbRenyuaninfo model = fbRenyuaninfoService.getDetailByGuid(rowGuid);
        return R.ok().put("data",model);
    }

}
