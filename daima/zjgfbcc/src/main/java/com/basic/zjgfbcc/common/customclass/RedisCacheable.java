package com.basic.zjgfbcc.common.customclass;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * @author hero
 * @Description 自定义的查询缓冲注解
 * @create 2019-02-16 15:02
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCacheable {
	
	//@RedisCacheable(Value = "select", Key = "类名+操控的表名",time=1000*60*30)
	//@RedisCacheable(Value = "insert", Key = "类名+操控的表名",time=1000*60*30)
	//@RedisCacheable(Value = "delete", Key = "类名+操控的表名",time=1000*60*30)
	//@RedisCacheable(Value = "update", Key = "类名+操控的表名",time=1000*60*30)
    String Value() default "";

    String Key() default "";

    long time() default 0L;
    
}
