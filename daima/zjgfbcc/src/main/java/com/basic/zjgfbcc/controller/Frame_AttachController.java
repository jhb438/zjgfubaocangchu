package com.basic.zjgfbcc.controller;

import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.LayuiUtil;
import com.basic.zjgfbcc.common.utils.PageUtils;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.entity.Frame_Attach;
import com.basic.zjgfbcc.service.Frame_AttachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(value = "/附件表")
@RestController
@CrossOrigin
@RequestMapping("sys/frameAttach")
public class Frame_AttachController extends BaseController{
    @Autowired
    private Frame_AttachService frameAttachService;
    /**
     * 列表数据
     */
    @PassToken
    @ApiOperation(value="附件表数据")
    @ResponseBody
    @RequestMapping(value="/listData",produces="application/json;charset=utf-8",method= RequestMethod.GET)
    public LayuiUtil listData(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        List<Frame_Attach> list = frameAttachService.selectFrameAttachList(query);
        int total = frameAttachService.getCount(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }

    /**
     * 新增
     **/
    @ApiOperation(value="附件表新增")
    @ResponseBody
    @RequestMapping(value="/add",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R add(@RequestBody Frame_Attach frame_attach){
        String uuid = UUID.randomUUID().toString();
        frame_attach.setRowGuid(uuid);
        frameAttachService.insertFrameAttach(frame_attach);
        return R.ok();
    }


    /**
     * 修改
     */
    @ApiOperation(value="附件表修改")
    @ResponseBody
    @RequestMapping(value="/update/{id}", produces = "application/json; charset=utf-8", method=RequestMethod.PUT)
    public R update(@PathVariable("id") Integer id,@RequestBody Frame_Attach frame_attach){
        frame_attach.setRowId(id);
        frameAttachService.updateFrameAttach(frame_attach);
        return R.ok();
    }
    
    /**
     * 删除
     */
    @ApiOperation(value="附件表删除")
    @ResponseBody
    @RequestMapping(value="/delete",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R delete(@RequestParam(value = "attachRowguid[]") String[] attachRowguid){
        frameAttachService.deleteFrameAttachByIds(attachRowguid);
        return R.ok();
    }
    
    @ApiOperation(value="根据关联guid获取附件列表")
    @ResponseBody
    @PassToken
    @RequestMapping(value="/getAttachList",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R getAttachList(@RequestParam(value = "guid",required = false) String guid){
    	List<Frame_Attach> attachList = frameAttachService.getAttachList(guid);
    	if (attachList!=null && attachList.size()>0) {
    		for (int i = 0; i < attachList.size(); i++) {
    			attachList.get(i).setUrl(fileUrl+"/file/"+attachList.get(i).getContentUrl());
    		}
		}
    	return R.ok().put("data", attachList);
    }

}
