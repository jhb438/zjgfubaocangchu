package com.basic.zjgfbcc.controller;

import com.basic.zjgfbcc.common.JJWT.JwtHelper;
import com.basic.zjgfbcc.common.JWT.TokenService;
import com.basic.zjgfbcc.common.config.TimeConfig;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.customclass.UserLoginToken;
import com.basic.zjgfbcc.common.utils.AESUtil;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.entity.Frame_Role;
import com.basic.zjgfbcc.entity.Frame_User;
import com.basic.zjgfbcc.service.Frame_DeptService;
import com.basic.zjgfbcc.service.Frame_RoleService;
import com.basic.zjgfbcc.service.Frame_UserService;
import com.basic.zjgfbcc.service.RedisService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys")
@Api(value="登录")
@CrossOrigin
public class Frame_LoginController {
	
	@Autowired
    Frame_UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
	private RedisService redisService;
    @Autowired
    private Frame_DeptService deptService;
    @Autowired
    private Frame_RoleService roleService;
    //登录
    @PassToken
    @ApiOperation(value="登录接口")
    @ResponseBody
    @RequestMapping(value="/login",produces="application/json;charset=utf-8",method=RequestMethod.POST)
    public R login(Frame_User user){
        Frame_User userForBase=userService.getFrameUserByLoginId(user.getLoginId());
        if(userForBase==null){
            
            return R.error("登录失败,用户不存在");
        }else {
           //if (!userForBase.getPassword().equals(Sha256.SHA(user.getPassword(), "SHA-256"))){
           // System.out.println(userForBase.getPassword()+">>>>>>"+AESUtil.encrypt(user.getPassword(), "expsoft1234"));
            if (!userForBase.getPassword().equals(AESUtil.encrypt(user.getPassword(), "expsoft1234"))){
                return R.error("登录失败,密码错误");
            }else {
                if(userForBase.getIsForbid()==1){
                    return R.error("用户已经禁用");
                }
                if(userForBase.getDelFlag()==1){
                    return R.error("用户不存在");
                }
                String token = JwtHelper.createJWT(userForBase.getRowGuid(), userForBase.getLoginId(), 1000*60*60*24);
                System.out.println(userForBase.getRowGuid()+" >>>>> "+userForBase.getLoginId());
                redisService.set(userForBase.getRowGuid(), userForBase.getLoginId(), 1000*60*60*24);
//                String token = tokenService.getToken(userForBase);
                //根据用户行标获取部门名部门行标
                Map<String, Object> maparams = userService.getDeptByGuid(userForBase.getRowGuid());
                Map<String, Object> map = new HashMap<>();
                map.put("token", token);
                map.put("userName", userForBase.getUserName()); 
                map.put("loginId", userForBase.getLoginId());
                map.put("mobile", userForBase.getMobile());
                map.put("sex", userForBase.getSex());
                map.put("userRowGuid", userForBase.getRowGuid());
                map.put("deptGuid", userForBase.getDeptGuid());
                map.put("gongHao", userForBase.getGongHao());
                map.put("deptName", maparams.get("deptName"));
                map.put("oucode", deptService.getDetailByGuid(userForBase.getDeptGuid()).getOucode());

                List<Frame_Role> frameRole= roleService.getRoleNameByUserGuid(userForBase.getRowGuid());
                String roleNames = "";
                for (int i = 0; i < frameRole.size(); i++) {
                    roleNames += frameRole.get(i).getRoleName() + ",";
                }
                roleNames = roleNames.substring(0, roleNames.length() - 1);
                map.put("roleName", roleNames);

                return R.ok().put("data", map);


            }
        }

    }
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
	
}
