package com.basic.zjgfbcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.dao.mysql.InformationInfoDao;
import com.basic.zjgfbcc.entity.InformationInfo;
import com.basic.zjgfbcc.service.InformationInfoService;




@Service("informationInfoService")
@Transactional
public class InformationInfoServiceImpl implements InformationInfoService {
	@Autowired
	private InformationInfoDao informationInfoDao;

	@Override
	public List<InformationInfo> getList(Map<String, Object> map){
		return informationInfoDao.getList(map);
	}

	@Override
	public List<InformationInfo> getList2(Map<String, Object> params) {
		return informationInfoDao.getList2(params);
	}

	@Override
	public int getCount2(Map<String, Object> params) {
		return informationInfoDao.getCount2(params);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return informationInfoDao.getCount(map);
	}

	@Override
	public void save(InformationInfo informationInfo){
		informationInfoDao.save(informationInfo);
	}

	@Override
	public void update(InformationInfo informationInfo){
		informationInfoDao.update(informationInfo);
	}

	@Override
	public void deleteBatch(String[] rowGuids){
		informationInfoDao.deleteBatch(rowGuids);
	}

	@Override
	public void auditPassInfo(String rowGuid) {
		informationInfoDao.auditPassInfo(rowGuid);
	}

	@Override
	public void auditFailInfo(String rowGuid) {
		informationInfoDao.auditFailInfo(rowGuid);
	}

	//发布信息
	@Override
	public void deliverInfoById(Integer[] ids) {
		informationInfoDao.deliverInfoById(ids);
	}

	//停止发布
	@Override
	public void  stopDeliverById(Integer[] ids) {
		informationInfoDao.stopDeliverById(ids);
	}

	@Override
	public void getTypeName(InformationInfo informationInfo) {
		informationInfoDao.getTypeName(informationInfo);
	}

	@Override
	public void infoOn(Integer id) {
		informationInfoDao.infoOn(id);
	}

	@Override
	public void infoOff(Integer id) {
		informationInfoDao.infoOff(id);
	}

	@Override
	public String getMInfoMation() {
		// TODO Auto-generated method stub
		return informationInfoDao.getMInfoMation();
	}
	@Override
	public  List<InformationInfo> getInfoListByCateGuid(String cateGoryGuid)
	{
		return  informationInfoDao.getInfoListByCateGuid(cateGoryGuid);
	}
	@Override
	public  InformationInfo getDetailByGuid(String rowGuid)
	{
		return  informationInfoDao.getDetailByGuid(rowGuid);
	}

	@Override
	public void deleteRelation(String rowGuid) {
		 informationInfoDao.deleteRelation(rowGuid);
	}

    @Override
    public List<InformationInfo> getListByMobile(Map<String, Object> map) {
		return informationInfoDao.getListByMobile(map);
    }

	@Override
	public List<String> selfCategory(String infoGuid) {
		return informationInfoDao.selfCategory(infoGuid);
	}


}
