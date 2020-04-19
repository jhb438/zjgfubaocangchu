package com.basic.zjgfbcc.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.customclass.RedisCacheable;
import com.basic.zjgfbcc.service.RedisService;


import java.lang.reflect.Method;
import java.lang.reflect.Type;


/**
 * @author hero
 * @Description
 * @create 2019-02-16 15:39
 * Order 拦截器执行顺序
 */
@Aspect
@Order(1)
@Component
public class RedisAop{
	
	public Logger logger = LoggerFactory.getLogger(RedisAop.class);
	
	@Value(value="${redis.config.time}")
	private String configTime;
	
    @Autowired
    private RedisService redisService;

    @Pointcut("@annotation(redisCacheable)")
    public void pointCut(RedisCacheable redisCacheable) {}
    
    
    /**
     * 根据参数生成cachekey
     * @param joinPoint
     * @return
     */
    private String getKey(ProceedingJoinPoint joinPoint,String key, String value) {
        String result = key+":"+value;
        return result;
    }

	// 设置切面为加有 @RedisCacheable注解的方法
    @Around("@annotation(redisCacheable)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, RedisCacheable redisCacheable) throws Throwable {
        System.out.println("拦截到方法：" + proceedingJoinPoint.getSignature().getName() + "方法...");
        return redisSelect(proceedingJoinPoint, redisCacheable);
    }


    @AfterThrowing(pointcut = "@annotation(redisCacheable)", throwing = "error")
    public void afterThrowing (Throwable  error, RedisCacheable redisCacheable){
        System.out.println("出现异常");
    }


    /**
     * 获取缓冲
     * @param proceedingJoinPoint
     * @param redisCacheable
     * @return
     */
    private Object redisSelect (ProceedingJoinPoint proceedingJoinPoint, RedisCacheable redisCacheable) throws Throwable {
        String value = redisCacheable.Value();
        String key = redisCacheable.Key();
        // 通过反射获取方法的参数、value、key 拼接为一个 key:value [...参数]的key值
        String newKey = getKey(proceedingJoinPoint,key,value);
        Object result = null;
        
        if ("select".equals(value)) {
        	//获取方法返回值类型
            Object[] args = proceedingJoinPoint.getArgs();
            Class<?>[] paramsCls = new Class<?>[args.length];
            for (int i = 0; i < args.length; ++i) {
                paramsCls[i] = args[i].getClass();
            }
    		//获取方法
    		Method method = proceedingJoinPoint.getTarget().getClass().getMethod(proceedingJoinPoint.getSignature().getName(), paramsCls);
    		//获取返回值类型
    		Type t = method.getAnnotatedReturnType().getType();
    		
            try {
                if(redisService.exists(newKey)) {
                	logger.info("从缓存中取出key为"+newKey);
                	String res = (String) redisService.get(newKey);
                	result = JSON.parseObject(res, t);
                } else {
                    result = getNewRedis(proceedingJoinPoint, redisCacheable, newKey, result);
                }
            } catch (Exception exception) {
                logger.info("Redis连接超时");
                result = getNewRedis(proceedingJoinPoint, redisCacheable, newKey, result);
            }
            return result;
		}else {
			redisService.remove(key+":select");
			result = proceedingJoinPoint.proceed();
			return result;
		}
        
        
    }


    /**
     * 获取数据库数据，并更新缓冲
     * @param proceedingJoinPoint
     * @param redisCacheable
     * @param newKey
     * @param result
     * @return
     * @throws Throwable
     */
    private Object getNewRedis (ProceedingJoinPoint proceedingJoinPoint, RedisCacheable redisCacheable, String newKey, Object result) throws Throwable {
        Long time = redisCacheable.time();
        result = proceedingJoinPoint.proceed();
        String res = JSONObject.toJSONString(result);
        if(time > 0L) {
        	redisService.set(newKey, res, time);
        } else {
        	if (configTime != null && !"".equals(configTime)) {
        		redisService.set(newKey, res,Long.valueOf(configTime));
			}else{
				logger.info("configTime 不正常");
			}
        }
        return result;
    }
}