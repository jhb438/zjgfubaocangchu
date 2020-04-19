package com.basic.zjgfbcc.dao.mysql;

import com.basic.zjgfbcc.entity.Frame_CodeValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: CodeValueDao</p>
 * <p>Description: 代码值接口层</p>
 * @author wzl
 */
public interface Frame_CodeValueDao {
    /**
     * 查询代码值参数列表
     *
     * @param params 系统参数类别信息
     * @return 集合
     */
    List<Frame_CodeValue> getCodeValue(Map<String,Object> params);

    /**
     * 查询代码关联值参数列表
     *
     * @param params 系统参数类别信息
     * @return 集合
     */
    List<Frame_CodeValue> getCodesToValue(Map<String,Object> params);

    //获取数量
    int getCount(Map<String,Object> params);

    /**
     * 新增代码值参数
     *
     * @param  codeValue 代码值信息
     * @return 结果
     */
    int insertCodeValue(Frame_CodeValue codeValue);

    /**
     * 批量更新代码项参数
     *
     * @param frameCodeValue 代码值信息
     * @return 结果
     */
    void updateCodeValue(Frame_CodeValue frameCodeValue);

    /**
     * 批量删除代码值参数
     *
     * @param rowGuids 代码值信息
     * @return 结果
     */
    int deleteCodeValue(String [] rowGuids);

	List<Frame_CodeValue> getCodeByValue(@Param("codeGuid")String code);

	List<Frame_CodeValue> getCodeValueByName(@Param("ruid")String ruid);

    /**
     * 获取子节点的个数
     * @param codeGuid
     * @param codeLevel
     * @return
     */
	int getSubCount(@Param("codeGuid")String codeGuid,@Param("codeLevel")String codeLevel);

    List<Frame_CodeValue> getCodesValueByGuid(@Param("codeGuid")String codeGuid, @Param("codeLevel")String codeLevel);

    Frame_CodeValue getDetailByGuid(String rowGuid);

    /**
     * 获取相应层级代码项
     * @param codeGuid
     * @param codeLevel
     * @return
     */
    List<Frame_CodeValue> getCodeValueByLevel(@Param("codeGuid")String codeGuid,@Param("codeLevel")String codeLevel);

    /**
     * 根据名称获取信息
     * @param itemText
     * @return
     */
    List<Frame_CodeValue> getCodeInfoByName(String itemText);

    /**
     * 根据父级名称查询子级标题
     * @param fatherItemText
     * @param sonItemText
     * @return
     */
    List<Frame_CodeValue> getSonByFather(@Param("fatherItemText")String fatherItemText,@Param("sonItemText")String sonItemText);
}
