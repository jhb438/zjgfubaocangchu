package com.basic.zjgfbcc.controller;

import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.enumresource.InformationStatusEnum;
import com.basic.zjgfbcc.common.utils.*;
import com.basic.zjgfbcc.entity.InformationInfo;
import com.basic.zjgfbcc.entity.Information_Info_Category;
import com.basic.zjgfbcc.service.Frame_AttachService;
import com.basic.zjgfbcc.service.Frame_UserService;
import com.basic.zjgfbcc.service.InformationInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * <p>InformationInfoController</p>
 * <p>信息查询控制层</p>
 *
 * @author wzl
 * @date 2019-04-09 14:18:17
 */
@Api(value = "信息查询")
@RestController
@CrossOrigin
@RequestMapping("sys/informationinfo")
public class InformationInfoController {

    private final static Logger logger = LoggerFactory.getLogger(InformationInfoController.class);
    @Autowired
    private InformationInfoService informationInfoService;

    @Autowired
    private Frame_AttachService frameAttachService;

    @Autowired
    private com.basic.zjgfbcc.service.Information_Info_CategoryService infoCategoryService;
    @Autowired
    private Frame_UserService userService;

    /**
     * 列表数据
     */
    @PassToken
    @ApiOperation(value = "获取所有发布信息")
    @ResponseBody
    @RequestMapping(value = "/listData", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public LayuiUtil listData(@RequestParam Map<String, Object> params) {
    	
    	String[] arr = ((String) params.get("roleName")).split(",");
    	boolean flag = false;
    	for (String it : arr) {
    		if("管理员".equals(it)){
				flag = true;
				params.put("createUserGuid",params.get(""));
				break;
			}
		}
    	if(flag){
    	}else{
    		if(params.get("roleName").toString().contains("部门管理员")){
        		params.put("createUserGuid",params.get("userGuid"));
        	}
    	}
    	
        //查询列表数据
        Query query = new Query(params);
        List<InformationInfo> informationInfoList = informationInfoService.getList(query);
        int total = informationInfoService.getCount(query);
        PageUtils pageUtil = new PageUtils(informationInfoList, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }

    /**
     * 审核通过列表数据
     */
    @PassToken
    @ApiOperation(value = "获取审核通过的发布信息")
    @ResponseBody
    @RequestMapping(value = "/listData2", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public LayuiUtil listData2(@RequestParam Map<String, Object> params) {
        InformationInfo informationInfo = new InformationInfo();
        informationInfoService.getTypeName(informationInfo);
        if (params.get("categorys") != null && params.get("categorys") != "") {
            String[] arr = params.get("categorys").toString().split(",");
            params.put("categorys", arr);
        }
        Query query = new Query(params);
        List<InformationInfo> informationInfoList = informationInfoService.getList2(query);
        int total = informationInfoService.getCount2(query);
        PageUtils pageUtil = new PageUtils(informationInfoList, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());

    }

    /**
     * 新增
     **/
    @ApiOperation(value = "新增")
    @ResponseBody
    @RequestMapping(value = "/add", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R add(InformationInfo informationInfo,String roleName) {
    	
    	String[] arr = roleName.split(",");
    	//如果存在管理员权限则取管理员
    	boolean flag = false;
    	for (String it : arr) {
			if("管理员".equals(it)){
				flag = true;
				informationInfo.setStatus(InformationStatusEnum.YFB.getCode());
				break;
			}
		}
    	if(flag){
    	}else{
    		if(roleName.contains("部门管理员")){
        		informationInfo.setStatus(InformationStatusEnum.DSH.getCode());
        	}
    	}
    	
        //生成uuid作为rowguid
        if(informationInfo.getRowGuid()==null||informationInfo.getRowGuid()=="") {
            String uuid = java.util.UUID.randomUUID().toString();
            informationInfo.setRowGuid(uuid);
        }

        Date createTime = DateUtil.changeDate(new Date());
        informationInfo.setCreateTime(createTime);
        informationInfo.setSortSq(0);

        informationInfoService.save(informationInfo);

        //插入信息栏目多对多表
        Information_Info_Category inforCategory = new Information_Info_Category();

        inforCategory.setCreateTime(createTime);
        inforCategory.setInfoGuid(informationInfo.getRowGuid());

        String[] cateGorys = informationInfo.getCategoryGuid().toString().split(",");
        if (cateGorys == null || cateGorys.length == 0) {
            return R.error("发布栏目为空");
        }
        for (int i = 0; i < cateGorys.length; i++) {
            inforCategory.setRowGuid(UUID.randomUUID().toString());
            inforCategory.setCategoryGuid(cateGorys[i]);
            infoCategoryService.insert(inforCategory);
        }

        return R.ok().put("msg", "提交成功");
    }


    /**
     * 修改
     */
    @ApiOperation(value = "修改发布信息")
    @ResponseBody
    @RequestMapping(value = "/update", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public R update(InformationInfo informationInfo) {
        informationInfoService.update(informationInfo);
        //插入信息栏目多对多表
        //先删除之前的
        informationInfoService.deleteRelation(informationInfo.getRowGuid());
        Information_Info_Category inforCategory = new Information_Info_Category();

        inforCategory.setCreateTime(DateUtil.changeDate(new Date()));
        inforCategory.setInfoGuid(informationInfo.getRowGuid());

        String[] cateGorys = informationInfo.getCategoryGuid().toString().split(",");
        if (cateGorys == null || cateGorys.length == 0) {
            return R.error("发布栏目为空");
        }
        for (int i = 0; i < cateGorys.length; i++) {
            inforCategory.setRowGuid(UUID.randomUUID().toString());
            inforCategory.setCategoryGuid(cateGorys[i]);
            infoCategoryService.insert(inforCategory);
        }
        return R.ok().put("msg", "更新成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除发布信息")
    @ResponseBody
    @RequestMapping(value = "/deleteInfo", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R deleteInfo(@RequestParam(value="rowGuids[]") String[] rowGuids) {
        informationInfoService.deleteBatch(rowGuids);
        return R.ok();
    }

    /**
     * 审核通过
     */
    @ApiOperation(value = "审核信息通过")
    @ResponseBody
    @RequestMapping(value = "/auditPassInfo/{rowGuid}", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R auditPassInfo(@PathVariable("rowGuid") String rowGuid) {
        informationInfoService.auditPassInfo(rowGuid);
        return R.ok();
    }

    /**
     * 审核不通过
     */
    @ApiOperation(value = "审核信息不通过")
    @ResponseBody
    @RequestMapping(value = "/auditFailInfo/{rowGuid}", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R auditFailInfo(@PathVariable("rowGuid") String rowGuid) {
        informationInfoService.auditFailInfo(rowGuid);
        return R.ok();
    }

    /**
     * 发布多条信息
     * <p>Title: deliverInfo</p>
     * <p>Description: 信息</p>
     *
     * @param ids
     * @return
     * @author
     */
    @ApiOperation(value = "发布多条信息")
    @ResponseBody
    @RequestMapping(value = "/deliverInfo", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R deliverInfo(@RequestParam(value="ids[]") Integer[] ids) {
        informationInfoService.deliverInfoById(ids);
        return R.ok();
    }

    /**
     * 停止发布信息
     * <p>Title: stopDeliver</p>
     * <p>Description: 信息</p>
     *
     * @param ids
     * @return
     * @author
     */
    @ApiOperation(value = "停止发布信息")
    @ResponseBody
    @RequestMapping(value = "/stopDeliver", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R stopDeliver(@RequestParam(value="ids[]") Integer[] ids) {
        informationInfoService.stopDeliverById(ids);
        return R.ok();
    }

    /**
     * 根据栏目查询信息
     *
     * @return
     */
    @ApiOperation(value = "根据栏目查询信息")
    @ResponseBody
    @RequestMapping(value = "/getInfoByCateGuid/{guid}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getInfoByGuid(@PathVariable("guid") String guid) {
        List<String> list = infoCategoryService.getInfoByCateGuid(guid);
        return R.ok().put("data", list);
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
    public R getDetailByGuid(@RequestParam("rowGuid") String rowGuid){
        InformationInfo data = informationInfoService.getDetailByGuid(rowGuid);
        return R.ok().put("data",data);
    }


    @PassToken
    @ApiOperation(value = "获取所有发布信息")
    @ResponseBody
    @RequestMapping(value = "/getAnnouncements", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public LayuiUtil getAnnouncements(@RequestParam Map<String, Object> params) {
        //System.out.println(params.get("limit")+" !!! "+params.get("page"));
        //查询列表数据
        Query query = new Query(params);
        List<InformationInfo> informationInfoList = informationInfoService.getList(query);
        int total = informationInfoService.getCount(query);
        PageUtils pageUtil = new PageUtils(informationInfoList, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }


}
