package com.basic.zjgfbcc.common.utils;

import org.apache.commons.lang.StringUtils;

import com.basic.zjgfbcc.common.exception.MyException;


/**
 * SQL过滤
  * @ClassName: SQLFilter 
  * @Description: SQL过滤
  * @author keeny
  * @date 2019年1月8日 下午1:37:55 
  *
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str,boolean flag){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str=flag?Underline2Camel.camel2Underline(str):str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new MyException("包含非法字符");
            }
        }

        return str;
    }
}
