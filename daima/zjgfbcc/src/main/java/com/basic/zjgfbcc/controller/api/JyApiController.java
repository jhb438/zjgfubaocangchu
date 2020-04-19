package com.basic.zjgfbcc.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.controller.BaseController;
import com.basic.zjgfbcc.service.api.JyApiService;


@CrossOrigin
@Api(value = "警翼控制器")
@RestController
@RequestMapping("sys/jy")
public class JyApiController extends BaseController{
	
	@Autowired
	public JyApiService jyApiService;
	
	/**
	 * 组织结构信息上传 81
	 * @param params
	 * @return
	 */
	@PassToken
	@ApiOperation(notes="组织结构信息上传", value = "组织结构信息上传")
	@ResponseBody
	@RequestMapping(value="/UnitinfoUp",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R UnitinfoUp(@RequestBody List<Map<String,String>> params){

		String res = jyApiService.UnitinfoUp(params);
		JSONObject obj = JSONObject.parseObject(res);
		if("1".equals(obj.getString("code"))){
			return R.ok(obj.getString("message"));
		}else{
			return R.error(obj.getString("message"));
		}
		
	}
	
	/**
	 * 警员基本信息上传 81
	 * @param params
	 * @return
	 */
	@PassToken
	@ApiOperation(notes="警员基本信息上传",value="警员基本信息上传")
	@ResponseBody
	@RequestMapping(value="/PoliceinfoUp",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R PoliceinfoUp(@RequestBody List<Map<String,String>> params){
		
		String res = jyApiService.PoliceinfoUp(params);
		JSONObject obj = JSONObject.parseObject(res);
		if("1".equals(obj.getString("code"))){
			return R.ok(obj.getString("message"));
		}else{
			return R.error(obj.getString("message"));
		}
	}
	
	/**
	 * 系统用户基本信息上传 81
	 * @param params
	 * @return
	 */
	@PassToken
	@ApiOperation(notes="系统用户基本信息上传",value="系统用户基本信息上传")
	@ResponseBody
	@RequestMapping(value="/UserinfoUp",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R UserinfoUp(@RequestBody List<Map<String,String>> params){
		
		String res = jyApiService.UserinfoUp(params);
		JSONObject obj = JSONObject.parseObject(res);
		if("1".equals(obj.getString("code"))){
			return R.ok(obj.getString("message"));
		}else{
			return R.error(obj.getString("message"));
		}
	}
	
	/**
	 * 模糊查询部门信息 8081
	 * @return
	 */
	@PassToken
	@ApiOperation(notes="模糊查询部门信息",value="模糊查询部门信息")
	@ResponseBody
	@RequestMapping(value="模糊查询部门信息",produces="application/json;charset=utf-8",method=RequestMethod.POST)
	public R departmentFuzzy(@RequestBody Map<String,String> params){
		
		JSONObject obj = jyApiService.departmentFuzzy(params);
		return R.ok().put("data", obj);
	}
   	
}
