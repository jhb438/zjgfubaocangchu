package com.basic.zjgfbcc.dao.mysql;

import com.basic.zjgfbcc.entity.InformationCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author 
 * @date 2019-04-09 11:30:38
 */
public interface InformationCategoryDao extends BaseDao<InformationCategory> {

	List<InformationCategory> findTopTrees(String pcategoryCode);

	List<InformationCategory> getByPCategoryCode(String categoryCode);

	List<InformationCategory> getAllList();

	List<InformationCategory> getCategoryByGuid(@Param("categoryGuid")String categoryGuid);

    String getNextCateGoryCode(String pcategoryCode);

	InformationCategory getDetailByGuid(String rowGuid);

	List<InformationCategory> findTopTreesByGuid(String rowGuid);
}
