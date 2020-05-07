package com.basic.zjgfbcc.dao.mysql;

import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.entity.FbDoorevents;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author 
 * @date 2020-04-19 13:17:50
 */
public interface FbDooreventsDao extends BaseDao<FbDoorevents> {

	void deleteAll();

	void insertAll(List<FbDoorevents> list);

	void deleteNowDays();

	List<Map<String, String>> Statistics(String str, String str2);

	List<Map<String, String>> StatisticsCBS(String string);

	List<FbDoorevents> getEventListByRu(@Param("areaName") String areaName, @Param("isCBS") String isCBS,
										@Param("isVTZ")String isVTZ,@Param("isKCSJ")String isKCSJ,@Param("isFK")String isFK,
										@Param("personName")String personName, @Param("orgName")String orgName);

	List<FbDoorevents> getEventListByShiJiChu(@Param("areaName") String areaName, @Param("isCBS") String isCBS,
											  @Param("isVTZ")String isVTZ,@Param("isKCSJ")String isKCSJ,@Param("isFK")String isFK,
											  @Param("personName")String personName, @Param("orgName")String orgName);

	void deleteSevenBe();

    FbDoorevents getLastDataById(String personId);
}
