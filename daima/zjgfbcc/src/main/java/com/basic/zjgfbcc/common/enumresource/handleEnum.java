package com.basic.zjgfbcc.common.enumresource;

import com.basic.zjgfbcc.common.utils.EnumMessage;

/**
 * 是否接单枚举
* <p>Title: ReceiveOrderEnum</p>  
* <p>Description: </p>  
* @author hero
 */
public enum handleEnum implements EnumMessage{
	READYHANDLE(0,"待交办"),
	READYSHENHE(1,"待审核"),
	ALREADYDOWN(2,"已办理"),
	ALREADYHANDLE(3,"已交办");
	
	private final int code;
    private final String value;
    private handleEnum(int code, String value) {
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
