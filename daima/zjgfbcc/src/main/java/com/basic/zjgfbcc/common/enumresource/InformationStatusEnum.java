package com.basic.zjgfbcc.common.enumresource;

import com.basic.zjgfbcc.common.utils.EnumMessage;

public enum InformationStatusEnum implements EnumMessage{
	DSH(0,"待审核"),
	YFB(1,"已发布");
	
	private final int code;
    private final String value;
    private InformationStatusEnum(int code, String value) {
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
