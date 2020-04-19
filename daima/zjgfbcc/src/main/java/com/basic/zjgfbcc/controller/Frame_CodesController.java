package com.basic.zjgfbcc.controller;

import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.LayuiUtil;
import com.basic.zjgfbcc.common.utils.PageUtils;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.entity.Frame_Codes;
import com.basic.zjgfbcc.service.Frame_CodesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Api(value = "代码项参数")
@RestController
@RequestMapping("sys/codes")
public class Frame_CodesController {
    private final static Logger logger = LoggerFactory.getLogger(Frame_CodesController.class);

    @Autowired
    private Frame_CodesService codesService;

    /**
     * 获取所有代码项
     * <p>Title: getCodes</p>
     * <p>Description: 代码项参数类别</p>
     * @author wzl
     * @param params
     * @return
     */
    @PassToken
    @ApiOperation(value="获取所有代码项")
    @ResponseBody
    @RequestMapping(value="/getCodes",produces="application/json;charset=utf-8",method= RequestMethod.GET)
    public LayuiUtil getCodes(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Frame_Codes> codesList = codesService.getCodes(query);
        int total = codesService.getCount(query);
        PageUtils pageUtil = new PageUtils(codesList, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }

    /**
     * 新增代码项
     * <p>Title: insertCodes</p>
     * <p>Description: 新增代码项</p>
     * @author wzl
     * @param codes
     * @return
     */
    @ApiOperation(value="新增代码项")
    @ResponseBody
    @RequestMapping(value="/insertCodes",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R insertCodes(Frame_Codes codes){
        codesService.insertCodes(codes);
        return R.ok();
    }



    /**
     * 删除代码项
     * <p>Title: deleteCodes</p>
     * <p>Description: 批量删除代码项</p>
     * @author wzl
     * @param rowGuids
     * @return
     */
    @ApiOperation(value="删除多个代码项参数")
    @ResponseBody
    @RequestMapping(value="/deleteCodes",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R deleteCodes(@RequestParam(value="rowGuids[]") String [] rowGuids){
        codesService.deleteCodes(rowGuids);
        return R.ok();
    }

    /**
     * 获取所有代码项类别
     * <p>Title: getAllRoles</p>
     * @return
     */
    @ApiOperation(value = "获取所有代码项参数")
    @ResponseBody
    @RequestMapping(value = "/getAllCodes", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getAllCodes(){
        List<Frame_Codes> list = codesService.getAllCodes();
        return R.ok().put("data",list);
    }

    /**
     * 更新代码项
     * <p>Title: updateCodes</p>
     * <p>Description: 更新代码项</p>
     * @author wzl
     *
     * @param frameCodes
     * @return
     */
    @ApiOperation(value="更新单个代码项参数")
    @ResponseBody
    @RequestMapping(value="/updateCodes",produces="application/json;charset=utf-8",method= RequestMethod.POST)
    public R updateCodes(Frame_Codes frameCodes){
        codesService.updateCodes(frameCodes);
        return R.ok();
    }

    /**
     * 验证代码名重复性
     * @param t
     * @param <T>
     * @return
     */
    @ApiOperation(value = "验证代码名重复性")
    @ResponseBody
    @RequestMapping(value = "/checkCodeName", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public <T> R checkCodeName(@RequestBody T t) {
        int count = codesService.checkCodeName(t);
        if (count >= 1) {
            return R.error("您输入的值已存在，请重新输入");
        }
        return R.ok();
    }

    /**
     * 根据代码项名称获取所有选项
     * <p>Title: getAllRoles</p>
     * @return
     */
    @ApiOperation(value = "根据代码项名称获取所有选项")
    @ResponseBody
    @RequestMapping(value = "/getCodeTreeItem", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R getCodeTreeItem(){
        List<Frame_Codes> list = codesService.getAllCodes();
        return R.ok().put("data",list);
    }

    /**
     * 保存列表数据
     * <p>Title: deleteDept</p>
     * <p>Description: 模块</p>
     * @author hero

     * @return
     */
    @ApiOperation(value="保存列表数据")
    @ResponseBody
    @RequestMapping(value="/saveRows",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R saveRows(@RequestParam(value="arrRowGuids[]") String [] arrRowGuids,@RequestParam(value="arrCodeNames[]") String [] arrCodeNames,
                     @RequestParam(value="arrSortSqs[]") String [] arrSortSqs){
        Frame_Codes model=new Frame_Codes();
        for(int i=0;i<arrRowGuids.length;i++) {
            model.setRowGuid(arrRowGuids[i]);
            model.setSortSq(Integer.parseInt(arrSortSqs[i].toString()));
            model.setCodeName(arrCodeNames[i]);
            codesService.updateCodes(model);
        }
        return R.ok();
    }


}
