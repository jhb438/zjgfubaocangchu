package com.basic.zjgfbcc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.LayuiUtil;
import com.basic.zjgfbcc.common.utils.PageUtils;
import com.basic.zjgfbcc.common.utils.Query;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.entity.Frame_CodeValue;
import com.basic.zjgfbcc.entity.Frame_Codes;
import com.basic.zjgfbcc.entity.TreeNode;
import com.basic.zjgfbcc.service.Frame_CodeValueService;
import com.basic.zjgfbcc.service.Frame_CodesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@Api(value = "代码值参数")
@RestController
@RequestMapping("sys/codeValue")
public class Frame_CodeValueController {
    private final static Logger logger = LoggerFactory.getLogger(Frame_CodeValueController.class);

    @Autowired
    private Frame_CodeValueService codeValueService;

    @Autowired
    private Frame_CodesService codesService;

    private Frame_CodeValue frame_codeValue;

    /**
     * 获取所有代码值
     * <p>Title: getCodeValue</p>
     * <p>Description: 代码值参数类别</p>
     *
     * @param params
     * @return
     * @author wzl
     */
    @PassToken
    @ApiOperation(value = "获取所有代码值")
    @ResponseBody
    @RequestMapping(value = "/getCodeValue", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public LayuiUtil getCodeValue(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Frame_CodeValue> codeValueVList = codeValueService.getCodeValue(query);
        int total = codeValueService.getCount(query);
        PageUtils pageUtil = new PageUtils(codeValueVList, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }


    /**
     * 获取关联代码值
     * <p>Title: getCodeValue</p>
     * <p>Description: 代码值参数类别</p>
     *
     * @param params
     * @return
     * @author wzl
     */
    @ApiOperation(value = "获取关联代码值")
    @ResponseBody
    @RequestMapping(value = "/getCodesToValue", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public LayuiUtil getCodesToValue(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Frame_CodeValue> codesToValueVList = codeValueService.getCodesToValue(query);
        int total = codeValueService.getCount(query);
        PageUtils pageUtil = new PageUtils(codesToValueVList, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }

    /**
     * 新增代码值
     * <p>Title: insertCodeValue</p>
     * <p>Description: 新增代码值</p>
     *
     * @return
     * @author wzl
     */
    @ApiOperation(value = "新增代码值")
    @ResponseBody
    @RequestMapping(value = "/insertCodeValue", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R insertCodeValue(Frame_CodeValue codeValue) {
        codeValueService.insertCodeValue(codeValue);

        //更新codeLevel
        Frame_CodeValue model = codeValueService.getDetailByGuid(codeValue.getRowGuid());
        String codeLevel = model.getRowId().toString();
        model.setCodeLevel(codeLevel);
        codeValueService.updateCodeValue(model);
        return R.ok();
    }

    /**
     * 新增代码值
     * <p>Title: insertCodeValue</p>
     * <p>Description: 新增代码值</p>
     *
     * @return
     * @author wzl
     */
    @ApiOperation(value = "新增代码值-多维")
    @ResponseBody
    @RequestMapping(value = "/addMuti", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R addMuti(Frame_CodeValue codeValue) {
        codeValueService.insertCodeValue(codeValue);

        //更新codeLevel
        Frame_CodeValue model = codeValueService.getDetailByGuid(codeValue.getRowGuid());
        String codeLevel = "";
        if (model.getParentGuid() != null && !model.getParentGuid().equals("")) {
            codeLevel = model.getParentGuid() + "." + model.getRowId().toString();
        } else {
            codeLevel = model.getRowId().toString();
        }
        model.setCodeLevel(codeLevel);


        codeValueService.updateCodeValue(model);

        return R.ok();
    }

    /**
     * 更新代码值
     * <p>Title: updateCodesBatch</p>
     * <p>Description: 更新代码值</p>
     *
     * @param frameCodeValue
     * @return
     * @author wzl
     */
    @PassToken
    @ApiOperation(value = "更新代码值参数")
    @ResponseBody
    @RequestMapping(value = "/updateCodeValue", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R updateCodeValueBatch(Frame_CodeValue frameCodeValue) {
        codeValueService.updateCodeValue(frameCodeValue);
        return R.ok();
    }

    /**
     * 保存列表数据
     * <p>Title: deleteDept</p>
     * <p>Description: 模块</p>
     *
     * @return
     * @author hero
     */
    @ApiOperation(value = "保存列表数据")
    @ResponseBody
    @RequestMapping(value = "/saveRows", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R saveRows(@RequestParam(value = "arrGuid[]") String[] arrGuid, @RequestParam(value = "arrSortSQ[]") String[] arrSortSQ,
                      @RequestParam(value = "arrItemTexts[]") String[] arrItemTexts, @RequestParam(value = "arrItemValues[]") String[] arrItemValues) {
        Frame_CodeValue model = new Frame_CodeValue();
        for (int i = 0; i < arrGuid.length; i++) {
            model.setRowGuid(arrGuid[i]);
            model.setItemText(arrItemTexts[i].toString());
            model.setItemValue(arrItemValues[i]);
            model.setSortSq(Integer.parseInt(arrSortSQ[i]));
            codeValueService.updateCodeValue(model);
        }
        return R.ok();
    }

    /**
     * 删除代码值
     * <p>Title: deleteCodes</p>
     * <p>Description: 批量删除代码值</p>
     *
     * @param rowGuids
     * @return
     * @author wzl
     */
    @ApiOperation(value = "删除多个代码值参数")
    @ResponseBody
    @RequestMapping(value = "/deleteCodeValue", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R deleteCodeValue(@RequestParam(value = "rowGuids[]") String[] rowGuids) {
        codeValueService.deleteCodeValue(rowGuids);
        return R.ok();
    }

    /**
     * 获取对应代码项的值
     * <p>Title: getCodeValue</p>
     * <p>Description: </p>
     *
     * @return
     * @author hero
     */
    @ApiOperation(value = "获取对应代码项的值")
    @ResponseBody
    @RequestMapping(value = "/getCodeByValue", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getCodeByValue(@RequestParam String codeName, String val) {
        List<Frame_Codes> codes = codesService.getCodesByName(codeName);
        if (codes != null && codes.size() > 1) {
            return R.error("该代码项存在多个相同的值");
        }
        List<Frame_CodeValue> value = codeValueService.getCodeByValue(codes.get(0).getRowGuid());

        if (value == null || value.size() == 0) {
            return R.ok().put("data", "");
        }
        JSONArray array = new JSONArray();
        for (Frame_CodeValue v : value) {
            JSONObject obj = new JSONObject();
            if (val != null && val.equals(v.getItemValue())) {
                obj.put("name", v.getItemText());
                obj.put("selected", "selected");
                obj.put("value", v.getItemValue());
                obj.put("disabled", "");
            } else {
                obj.put("name", v.getItemText());
                obj.put("value", v.getItemValue());
                obj.put("selected", "");
                obj.put("disabled", "");
            }
            array.add(obj);
        }

        return R.ok().put("data", array);
    }

    @ApiOperation(value = "获取对应代码项的值")
    @ResponseBody
    @RequestMapping(value = "/getCodeValueToMap", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R getCodeValueToMap(@RequestParam String codeName) {
        List<Frame_Codes> codes = codesService.getCodesByName(codeName);
        if (codes != null && codes.size() > 1) {
            return R.error("该代码项存在多个相同的值");
        }
        List<Frame_CodeValue> codeValue = codeValueService.getCodeValueByName(codes.get(0).getRowGuid());
        Map<String, Object> map = new HashMap<>();
        if (codeValue == null || codeValue.size() == 0) {
            return R.error("未找到任何代码项");
        }
        for (int i = 0; i < codeValue.size(); i++) {
            map.put(codeValue.get(i).getItemText(), codeValue.get(i).getItemValue());
        }
        return R.ok().put("data", map);
    }

    @ApiOperation(value = "获取对应代码项的值-多选")
    @ResponseBody
    @RequestMapping(value = "/getCodeValueMuti", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getCodeValueMuti(@RequestParam String codeName) {
        List<Frame_Codes> codes = codesService.getCodesByName(codeName);
        List<Frame_CodeValue> codeValue = codeValueService.getCodeValueByName(codes.get(0).getRowGuid());
        JSONArray array = new JSONArray();
        for (Frame_CodeValue v : codeValue) {
            JSONObject obj = new JSONObject();
            obj.put("name", v.getItemText());
            obj.put("value", v.getItemValue());
            obj.put("selected", "");
            obj.put("disabled", "");
            array.add(obj);
        }
        return R.ok().put("data", array);
    }

    @ApiOperation(value = "多维代码-获取子集")
    @ResponseBody
    @RequestMapping(value = "/MultigetSubCodes", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public List<TreeNode> MultigetSubCodes(String id, String name, String value, String tag) {
        if (value == null) {
            value = "";
        }
        List<Frame_CodeValue> codes = codeValueService.getCodesValueByGuid(tag, value);
        //System.out.println(tag + "  |  " + value);
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        for (Frame_CodeValue v : codes) {
            TreeNode tn = new TreeNode();
            tn.id = v.getRowId();
            tn.name = v.getItemText();
            tn.value = v.getCodeLevel();
            tn.tag = v.getCodeGuid();
            tn.click = "NodeChecked(this,'" + v.getCodeLevel() + "','" + v.getItemText() + "')";
            int Child = codeValueService.getCodesValueByGuid(v.getCodeGuid(), v.getCodeLevel()).size();
            tn.isParent = Child > 0 ? 1 : 0;

            nodes.add(tn);
        }
        return nodes;
    }

    /**
     * 通过rowGuid获取一条记录
     *
     * @param rowGuid
     * @return
     */
    @PassToken
    @ApiOperation(value = "通过rowGuid获取一条记录")
    @ResponseBody
    @RequestMapping(value = "/getDetailByGuid", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R getDetailByGuid(@RequestParam String rowGuid) {
        Frame_CodeValue model = codeValueService.getDetailByGuid(rowGuid);
        return R.ok().put("data", model);
    }

    @ApiOperation(value = "获取相应层级代码项")
    @ResponseBody
    @RequestMapping(value = "/getCodeValueByLevel", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getCodeValueByLevel(@RequestParam String codeName, @RequestParam String codeLevel) {
        //获取年月
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        //System.out.println(codeName + "   " + codeLevel + "  ");
        List<Frame_CodeValue> codeValues;
        if (month == 12) {
            codeValues = codeValueService.getCodeInfoByName(String.valueOf(year + 1));
        } else {
            codeValues = codeValueService.getCodeInfoByName(String.valueOf(year));
        }

        if (codeValues != null && codeValues.size() > 1) {
            return R.error("该代码项存在多个相同的值,请检查！");
        }
        assert codeValues != null;
        String codeGuid = codeValues.get(0).getCodeGuid();
        String level = codeValues.get(0).getCodeLevel();
        List<Frame_CodeValue> codeValue = codeValueService.getCodesValueByGuid(codeGuid, level);
        Map<String, Object> map = new HashMap<>();
        if (codeValue == null || codeValue.size() == 0) {
            return R.error("未找到任何代码项");
        }

        for (Frame_CodeValue frameCodeValue : codeValue) {
            map.put(frameCodeValue.getItemText(), frameCodeValue.getRowGuid());
        }
        return R.ok().put("data", map);
    }

    /**
     * 通过itemText获取一条记录
     *
     * @param itemText
     * @return
     */
    @PassToken
    @ApiOperation(value = "通过itemText获取一条记录")
    @ResponseBody
    @RequestMapping(value = "/getCodeInfoByName", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R getCodeInfoByName(@RequestParam String itemText) {
        List<Frame_CodeValue> frameCodeValueList = codeValueService.getCodeInfoByName(itemText);
        String rowGuid;
        if (frameCodeValueList.size() > 1) {
            return R.error("代码项含有相同的名称，请检查！");
        } else {
            rowGuid = frameCodeValueList.get(0).getRowGuid();
        }
        return R.ok().put("data", rowGuid);
    }


    @ApiOperation(value = "获取相应层级代码项(2级)")
    @ResponseBody
    @RequestMapping(value = "/getCodeValueByLevel2", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getCodeValueByLevel2(@RequestParam String rowGuid) {
        //获取年月
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        List<Frame_CodeValue> codeValues;
        if (month == 12) {
            codeValues = codeValueService.getCodeInfoByName(String.valueOf(year + 1));
        } else {
            codeValues = codeValueService.getCodeInfoByName(String.valueOf(year));
        }

        if (codeValues != null && codeValues.size() > 1) {
            return R.error("该代码项异常,请检查！");
        }
        assert codeValues != null;
        String codeGuid = codeValues.get(0).getCodeGuid();
        Frame_CodeValue codeValue = codeValueService.getDetailByGuid(rowGuid);
        String level = codeValue.getCodeLevel();
        List<Frame_CodeValue> codeValueList = codeValueService.getCodesValueByGuid(codeGuid, level);
        Map<String, Object> map = new HashMap<>();
        if (codeValueList == null || codeValueList.size() == 0) {
            return R.error("未找到任何代码项");
        }

        for (Frame_CodeValue frameCodeValue : codeValueList) {
            map.put(frameCodeValue.getItemText(), frameCodeValue.getRowGuid());
        }
        return R.ok().put("data", map);
    }

    @ApiOperation(value = "获取年度为民办事代码项")
    @ResponseBody
    @RequestMapping(value = "/getProposingByYear", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getProposingByYear(@RequestParam String codeName, @RequestParam String codeLevel) {
        //获取年月
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        //System.out.println(codeName + "   " + codeLevel + "  ");
        List<Frame_CodeValue> codeValues;
        if (month == 12) {
            codeValues = codeValueService.getCodeInfoByName(String.valueOf(year + 1)+"年");
        } else {
            codeValues = codeValueService.getCodeInfoByName(String.valueOf(year)+"年");
        }

        if (codeValues != null && codeValues.size() > 1) {
            return R.error("该代码项异常,请检查！");
        }
        assert codeValues != null;
        String codeGuid = codeValues.get(0).getCodeGuid();
        String level = codeValues.get(0).getCodeLevel();
        List<Frame_CodeValue> codeValue = codeValueService.getCodesValueByGuid(codeGuid, level);
        Map<String, Object> map = new HashMap<>();
        if (codeValue == null || codeValue.size() == 0) {
            return R.error("未找到任何代码项");
        }

        for (Frame_CodeValue frameCodeValue : codeValue) {
            map.put(frameCodeValue.getItemText(), frameCodeValue.getItemValue());
        }
        return R.ok().put("data", map);
    }
}
