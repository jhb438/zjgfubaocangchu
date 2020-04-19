package com.basic.zjgfbcc.common.enumresource;

import com.basic.zjgfbcc.common.utils.EnumMessage;

public enum reportStatusEnum implements EnumMessage{
	YRE(1,"已上报"),
	NRE(0,"未上报");
	
	private final int code;
    private final String value;
    private reportStatusEnum(int code, String value) {
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
