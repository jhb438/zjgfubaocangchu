package com.basic.zjgfbcc.controller;

import java.util.Date;
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
import com.basic.zjgfbcc.entity.FbMenjindian;
import com.basic.zjgfbcc.service.FbMenjindianService;
import com.basic.zjgfbcc.common.utils.PageUtils;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.common.utils.R;


/**
 * 
 * 
 * @author 
 * @date 2020-04-19 13:17:31
 */
@Api(value = "")
@RestController
@CrossOrigin
@RequestMapping("sys/fbmenjindian")
public class FbMenjindianController {
	@Autowired
	private FbMenjindianService fbMenjindianService;
	
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
		List<FbMenjindian> fbMenjindianList = fbMenjindianService.getList(query);
		int total = fbMenjindianService.getCount(query);
		PageUtils pageUtil = new PageUtils(fbMenjindianList, total, query.getLimit(), query.getPage());
		return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
	}

    /**
     * 新增
     **/
    @ApiOperation(value="")
    @ResponseBody
    @RequestMapping(value="/add",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R add(FbMenjindian fbMenjindian){
		fbMenjindianService.save(fbMenjindian);
        return R.ok();  
    }

	/**
	 * 修改
	 */
	@ApiOperation(value="")
    @ResponseBody
	@RequestMapping(value="/update", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	public R update(FbMenjindian fbMenjindian){
		fbMenjindianService.update(fbMenjindian);
		return R.ok();
	}

	/**
	 * 删除
	 */
    @ApiOperation(value="")
	@ResponseBody
	@RequestMapping(value="/delete",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R delete(@RequestParam(value="rowGuids[]") String[] rowGuids){
		fbMenjindianService.deleteBatch(rowGuids);
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
		FbMenjindian model = fbMenjindianService.getDetailByGuid(rowGuid);
        return R.ok().put("data",model);
    }

	/**
	 * 获取所有的列表数据
	 * @return
	 */
	@PassToken
	@ApiOperation(value="通过rowGuid获取一条记录")
	@ResponseBody
	@RequestMapping(value="/getMenJinList",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R getAreaList(){
		List<Frame_CodeValue> list = fbMenjindianService.getMenJinList();
		return R.ok().put("data",list);
	}
	
}
