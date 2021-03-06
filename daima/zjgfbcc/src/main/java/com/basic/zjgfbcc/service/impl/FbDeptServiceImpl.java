package com.basic.zjgfbcc.service.impl;

import com.basic.zjgfbcc.entity.Frame_Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.basic.zjgfbcc.dao.mysql.FbDeptDao;
import com.basic.zjgfbcc.entity.FbDept;
import com.basic.zjgfbcc.service.FbDeptService;




@Service("fbDeptService")
@Transactional
public class FbDeptServiceImpl implements FbDeptService {


	@Autowired
	private FbDeptDao fbDeptDao;

	@Override
	public List<FbDept> getList(Map<String, Object> map){
		return fbDeptDao.getList(map);
	}

	@Override
	public List<FbDept> getQueryList(Map<String, Object> map)
	{
	    return fbDeptDao.getQueryList(map);
	}

	@Override
	public int getCount(Map<String, Object> map){
		return fbDeptDao.getCount(map);
	}

	@Override
	public void save(FbDept fbDept){
		fbDeptDao.save(fbDept);
	}

	@Override
	public void update(FbDept fbDept){
		fbDeptDao.update(fbDept);
	}

	@Override
	public void deleteBatch(String[] rowGuids){
		fbDeptDao.deleteBatch(rowGuids);
	}

    @Override
    public FbDept getDetailByGuid(String rowGuid) {
        return fbDeptDao.getDetailByGuid(rowGuid);
    }

    @Override
    public FbDept getDetailByPara(String para,String value) {
        return fbDeptDao.getDetailByPara(para,value);
    }

	@Override
	public List<FbDept> findDepts(String orgIndexCode) {
		return fbDeptDao.findDepts(orgIndexCode);
	}

	@Override
	public List<FbDept> getChildDepts(String orgIndexCode) {
		return fbDeptDao.getChildDepts(orgIndexCode);
	}

	@Override
	public void deleteAll() {
		fbDeptDao.deleteAll();
	}

	@Override
	public void insertAll(List<FbDept> list) {
		fbDeptDao.insertAll(list);
	}
	
}
