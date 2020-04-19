package com.basic.zjgfbcc.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.common.utils.LayuiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.entity.FbAreamenjinRelation;
import com.basic.zjgfbcc.service.FbAreamenjinRelationService;
import com.basic.zjgfbcc.common.utils.PageUtils;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.common.utils.R;


/**
 * 
 * 
 * @author 
 * @date 2020-04-19 14:41:28
 */
@Api(value = "")
@RestController
@CrossOrigin
@RequestMapping("sys/fbareamenjinrelation")
public class FbAreamenjinRelationController {
	@Autowired
	private FbAreamenjinRelationService fbAreamenjinRelationService;
	
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
		List<FbAreamenjinRelation> fbAreamenjinRelationList = fbAreamenjinRelationService.getList(query);
		int total = fbAreamenjinRelationService.getCount(query);
		PageUtils pageUtil = new PageUtils(fbAreamenjinRelationList, total, query.getLimit(), query.getPage());
		return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
	}

    /**
     * 新增
     **/
    @ApiOperation(value="")
    @ResponseBody
    @RequestMapping(value="/add",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R add(FbAreamenjinRelation fbAreamenjinRelation){
		fbAreamenjinRelationService.save(fbAreamenjinRelation);
        return R.ok();  
    }

	/**
	 * 修改
	 */
	@ApiOperation(value="")
    @ResponseBody
	@RequestMapping(value="/update", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	public R update(FbAreamenjinRelation fbAreamenjinRelation){
		fbAreamenjinRelationService.update(fbAreamenjinRelation);
		return R.ok();
	}

	/**
	 * 删除
	 */
    @ApiOperation(value="")
	@ResponseBody
	@RequestMapping(value="/delete",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R delete(@RequestParam(value="rowGuids[]") String[] rowGuids){
		fbAreamenjinRelationService.deleteBatch(rowGuids);
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
		FbAreamenjinRelation model = fbAreamenjinRelationService.getDetailByGuid(rowGuid);
        return R.ok().put("data",model);
    }
	
}
