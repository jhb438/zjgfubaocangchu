package com.basic.zjgfbcc.controller;

import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.LayuiUtil;
import com.basic.zjgfbcc.common.utils.PageUtils;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.entity.Frame_Config;
import com.basic.zjgfbcc.service.Frame_CodeValueService;
import com.basic.zjgfbcc.service.Frame_ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Api(value = "系统参数")
@RestController
@RequestMapping("sys/config")
public class Frame_ConfigController {
    private final static Logger logger = LoggerFactory.getLogger(Frame_ConfigController.class);

    @Autowired
    private Frame_ConfigService configService;
    
    @Autowired
    private Frame_CodeValueService codeValueService;

    /**
     * 获取所有系统参数
     * <p>Title: getConfig</p>
     * <p>Description: 系统参数类别</p>
     *
     * @param params
     * @return
     * @author wzl
     */
    @PassToken
    @ApiOperation(value = "获取所有系统参数")
    @ResponseBody
    @RequestMapping(value = "/getConfig", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public LayuiUtil getConfig(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Frame_Config> configList = configService.getConfig(query);
        int total = configService.getCount(query);
        PageUtils pageUtil = new PageUtils(configList, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }

    /**
     * 新增系统参数
     * <p>Title: insert</p>
     * <p>Description: 新增系统参数类别</p>
     *
     * @param config
     * @return
     * @author wzl
     */
    @ApiOperation(value = "新增系统参数")
    @ResponseBody
    @RequestMapping(value = "/insertConfig", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R insertConfig(Frame_Config config) {
        configService.insertConfig(config);
        return R.ok();
    }

    /**
     * 更新系统参数类别
     * <p>Title: update</p>
     * <p>Description: 更新系统参数</p>
     *

     * @param config
     * @return
     * @author wzl
     */
    @ApiOperation(value = "更新系统参数")
    @ResponseBody
    @RequestMapping(value = "/updateConfig", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R updateConfig(Frame_Config config) {
        configService.updateConfig(config);
        return R.ok();
    }

    /**
     * 删除系统参数
     * <p>Title: delete</p>
     * <p>Description: 系统参数</p>
     *
     * @return
     * @author wzl
     */
    @ApiOperation(value = "删除多个系统参数")
    @ResponseBody
    @RequestMapping(value = "/deleteConfig", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R deleteConfig(@RequestParam(value="rowGuids[]") String [] rowGuids) {
        configService.deleteConfig(rowGuids);
        return R.ok();
    }

    /**
     * 验证参数名重复性
     * @param t
     * @param <T>
     * @return
     */
    @ApiOperation(value = "验证参数名重复性")
    @ResponseBody
    @RequestMapping(value = "/checkConfigName", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public <T> R checkConfigName(@RequestBody T t) {
        int count = configService.checkConfigName(t);
        if (count >= 1) {
            return R.error("您输入的值已存在，请重新输入");
        }
        return R.ok();
    }
    

    /**
     * 获取参数值
     * <p>Title: queryReceiveOrder</p>
     * <p>Description: </p>
     * @author jianghb
     * @return
     */
    @ApiOperation(value="获取参数值")
    @ResponseBody
    @RequestMapping(value="/getParmaValue",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R getParmaValue(@RequestParam String configName){
        //获取当前配置
        Frame_Config config = configService.getConfigByName(configName);
        return R.ok().put("data", config);
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
        Frame_Config model = configService.getDetailByGuid(rowGuid);
        return R.ok().put("data",model);
    }



}
