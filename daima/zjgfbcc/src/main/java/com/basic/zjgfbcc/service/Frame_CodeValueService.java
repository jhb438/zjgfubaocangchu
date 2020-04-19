package com.basic.zjgfbcc.service;

import com.basic.zjgfbcc.entity.Frame_CodeValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: CodeValueService</p>
 * <p>Description: 代码值类务层</p>
 * @author wzl
 */
public interface Frame_CodeValueService {
	
    /**
     * 查询代码值参数列表
     *
     * @param params 代码值参数信息
     * @return 集合
     */
    List<Frame_CodeValue> getCodeValue(Map<String,Object> params);

    /**
     * 查询代码关联值参数列表
     *
     * @param params 代码值参数信息
     * @return 集合
     */
    List<Frame_CodeValue> getCodesToValue(Map<String,Object> params);

    //获取数量
    int getCount(Map<String,Object> params);

    /**
     * 新增代码值参数列表
     *
     * @param codeValue 系统参数信息
     * @return 集合
     */
    void insertCodeValue(Frame_CodeValue codeValue);

    /**
     * 更新代码值参数列表
     *
     * @param frameCodeValue 代码值参数信息
     * @return 集合
     *
     */
    void updateCodeValue(Frame_CodeValue frameCodeValue);

    /**
     * 批量删除代码项参数列表
     *
     * @param rowGuids 代码值参数信息
     * @return 集合
     */
    void deleteCodeValue(String[] rowGuids);

	List<Frame_CodeValue> getCodeByValue(String code);

	List<Frame_CodeValue> getCodeValueByName(String name);
	
	String getCodeByNameAndValue(String name,String code);

	Map<String, String> getCodeToMap(String name);

    int getSubCount(String codeGuid,String codeLevel);

    List<Frame_CodeValue> getCodesValueByGuid(String codeGuid, String codeLevel);

    Frame_CodeValue getDetailByGuid(String rowGuid);

    List<Frame_CodeValue> getCodeValueByLevel(String codeGuid, String codeLevel);

    List<Frame_CodeValue> getCodeInfoByName(String itemText);

    /**
     * 根据父级名称查询子级标题
     * @param fatherItemText
     * @param sonItemText
     * @return
     */
    List<Frame_CodeValue> getSonByFather(String fatherItemText,String sonItemText);

}

