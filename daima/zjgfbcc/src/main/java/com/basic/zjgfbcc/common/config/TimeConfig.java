package com.basic.zjgfbcc.common.config;

/**
 * jwt 时间配置 
 * @ClassName: TimeConfig 
 * @Description: jwt 时间配置 
 * @author keeny
 * @date 2018年11月6日 上午10:43:48 
 *
*/
public class TimeConfig {
   //创建jwt时设置的时间 60天有效  用户刷新的token
   final  public static long REFRESH_TOKEN_EXPIREX_SECOND=1000*60*60*24*60;

   //创建jwt时设置的时间 7天有效  用户请求验证的token
   final  public static long ACCESS_TOKEN_EXPIREX_SECOND=5000;

   //redis 中缓存登录人员的信息 60天有效
   final  public static long REFRESH_USERS_EXPIREX_SECOND=1000*60*24*60;

   //redis 中缓存登录人员的信息 7天有效
   final  public static long ACCESS_USERS_EXPIREX_SECOND=1000*60*24*7;

   //token 标记 用于请求或者刷新token
   final public static  String ACCESS_FLAG="access";

   final public static  String REFRESH_FLAG="refresh";
   
   //邮件重置密码 1天有效 
   final  public static long RESET_PWD_EXPIREX_SECOND=1000*60*60*24*1;
}
