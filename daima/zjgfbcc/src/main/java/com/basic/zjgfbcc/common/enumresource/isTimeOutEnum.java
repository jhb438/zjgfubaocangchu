package com.basic.zjgfbcc.common.enumresource;

import com.basic.zjgfbcc.common.utils.EnumMessage;

/**
 * 是否接单枚举
* <p>Title: ReceiveOrderEnum</p>  
* <p>Description: </p>  
* @author hero
 */
public enum isTimeOutEnum implements EnumMessage{
	NORMAL(0,"正常"),
	JJGQ(1,"即将过期"),
	YGQ(2,"已过期");
	
	private final int code;
    private final String value;
    private isTimeOutEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}
}
