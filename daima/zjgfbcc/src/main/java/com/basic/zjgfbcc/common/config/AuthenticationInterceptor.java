package com.basic.zjgfbcc.common.config;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.basic.zjgfbcc.common.JJWT.JwtHelper;
import com.basic.zjgfbcc.common.customclass.PassToken;
import com.basic.zjgfbcc.common.customclass.UserLoginToken;
import com.basic.zjgfbcc.common.exception.MyException;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.entity.Frame_User;
import com.basic.zjgfbcc.service.Frame_UserService;
import com.basic.zjgfbcc.service.RedisService;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.sql.Date;

/**
 * 拦截器去获取token并验证token
* <p>Title: AuthenticationInterceptor</p>  
* <p>Description: </p>  
* @author hero
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    Frame_UserService userService;
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
//        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
//        // 如果不是映射到方法直接通过
//        if(!(object instanceof HandlerMethod)){
//            return true;
//        }
////        System.out.println((object instanceof HandlerMethod));
//        HandlerMethod handlerMethod=(HandlerMethod)object;
//        Method method=handlerMethod.getMethod();
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if (passToken.required()) { 
//                return true; 
//            }    
//        }            
//        else{     
//        	PassToken passToken = method.getAnnotation(PassToken.class);
//        	if (token == null) {
//        		System.out.println(token); 
//                throw new MyException("无token，请重新登录",401);
//            }
//        	 String userId;  
//             try {
//                 userId = JWT.decode(token).getAudience().get(0);
//             } catch (JWTDecodeException j) {
//                 throw new MyException("token格式错误，请重新登录",401);
//             }
//             Frame_User user = userService.findUserByGuid(userId);
//             if (user == null) {
//                 throw new MyException("用户不存在，请重新登录",401);
//             }
//             // 验证 token
//             JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//             try {
//                 jwtVerifier.verify(token);
//             } catch (JWTVerificationException e) {
//                 throw new MyException("token已过期，请重新登录",401);
//             }
//             return true;
//        }
//        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if (userLoginToken.required()) {
//                // 执行认证
//                if (token == null) { 
//                    throw new MyException("无token，请重新登录",401);
//                }
//                // 获取 token 中的 user id
//                String userId;
//                try {
//                     userId = JWT.decode(token).getAudience().get(0);
//                } catch (JWTDecodeException j) {
//                    throw new MyException("401",401);
//                }
//                Frame_User user = userService.findUserByGuid(userId);
//                if (user == null) {
//                    throw new MyException("用户不存在，请重新登录",401);
//                }
//                // 验证 token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//                try {
//                    jwtVerifier.verify(token);
//                } catch (JWTVerificationException e) {
//                    throw new MyException("token已过期，请重新登录",401);
//                }
//                return true;
//            }
//        }
//        return true;
//    }
    

    /**
      *  header token 拦截器 
      * @ClassName: HeaderOptionsInterceptor 
      * @Description: header token 拦截器 
      * @author lhq
      * @date 2018年11月6日 下午2:30:46 
      *
      */
    	@Autowired
        private RedisService redisService;

//        public AuthenticationInterceptor(RedisService   redisService) {
//            this.redisService = redisService;
//        }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
    	HandlerMethod handlerMethod=(HandlerMethod)handle;
       Method method=handlerMethod.getMethod(); 
    	//检查是否有passtoken注释，有则跳过认证
      if (method.isAnnotationPresent(PassToken.class)) {
          PassToken passToken = method.getAnnotation(PassToken.class);
          if (passToken.required()) { 
              return true; 
          }    
      }       
    	
    	response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //等到请求头信息authorization信息
        final String authHeader = request.getHeader("X-Auth-Token");
        if (authHeader == null) {
            response.getWriter().print(JSONObject.toJSONString(R.error(401, "用户token验证失败！")));
            return  false;
        }else{
            final String token = authHeader;
            try {
                final Claims claims = JwtHelper.parseJWT(token);
                if(claims!=null){
                    String uuid=claims.get("uuid").toString();
                    if(redisService.exists(uuid)){
                    	//说明token未过期
//                    	 java.util.Date expireTime = claims.getExpiration();
                    	//获取redis过期时间  秒单位
                    	Long expireTime = redisService.getExpire(uuid);
                         if (expireTime <= 60*60) {
                         	long now = new java.util.Date().getTime();
                         	now = now+1000*60*60*24;
                         	claims.setExpiration(new java.util.Date(now));
                         	redisService.expire(uuid, 1000*60*60*24);
     					}
                       return  true;
                    }else{
                        response.getWriter().print(JSONObject.toJSONString(R.error(401, "用户token验证失败！")));
                        return  false;
                    }
                }else {
                    response.getWriter().print(JSONObject.toJSONString(R.error(401, "用户token验证失败！")));
                    return  false;
                }

            } catch (final Exception e) {
                return  false;
            }
        }
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, 
                                  HttpServletResponse httpServletResponse, 
                            Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, 
                                          HttpServletResponse httpServletResponse, 
                                          Object o, Exception e) throws Exception {
    }
    
    public static long getDatePoor(java.util.Date expireTime, java.util.Date nowDate) {
    	 
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = expireTime.getTime() - nowDate.getTime();
        // 计算差多少分钟
        long min = diff / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return min;
    }
}