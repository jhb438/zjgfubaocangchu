package com.basic.zjgfbcc.controller;

import com.alibaba.fastjson.JSONArray;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.*;
import com.basic.zjgfbcc.entity.Frame_ModuleRight;
import com.basic.zjgfbcc.entity.Frame_Role;
import com.basic.zjgfbcc.service.Frame_ModuleRightService;
import com.basic.zjgfbcc.service.Frame_RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色管理 信息操作处理
 * 角色控制层
 * @author my
 * @date 2019-03-11
 */
@CrossOrigin
@Api(value = "角色管理")
@RestController
@RequestMapping("sys/role")
public class Frame_RoleController {
    private final static Logger logger = LoggerFactory.getLogger(Frame_RoleController.class);

    @Autowired
    private Frame_RoleService roleService;
    
    @Autowired
    private Frame_ModuleRightService moduleRightService;
    /**
     * 查询角色列表
     * <p>Title: getRole</p>
     * <p>Description: 角色</p>
     * @author my
     * @param params
     * @return
     */
    @PassToken
    @ApiOperation(value = "查询角色列表")
    @ResponseBody
    @RequestMapping(value = "/getRole",produces="application/json;charset=utf-8",method= RequestMethod.GET)
    public LayuiUtil getRole(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
        List<Frame_Role> list = roleService.selectFrameRoleList(query);
        int total = roleService.getCount(query);
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }
    
    /**
     * 获取所有角色
     * <p>Title: getAllRole</p>  
     * <p>Description: </p>
     * @author hero  
     * @return
     */
    @ApiOperation(value = "查询所有角色列表")
    @ResponseBody
    @RequestMapping(value = "/getAllRole",produces="application/json;charset=utf-8",method= RequestMethod.GET)
    public R getAllRole(){
    	List<Frame_Role> list = roleService.getAllRole();
    	return R.ok().put("data", list);
    }
    
    /**
     * 新增角色
     * <p>Title: addRole</p>
     * <p>Description: 角色</p>
     * @author my
     * @param frame_role
     * @return
     */
    @ApiOperation(value = "新增角色")
    @ResponseBody
    @RequestMapping(value = "/add",produces="application/json;charset=utf-8",method= RequestMethod.POST)
    public R add(Frame_Role frame_role){
        roleService.insertFrameRole(frame_role);
        return R.ok();
    }

    /**
     * 修改角色
     * <p>Title: addRole</p>
     * <p>Description: 角色</p>
     * @author my
     * @param frameRole
     * @return
     */
    @ApiOperation(value = "修改角色")
    @ResponseBody
    @RequestMapping(value = "/updateRole",produces="application/json;charset=utf-8",method= RequestMethod.POST)
    public R updateRole(Frame_Role frameRole){
        roleService.updateFrameRole(frameRole);
        return R.ok();
    }

    /**
     * 删除角色的所有内容
     * <p>Title: deleteRole</p>
     * <p>Description: 角色</p>
     * @author my
     * @return
     */
    @ApiOperation(value="删除角色的所有内容")
    @ResponseBody
    @RequestMapping(value="/deleteRole",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R deleteRole(@RequestParam(value="rowGuids[]") String [] rowGuids){
        moduleRightService.deleteModuleRightByGuid(rowGuids);
        roleService.deleteFrameRoleByRowGuid(rowGuids);
        return R.ok();
    }

    /**
     * 通过rowGuid获取一条记录
     * @param rowGuid
     * @return
     */
    @ApiOperation(value="通过rowGuid获取一条记录")
    @ResponseBody
    @RequestMapping(value="/getDetailByGuid",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R getDetailByGuid(@RequestParam String rowGuid){
        Frame_Role model = roleService.getDetailByGuid(rowGuid);
        return R.ok().put("data",model);
    }

    /**
     * 通过角色名查询
     * <p>Title: findFrameRoleByName</p>
     * <p>Description: 角色名</p>
     * @author my
     * @param
     * @return
     */
    @ApiOperation(value="通过角色名查询")
    @ResponseBody
    @RequestMapping(value="/findFrameRoleByName/{name}",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R selectFrameRoleByName(@PathVariable("name")String roleName ){
        roleService.selectFrameRoleByName(roleName);
        return R.ok();
    }
    
    /**
     * 通过角色guid查询拥有的模块并返回tree
     * <p>Title: selectModuleByRole</p>  
     * <p>Description: </p>
     * @author hero  
     * @return
     */
    @ApiOperation(value="通过角色guid查询拥有的模块返回tree")
    @ResponseBody
    @RequestMapping(value="/selectModule",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R selectModuleByRoleGuid(@RequestBody String roleGuid){
    	JSONArray trees = moduleRightService.selectModuleByRoleGuid(roleGuid);
    	return R.ok().put("data", trees);
    }

    /**
     * 批量新增模块权限
     * <p>Title: insertModuleRightBatch</p>
     * <p>Description: </p>
     * @author wzl
     * @return
     */
    @ApiOperation(value="批量新增模块权限")
    @ResponseBody
    @RequestMapping(value="/insertModuleRightBatch",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R insertModuleRightBatch(@RequestBody Map<Object, Object> params){
        //新增之前根据allowTo指向目标全删
    	String roleGuid = (String) params.get("checkedBtn");
    	if (StringUtil.isEmpty(roleGuid)) {
			return R.error("请先选择要设置的角色");
		}
    	List<String> moduleGuids = (List<String>) params.get("paramArray");
    	if (moduleGuids.size() == 0) {
			return R.ok();
		}
    	List<Frame_ModuleRight> moduleRightList = new ArrayList<>();
    	for (String moduleGuid : moduleGuids) {
    		Frame_ModuleRight moduleRight = new Frame_ModuleRight();
    		moduleRight.setModuleGuid(moduleGuid);
    		moduleRight.setAllowTo(roleGuid);
    		moduleRightList.add(moduleRight);
		}
    	//根据roleGuid删除记录
    	moduleRightService.deleteModuleRightBatch(roleGuid);
    	//批量新增
    	moduleRightService.insertModuleRightBatch(moduleRightList);
        return R.ok();
    }

    /**
     * 通过角色名检验是否重复
     * <p>Title: checkUserByRoleName</p>
     * <p>Description: 角色</p>
     *
     * @param
     * @return
     * @author my
     */
    @ApiOperation(value = "通过角色名检验")
    @ResponseBody
    @RequestMapping(value = "/checkUserByRoleName", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R checkUserByRoleName(String roleName) {
        Frame_Role frameRole = roleService.checkUserByRoleName(roleName);
        if(frameRole !=null){
            return R.error("角色名已存在！");
        }
        return R.ok();
    }

    /**
     * 根据userGuid获取所有角色
     *
     */
    @PassToken
    @ApiOperation(value = "根据userGuid获取所有角色")
    @ResponseBody
    @RequestMapping(value = "/getRoleNameByUserGuid", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getRoleNameByUserGuid(String userGuid) {
        List<Frame_Role> frameRole = roleService.getRoleNameByUserGuid(userGuid);
        return R.ok().put("data",frameRole);
    }
}
