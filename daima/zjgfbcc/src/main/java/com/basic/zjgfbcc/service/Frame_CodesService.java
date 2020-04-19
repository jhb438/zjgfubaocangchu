package com.basic.zjgfbcc.service;

import com.basic.zjgfbcc.entity.Frame_Codes;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: CodesService</p>
 * <p>Description: 代码项类务层</p>
 * @author wzl
 */
public interface Frame_CodesService {

    /**
     * 查询代码项参数列表
     *
     * @param params 代码项参数信息
     * @return 集合
     */
    List<Frame_Codes> getCodes(Map<String,Object> params);

    //获取数量
    int getCount(Map<String,Object> params);

    /**
     * 新增代码项参数列表
     *
     * @param codes 代码项参数信息
     * @return 集合
     */
    void insertCodes(Frame_Codes codes);

    /**
     * 批量生产代码项参数列表
     *
     * @param rowGuids 代码项参数信息
     * @return 集合
     */
    void deleteCodes(String[] rowGuids);

    /**
     * 获取所有代码项
     * @return
     */
    List<Frame_Codes> getAllCodes();

    /**
     * 单个更新代码项参数
     *
     * @param frameCodes 代码项参数信息
     * @return 集合
     */
    void updateCodes(Frame_Codes frameCodes);

    /**
     *
     * @param t
     * @param <T>
     * @return
     */
    <T> int checkCodeName(T t);

	List<Frame_Codes> getCodesByName(String codeName);

	Frame_Codes selectByRowGuid(String codesGuid);
}
