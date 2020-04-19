package com.basic.zjgfbcc.common.enumresource;

import com.basic.zjgfbcc.common.utils.EnumMessage;

public enum ImportantEnum implements EnumMessage{
	BANLI(0,"办理中"),
	YIBANLI(1,"已办理"),
	YISHANGBAO(2,"已上报");
	
	private final int code;
    private final String value;
    private ImportantEnum(int code, String value) {
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
