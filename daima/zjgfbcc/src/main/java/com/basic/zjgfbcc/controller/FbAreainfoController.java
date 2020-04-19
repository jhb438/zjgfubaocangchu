package com.basic.zjgfbcc.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.common.utils.LayuiUtil;
import com.basic.zjgfbcc.entity.Frame_CodeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.entity.FbAreainfo;
import com.basic.zjgfbcc.service.FbAreainfoService;
import com.basic.zjgfbcc.common.utils.PageUtils;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.common.utils.R;


/**
 * 
 * 
 * @author 
 * @date 2020-04-19 14:40:28
 */
@Api(value = "")
@RestController
@CrossOrigin
@RequestMapping("sys/fbareainfo")
public class FbAreainfoController {
	@Autowired
	private FbAreainfoService fbAreainfoService;
	
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
		List<FbAreainfo> fbAreainfoList = fbAreainfoService.getList(query);
		int total = fbAreainfoService.getCount(query);
		PageUtils pageUtil = new PageUtils(fbAreainfoList, total, query.getLimit(), query.getPage());
		return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
	}

    /**
     * 新增
     **/
    @ApiOperation(value="")
    @ResponseBody
    @RequestMapping(value="/add",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R add(FbAreainfo fbAreainfo){
		fbAreainfoService.save(fbAreainfo);
        return R.ok();  
    }

	/**
	 * 修改
	 */
	@ApiOperation(value="")
    @ResponseBody
	@RequestMapping(value="/update", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	public R update(FbAreainfo fbAreainfo){
		fbAreainfoService.update(fbAreainfo);
		return R.ok();
	}

	/**
	 * 删除
	 */
    @ApiOperation(value="")
	@ResponseBody
	@RequestMapping(value="/delete",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R delete(@RequestParam(value="rowGuids[]") String[] rowGuids){
		fbAreainfoService.deleteBatch(rowGuids);
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
		FbAreainfo model = fbAreainfoService.getDetailByGuid(rowGuid);
        return R.ok().put("data",model);
    }

	/**
	 * 获取所有的列表数据
	 * @return
	 */
	@PassToken
	@ApiOperation(value="通过rowGuid获取一条记录")
	@ResponseBody
	@RequestMapping(value="/getAreaList",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R getAreaList(){
		List<Frame_CodeValue> list = fbAreainfoService.getAreaList();
		return R.ok().put("data",list);
	}
	
}
