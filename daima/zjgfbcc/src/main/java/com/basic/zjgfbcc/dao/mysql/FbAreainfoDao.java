package com.basic.zjgfbcc.dao.mysql;

import com.basic.zjgfbcc.entity.FbAreainfo;
import com.basic.zjgfbcc.entity.Frame_CodeValue;

import java.util.List;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 14:40:28
 */
public interface FbAreainfoDao extends BaseDao<FbAreainfo> {
    List<Frame_CodeValue> getAreaList();
}
