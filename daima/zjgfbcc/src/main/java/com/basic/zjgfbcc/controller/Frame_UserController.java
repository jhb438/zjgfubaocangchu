package com.basic.zjgfbcc.controller;

import cn.jiguang.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.utils.*;
import com.basic.zjgfbcc.entity.*;
import com.basic.zjgfbcc.service.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.UUID;


/**
 * <p>Title: WxappAgreementController</p>
 * <p>Description: 用户控制层</p>
 *
 * @author hero
 */

@CrossOrigin
@Api(value = "用户")
@RestController
@RequestMapping("sys/user")
public class Frame_UserController {

    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(Frame_UserController.class);

    @Autowired
    private Frame_UserService userService;

    @Autowired
    private Frame_Role_UserService roleUserService;
    @Autowired
    private Frame_DeptService deptService;
    @Autowired
    private Frame_ModuleRightService moduleRightService;
    @Autowired
    Frame_ModuleService moduleService;
    @Autowired
    private Frame_ConfigService configService;
    @Autowired
    private Frame_RoleService roleService;
    

    /**
     * 获取所有正常用户
     * <p>Title: getUser</p>
     * <p>Description: 用户</p>
     *
     * @param params
     * @return
     * @author hero
     */
    @PassToken
    @ApiOperation(value = "获取所有正常用户")
    @ResponseBody
    @RequestMapping(value = "/getAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public LayuiUtil getUser(@RequestParam Map<String, Object> params) {
    	if (params.get("spots") != null) {
			params.put("spots", ((String) params.get("spots")).split(","));
		}
    	if (params.get("centerSpots") != null) {
			params.put("centerSpots", ((String) params.get("centerSpots")).split(","));
		}

        Query query = new Query(params);
        List<Frame_User> userList = userService.getUser(query);
        int total = userService.getCount(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }

    /**
     * 通过角色获取用户并且分页
     * <p>Title: getUserByRoleName</p>
     * <p>Description: 用户</p>
     *
     * @param params
     * @return
     * @author hero
     */
    @PassToken
    @ApiOperation(value = "通过角色获取用户并且分页")
    @ResponseBody
    @RequestMapping(value = "/getUserByRoleName", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public LayuiUtil getUserByRoleName(@RequestParam Map<String, Object> params) {

        Query query = new Query(params);
        List<Frame_User> userList = userService.getUserByRoleName(query);
        int total = userService.getUserFromRoleName((String) params.get("roleName")).size();
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }

    /**
     * 新增用户
     * <p>Title: insert</p>
     * <p>Description: 新增用户</p>
     *
     * @param params
     * @return
     * @author hero
     */
    @ApiOperation(value = "新增用户")
    @ResponseBody
    @RequestMapping(value = "/add",produces="application/json;charset=utf-8", method = RequestMethod.POST)
    public R insert(Frame_User user) {
        //生成uuid作为rowguid
        String uuid = UUID.randomUUID().toString();
        user.setRowGuid(uuid);
        Date createTime = DateUtil.changeDate(new Date());
        user.setCreateTime(createTime);

        Frame_Dept deptModel=deptService.getDetailByGuid(user.getDeptGuid());
        user.setDeptCode(deptModel.getDeptCode());

        if (user.getPassword() == null || user.getPassword().equals("")) {
            String pass1 = configService.getDefaultPassWord();
            String pass2 = AESUtil.encrypt(pass1, "expsoft1234");
            user.setPassword(pass2);
            userService.insert(user);
        } else {
            //密码hash加密
            String password = AESUtil.encrypt(user.getPassword(),"expsoft1234");
            user.setPassword(password);
            userService.insert(user);
        }

        //插入用户与角色关系
        String[] roleGuids = user.getRoleGuid();
        List<Frame_Role_User> roUserList = new ArrayList<>();
        for (String roleGuid : roleGuids) {
            Frame_Role_User roUser = new Frame_Role_User();
            roUser.setUserGuid(uuid);
            roUser.setRoleGuid(roleGuid);
            roUserList.add(roUser);
        }
        roleUserService.insertBatch(roUserList);

        return R.ok();
    }

    /**
     * 更新用户
     * <p>Title: update</p>
     * <p>Description: 更新用户</p>
     *
     * @param params
     * @param
     * @return
     * @author hero
     */
    @ApiOperation(value = "更新用户")
    @ResponseBody
    @RequestMapping(value = "/update", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R update(Frame_User user) {
//        List<String> roleGuidList = (List<String>) params.get("roleGuids");
        String[] roleGuidList = user.getRoleGuid();
        if (!"".equals(user.getPassword())) {
        	 //密码加密
            String password = AESUtil.encrypt(user.getPassword(), "expsoft1234");
            user.setPassword(password);
		}else {
			user.setPassword(null);
		}
        //先全部删除所有有关角色
        roleUserService.deleteByUserId(user.getRowGuid());
        if (roleGuidList != null && roleGuidList.length != 0) {
        	 //再新增有关角色
            List<Frame_Role_User> roUserList = new ArrayList<>();
            for (String role : roleGuidList) {
                Frame_Role_User roUser = new Frame_Role_User();
                roUser.setUserGuid(user.getRowGuid());
                roUser.setRoleGuid(role);
                roUserList.add(roUser);
            }
            roleUserService.insertBatch(roUserList);
		}
//        String cu = ChineseCharacterUtil.getLowerCase(user.getUserName().toLowerCase(), false);//生成小写简拼
//        user.setLoginId(cu);
        userService.update(user);
        return R.ok();
    }

    /**
     * 根据用户guid查询角色list
     * <p>Title: getCheckedRole</p>
     * <p>Description: </p>
     *
     * @return
     * @author hero
     */
    @ApiOperation(value = "根据用户guid查询角色list")
    @ResponseBody
    @RequestMapping(value = "/getCheckedRole", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getCheckedRole(@RequestParam String userGuid) {
        List<String> roles = roleUserService.getCheckedRole(userGuid);
        return R.ok().put("data", roles);
    }

    /**
     * 点击角色获取用户列表
     *
     * @param roleGuid
     * @return
     */
    @PassToken
    @ApiOperation(value = "点击角色获取用户列表")
    @ResponseBody
    @RequestMapping(value = "/getUserFromRole", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public LayuiUtil getUserFromRole(@RequestParam String roleGuid, @RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Frame_User> list = userService.getUserFromRole(roleGuid);
        int total = list.size();
        PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
        return LayuiUtil.data(pageUtil.getTotalCount(), pageUtil.getList());
    }

    /**
     * 根据用户名查询所有模块
     * <p>Title: getMenuByUserGuid</p>
     * <p>Description: </p>
     *
     * @param userGuid
     * @return
     * @author hero
     */
    @PassToken
    @ApiOperation(value = "根据用户名查询所有模块")
    @ResponseBody
    @RequestMapping(value = "/getMenuByUserGuid", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R getMenuByUserGuid(@RequestParam String userGuid) {
        //根据用户guid查询角色list
        List<String> roles = roleUserService.getCheckedRole(userGuid);
        JSONArray obj = new JSONArray();
        if (roles != null && roles.size() != 0) {
            for (String roleGuid : roles) {
                //通过角色查询所有模块
                JSONArray arr = moduleRightService.selectModuleByRole(roleGuid);
                if (arr != null) {
                    for (int i = 0; i < arr.size(); i++) {
                        obj.add(arr.getJSONObject(i));
                    }
                }
            }
        }
        //去重合并
//		System.out.println(roles);
//		System.out.println(obj);
        //求并集
        for (int i = 0; i < obj.size(); i++) {
            for (int j = obj.size() - 1; j > i; j--) {
                JSONObject ijs = obj.getJSONObject(i);
                JSONObject js = obj.getJSONObject(j);
                if (ijs.getString("title").equals(js.getString("title"))) {
                    js.getJSONArray("data").removeAll(ijs.getJSONArray("data"));
                    ijs.getJSONArray("data").addAll(js.getJSONArray("data"));
                    js.getJSONArray("data").clear();
                }
            }
        }
        //遍历以后删除空集合
        for (int i = 0; i < obj.size(); i++) {
        	System.out.println(i);
            JSONObject js = obj.getJSONObject(i);
            if (js.getJSONArray("data") == null || js.getJSONArray("data").size() == 0) {
                obj.remove(i);
                i--;
            }
        }
        return R.ok().put("data", obj);
    }

    /**
     * 根据用户名查询所有模块
     * <p>Title: GetSubMenuByUserGuid</p>
     * <p>Description: </p>
     *
     * @param pModuleCode
     * @return
     * @author hero
     */
    @PassToken
    @ApiOperation(value = "根据用户名查询所有模块")
    @ResponseBody
    @RequestMapping(value = "/GetSubMenuByUserGuid", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public R GetSubMenuByUserGuid(@RequestParam String pModuleCode, HttpServletRequest httpServletRequest) {
        String userGuid = httpServletRequest.getHeader("userGuid");// 从 http 请求头中取出

        List<Frame_Module> moduleParent = moduleService.GetSubMenu(pModuleCode,userGuid);
        List<TplMenu> list=new ArrayList<TplMenu>();
        for (Frame_Module item :moduleParent)
        {
            TplMenu menu = new TplMenu();
            menu.name = item.getModuleCode();
            menu.title = item.getModuleName();
            menu.url = item.getModuleAddr();
            menu.icon = item.getSmallIcon();
            menu.spread = false;
            if (menu.icon.equals("")||menu.icon==null)
            {
                menu.icon = "layui-icon-app";
            }
            menu.list = GetSubMenuItem(item.getModuleCode(),userGuid);
            list.add(menu);
        }
        return R.ok().put("data", list);
    }

    /// <summary>
    /// 循环遍历菜单
    /// </summary>
    /// <param name="ModuleCode"></param>
    /// <returns></returns>
    public List<TplMenu> GetSubMenuItem(String ModuleCode,String userGuid)
    {
        List<TplMenu> list = new ArrayList<TplMenu>();
        List<Frame_Module> moduleParent = moduleService.GetSubMenu(ModuleCode,userGuid);
        if (moduleParent.size() > 0)
        {
            for (Frame_Module item: moduleParent)
            {
                TplMenu menu = new TplMenu();
                menu.name = item.getModuleCode();
                menu.title = item.getModuleName();
                menu.url = item.getModuleAddr();
                //menu.icon = item.getSmallIcon();
                menu.list = GetSubMenuItem(item.getModuleCode(),userGuid);
                list.add(menu);
            }
        }
        return list;
    }

    /**
     * 重置用户密码
     * <p>Title: update</p>
     * <p>Description: 重置用户密码</p>
     *
     * @param rowGuids
     * @return
     * @author hero
     */
    @ApiOperation(value = "重置用户密码")
    @ResponseBody
    @RequestMapping(value = "/resetPassword", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R resetPassword( @RequestParam(value="rowGuids[]")String[] rowGuids) {
        String password = configService.getDefaultPassWord();

        String pass1 = AESUtil.encrypt(password, "expsoft1234");
        userService.resetPasswordById(pass1, rowGuids);
        return R.ok();
    }


    /**
     * 删除多个用户
     * <p>Title: deleteUser</p>
     * <p>Description: 用户</p>
     *
     * @param rowGuids
     * @return
     * @author my
     */
    @ApiOperation(value = "删除多个用户")
    @ResponseBody
    @RequestMapping(value = "/deleteUser", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R deleteUser(@RequestParam(value="rowGuids[]")String[] rowGuids) {
        userService.deleteUserById(rowGuids);
        roleUserService.deleteRoleUserByGuid(rowGuids);
        return R.ok();
    }

    /**
     * 启用多个用户
     * <p>Title: enableUser</p>
     * <p>Description: 用户</p>
     *
     * @param rowGuids
     * @return
     * @author wzl
     */
    @ApiOperation(value = "启用多个用户")
    @ResponseBody
    @RequestMapping(value = "/enableUser", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R enableUser(@RequestParam(value="rowGuids[]")String[] rowGuids) {
        userService.enableUserById(rowGuids);
        return R.ok();
    }

    /**
     * 禁用多个用户
     * <p>Title: forbidUser</p>
     * <p>Description: 用户</p>
     *
     * @param rowGuids
     * @return
     * @author wzl
     */
    @ApiOperation(value = "禁用多个用户")
    @ResponseBody
    @RequestMapping(value = "/forbidUser", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R forbidUser(@RequestParam(value="rowGuids[]")String[] rowGuids) {
        userService.forbidUserById(rowGuids);
        return R.ok();
    }

    /**
     * 保存排序号
     * <p>Title: enableUser</p>
     * <p>Description: 用户</p>
     *
     * @return
     * @author wzl
     */
    @ApiOperation(value = "保存排序号")
    @ResponseBody
    @RequestMapping(value = "/saveSortSq", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R saveSortSq(@RequestParam(value="arrGuid[]") String [] arrGuid,@RequestParam(value="arrSortSQ[]") String [] arrSortSQ) {
      Frame_User model=new Frame_User();
        for(int i=0;i<arrGuid.length;i++) {
            model.setRowGuid(arrGuid[i]);
            model.setSortSq(Integer.parseInt(arrSortSQ[i]));
            userService.update(model);
        }
        return R.ok();
    }

    /**
     * 通过登录名查询用户
     * <p>Title: findFrameUserByLoginId</p>
     * <p>Description: 用户</p>
     *
     * @param
     * @return
     * @author wzl
     */
    @ApiOperation(value = "通过登录名查询用户")
    @ResponseBody
    @RequestMapping(value = "/findFrameUserByLoginId/{loginId}", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R getFrameUserByLoginId(@PathVariable("loginId") String loginId) {
        userService.getFrameUserByLoginId(loginId);
        //System.out.println(loginId);
        return R.ok();
    }

    /**
     * 通过登录名检验用户重复
     * <p>Title: checkFrameUserByLoginId</p>
     * <p>Description: 用户</p>
     *
     * @param
     * @return
     * @author wzl
     */
    @ApiOperation(value = "通过登录名检验用户")
    @ResponseBody
    @RequestMapping(value = "/checkUser", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public <T> R checkUser(@RequestBody T t){
        int count = userService.checkUser(t);
        if (count >= 1) {
            return R.error("您输入的值已存在，请重新输入");
        }
        return R.ok();
    }

    /**
     * 旧密码验证
     * <p>Title: changePassword</p>
     * <p>Description: 用户</p>
     *
     * @param
     * @return
     * @author wzl
     */
    @ApiOperation(value = "用户旧密码验证")
    @ResponseBody
    @RequestMapping(value = "/checkOldPassword", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R checkOldPassword(@RequestParam String rowGuid,@RequestParam String old_pass){
        String old_pass2=AESUtil.encrypt(old_pass, "expsoft1234");
        System.out.println(old_pass2);
        String oldPass = userService.checkOldPassword(rowGuid);
        System.out.println(oldPass);
        if(!old_pass2.equals(oldPass)){
            return R.error("旧密码错误");
        }
        return R.ok();
    }

    /**
     * 写入新密码
     * <p>Title: changePassword</p>
     * <p>Description: 用户</p>
     *
     * @param
     * @return
     * @author wzl
     */
    @ApiOperation(value = "用户新密码写入")
    @ResponseBody
    @RequestMapping(value = "/updateNewPassword", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R updateNewPassword(@RequestParam("loginId")String loginId,@RequestParam("old_pass") String old_pass,@RequestParam("new_pass") String new_pass){
        Frame_User userForBase = userService.getFrameUserByLoginId(loginId);
        if (!userForBase.getPassword().equals(AESUtil.encrypt(old_pass, "expsoft1234"))) {
            return R.error(-1,"当前密码输入错误!");
        } else {
            String new_pass2=AESUtil.encrypt(new_pass, "expsoft1234");
            userService.updateNewPassword(userForBase.getRowGuid(),new_pass2);
            return R.ok();

        }
    }

    @ApiOperation(value = "更新管理员")
    @ResponseBody
    @RequestMapping(value = "/updateAdmin", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public R updateAdmin(@RequestBody Frame_User user) {
        userService.updateAdmin(user);
        return R.ok();
    }

    @ApiOperation(value = "更新用户登录时间")
    @ResponseBody
    @RequestMapping(value = "/updateLoginTime", produces = "application/json;charset=utf-8", method = RequestMethod.PUT)
    public R updateLoginTime(@RequestBody Frame_User frame_user){
        Date loginTime = DateUtil.changeDate(new Date());
        frame_user.setLastloginTime(loginTime);
        userService.updateLoginTime(frame_user);
        return R.ok();
    }

    /**
     * 获取所有正常用户
     * <p>Title: getAllUserList</p>
     * <p>Description: </p>
     * @author jianghb
     * @return
     */
    @ApiOperation(value = "查询所有正常用户")
    @ResponseBody
    @RequestMapping(value = "/getAllUserList",produces="application/json;charset=utf-8",method= RequestMethod.POST)
    public R getAllUserList(){
        List<Frame_User> list = userService.getAllUserList();
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
        Frame_User model = userService.getDetailByGuid(rowGuid);
        List<Frame_Role> u = userService.getRolesByUserGuid(model.getRowGuid());
        model.setRoles(u);
        return R.ok().put("data", model);
    }




    
}


