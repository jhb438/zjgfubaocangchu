package com.basic.zjgfbcc.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.exception.MyException;
import com.basic.zjgfbcc.common.utils.DateUtil;
import com.basic.zjgfbcc.common.utils.LayuiUtil;
import com.basic.zjgfbcc.thread.HkThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.entity.FbDoorevents;
import com.basic.zjgfbcc.service.FbDooreventsService;
import com.basic.zjgfbcc.common.utils.PageUtils;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.common.utils.R;


/**
 * 
 * 
 * @author 
 * @date 2020-04-19 13:17:50
 */
@Api(value = "")
@RestController
@CrossOrigin
@RequestMapping("sys/fbdoorevents")
public class FbDooreventsController {
	private static final Logger logger = LoggerFactory.getLogger(FbDooreventsController.class);
	@Autowired
	private FbDooreventsService fbDooreventsService;
	@Autowired
	HkThread HkThread;
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
		List<FbDoorevents> fbDooreventsList = fbDooreventsService.getList(query);
		int total = fbDooreventsService.getCount(query);
		PageUtils pageUtil = new PageUtils(fbDooreventsList, total, query.getLimit(), query.getPage());
		return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
	}

    /**
     * 新增
     **/
    @ApiOperation(value="")
    @ResponseBody
    @RequestMapping(value="/add",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public void add(String startTime,String endTime){
		//获取上次时间作开始
		try {
			Future<String> res = null;
			try {
				startTime= DateUtil.getS(DateUtil.changeStrToTime(startTime))+"+08:00";
				endTime= DateUtil.getS(DateUtil.changeStrToTime(endTime))+"+08:00";
				res = HkThread.doorEvents(startTime,endTime);

				JSONObject obj = JSONObject.parseObject(res.get());
				if(obj.getIntValue("code") == 0){
					logger.info("获取当天门禁进出成功");
				}else{
					throw new MyException("获取门禁进出异常");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}




		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }

	/**
	 * 修改
	 */
	@ApiOperation(value="")
    @ResponseBody
	@RequestMapping(value="/update", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	public R update(FbDoorevents fbDoorevents){
		fbDooreventsService.update(fbDoorevents);
		return R.ok();
	}

	/**
	 * 删除
	 */
    @ApiOperation(value="")
	@ResponseBody
	@RequestMapping(value="/delete",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R delete(@RequestParam(value="rowGuids[]") String[] rowGuids){
		fbDooreventsService.deleteBatch(rowGuids);
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
		FbDoorevents model = fbDooreventsService.getDetailByGuid(rowGuid);
        return R.ok().put("data",model);
    }
	
}
