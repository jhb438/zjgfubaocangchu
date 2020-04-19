package com.basic.zjgfbcc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.*;
import com.basic.zjgfbcc.entity.*;
import com.basic.zjgfbcc.service.*;
import com.basic.zjgfbcc.service.api.JyApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.Future;

/**
 * 部门管理 信息操作处理
 * 部门控制层
 * @author wzl
 * @date 2019-03-06
 */
@CrossOrigin
@Api(value = "部门")
@RestController
@RequestMapping("sys/dept")
public class Frame_DeptController extends BaseController {
    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(Frame_DeptController.class);

    @Autowired
    private Frame_DeptService deptService;
    @Autowired
    private Frame_UserService userService;
    @Autowired
    private Frame_ConfigService configService;

    @Autowired
    private Frame_RoleService roleService;
    @Autowired
    private Frame_Role_UserService role_userService;

    @Autowired
    public JyApiService jyApiService;



    /**
     * 查询部门列表
     * <p>Title: getDept</p>
     * <p>Description: 部门</p>
     *
     * @param params
     * @return
     * @author hero
     */
    @PassToken
    @ApiOperation(value = "查询部门列表")
    @ResponseBody
    @RequestMapping(value = "/getDept", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public LayuiUtil getDept(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Frame_Dept> list = deptService.selectFrameDeptList(query);
        int total = deptService.getCount(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }

    /**
     * 新增部门
     * <p>Title: addDept</p>
     * <p>Description: 部门</p>
     *
     * @param frame_dept
     * @return
     * @author hero
     */
    @Transactional
    @ApiOperation(value = "新增部门")
    @ResponseBody
    @RequestMapping(value = "/add", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R addDept(Frame_Dept frame_dept) {

        deptService.insertFrameDept(frame_dept);
        //更新deptCode
        Frame_Dept model = deptService.getDetailByGuid(frame_dept.getRowGuid());
        String DeptCode = "";
        if (model.getPdeptCode() == null || model.getPdeptCode().equals("")) {
            DeptCode = model.getRowId().toString();
        } else {
            DeptCode = model.getPdeptCode() + "." + model.getRowId().toString();
        }
        model.setDeptCode(DeptCode);
        //rowGuid重新取值
        int c = UtilsController.countChar(DeptCode, ".") + 1;
        model.setCodeLevel(c);
        deptService.updateFrameDept(model);

        return R.ok("新增成功");
    }

    /**
     * 修改部门
     * <p>Title: addDept</p>
     * <p>Description: 部门</p>
     *
     * @param frameDept
     * @return
     * @author hero
     */
    @ApiOperation(value = "修改部门")
    @ResponseBody
    @RequestMapping(value = "/updateDept", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R updateDept(Frame_Dept frameDept) {
        String oldDeptCode = frameDept.getDeptCode();
        String newDeptCode = "";
        if (frameDept.getPdeptCode().equals("")) {
            newDeptCode = frameDept.getRowId().toString();
            frameDept.setDeptCode(frameDept.getRowId().toString());
        } else {
            newDeptCode = frameDept.getPdeptCode() + "." + frameDept.getRowId().toString();
            frameDept.setDeptCode(frameDept.getPdeptCode() + "." + frameDept.getRowId().toString());
        }
        deptService.updateFrameDept(frameDept);
        deptService.updatePDetCode(oldDeptCode, newDeptCode);
        return R.ok();
    }

    /**
     * 删除部门
     * <p>Title: deleteDept</p>
     * <p>Description: 部门</p>
     *
     * @param rowGuids
     * @return
     * @author hero
     */
    @ApiOperation(value = "删除部门")
    @ResponseBody
    @RequestMapping(value = "/deleteDept", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R deleteDept(@RequestParam(value = "rowGuids[]") String[] rowGuids) {
        deptService.deleteFrameDeptById(rowGuids);
        return R.ok();
    }

    /**
     * 查询部门树
     * <p>Title: getdeptTrees</p>
     * <p>Description: </p>
     *
     * @return
     * @author hero
     */
    @ApiOperation(value = "查询部门树")
    @ResponseBody
    @RequestMapping(value = "/getDeptTrees", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R getdeptTrees() {
        JSONArray trees = deptService.findTopDepts();
        return R.ok().put("data", trees);
    }




    /**
     * 查询部门树
     * <p>Title: getdeptTrees</p>
     * <p>Description: </p>
     *
     * @return
     * @author hero
     */
    @ApiOperation(value = "查询部门树")
    @ResponseBody
    @RequestMapping(value = "/getDeptTree", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public List<TreeNode> getdeptTree(String id, String name, String value) {
        String deptcode = "";
        if (value != null) {
            deptcode = value;
        }

        List<Frame_Dept> trees = deptService.findDepts(deptcode, "1");

        List<TreeNode> nodes = new ArrayList<TreeNode>();
        for (Frame_Dept v : trees) {
            TreeNode tn = new TreeNode();
            tn.id = v.getRowId();
            tn.name = v.getDeptName();
            tn.value = v.getDeptCode();
            tn.click = "NodeChecked(this,'" + v.getDeptCode() + "','" + v.getDeptName() + "','" + v.getRowGuid() + "')";
            int Child = deptService.getChildDepts(v.getDeptCode()).size();
            tn.isParent = Child > 0 ? 1 : 0;

            nodes.add(tn);
        }
        return nodes;
    }





    /**
     * 查询上级部门
     * <p>Title: getByDeptCode</p>
     * <p>Description: </p>
     *
     * @return
     * @author wzl
     */
    @ApiOperation(value = "查询下级所有部门")
    @ResponseBody
    @RequestMapping(value = "/getChildDepts", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R getChildDepts(@RequestParam("deptCode") String deptCode) {
        List<Frame_Dept> list = deptService.getChildDepts(deptCode);
        return R.ok().put("data", list);
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
        Frame_Dept frame_Dept = deptService.getDetailByGuid(rowGuid);
        return R.ok().put("data", frame_Dept);
    }

    /**
     * 查询上级部门
     * <p>Title: getByDeptCode</p>
     * <p>Description: </p>
     *
     * @return
     * @author wzl
     */
    @ApiOperation(value = "查询上级部门")
    @ResponseBody
    @RequestMapping(value = "/getByDeptCode/{deptCode}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getByDeptCode(@PathVariable("deptCode") String deptCode) {
        Frame_Dept dept = deptService.getByDeptCode(deptCode);
        System.out.println(deptCode);
        return R.ok().put("data", dept.getDeptName());
    }

    /**
     * 根据部门名查询指定部门用户
     * <p>Title: getByDeptCode</p>
     * <p>Description: </p>
     *
     * @return
     * @author wzl
     */
    @ApiOperation(value = "根据部门名查询指定部门用户")
    @ResponseBody
    @RequestMapping(value = "/getUserByDeptName", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R getUserByDeptName(@RequestParam String deptName) {
        List<Frame_User> userList = deptService.getDeviceByDeptGuid(deptName);
        return R.ok().put("data", userList);
    }

    /**
     * 验证重复性
     * <p>Title: checkModuleNmae</p>
     * <p>Description: </p>
     *
     * @param <T>
     * @return
     * @author hero
     */
    @ApiOperation(value = "验证重复性")
    @ResponseBody
    @RequestMapping(value = "/checkDept", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public <T> R checkDept(@RequestBody T t) {
        //泛型无法判断传入的值是否为空
        int count = deptService.checkDept(t);
        if (count >= 1) {
            return R.error("您输入的值已存在，请重新输入");
        }
        return R.ok();
    }

    /**
     * 重点工作部门树
     * <p>Title: getImpDeptTree</p>
     * <p>Description: </p>
     *
     * @return nodes
     * @author hero
     */
    @ApiOperation(value = "重点工作部门树")
    @ResponseBody
    @RequestMapping(value = "/getImpDeptTree", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public List<TreeNode> getImpDeptTree(String id, String name, String value) {
        String deptcode = "";
        if (value != null) {
            deptcode = value;
        }

        List<Frame_Dept> trees = deptService.findDepts(deptcode, "");

        List<TreeNode> nodes = new ArrayList<>();
        for (Frame_Dept v : trees) {
            TreeNode tn = new TreeNode();
            tn.id = v.getRowId();
            tn.name = v.getDeptName();
            tn.value = v.getDeptCode();
            tn.tag = v.getRowGuid();
            //tn.click = "NodeChecked(this,'" +  v.getDeptCode() + "','" + v.getDeptName() + "')";
            int Child = deptService.getChildDepts(v.getDeptCode()).size();
            tn.isParent = Child > 0 ? 1 : 0;

            nodes.add(tn);
        }
        return nodes;
    }

    /**
     * 获取除系统管理部外所有部门
     */
    @ApiOperation(value = "获取除系统管理部外所有部门")
    @ResponseBody
    @RequestMapping(value = "/getDeptList", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R getDeptList() {
        List<Frame_CodeValue> list = deptService.getDeptList();
        return R.ok().put("data", list);
    }


    public void updateDeptByName(int page) {
        String deptUrl = InterFace_DZZJUrl;
        Map map = new HashMap();
        map.put("_a", "getunit");
        map.put("authKey", interface_AuthKey);
        map.put("page", String.valueOf(page));
        String deptData = new HttpUtil().sendGet(deptUrl, map);
        JSONObject unitObj = JSONObject.parseObject(deptData);
        JSONObject headerObj = unitObj.getJSONObject("header");
        JSONArray unitArray = unitObj.getJSONArray("body");
        if (headerObj.getInteger("count") > 0) {
            for (int i = 0; i < unitArray.size(); i++) {
                JSONObject deptChildObj = unitArray.getJSONObject(i);

                Frame_Dept deptModel = deptService.getDetailByPara("dept_name", deptChildObj.getString("unit_name"));
                deptModel.setOucode(deptChildObj.getString("unit_no"));
                deptModel.setSortSq(deptChildObj.getInteger("orderby"));
                deptService.updateFrameDept(deptModel);
            }


            page++;
            updateDeptByName(page);
        }
    }




}
