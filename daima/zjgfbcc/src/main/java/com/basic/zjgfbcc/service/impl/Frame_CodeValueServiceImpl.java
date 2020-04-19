package com.basic.zjgfbcc.service.impl;

import com.basic.zjgfbcc.common.exception.MyException;
import com.basic.zjgfbcc.common.utils.R;
import com.basic.zjgfbcc.dao.mysql.Frame_CodeValueDao;
import com.basic.zjgfbcc.entity.Frame_CodeValue;
import com.basic.zjgfbcc.entity.Frame_Codes;
import com.basic.zjgfbcc.service.Frame_CodeValueService;
import com.basic.zjgfbcc.service.Frame_CodesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: FrameCodeValueService</p>
 * <p>Description: 代码值IMPL服务层</p>
 *
 * @author wzl
 */
@Service("codeValueService")
public class Frame_CodeValueServiceImpl implements Frame_CodeValueService {

    @Autowired
    private Frame_CodeValueDao codeValueDao;
    
    @Autowired
    private Frame_CodesService codeService;

    /**
     * 获取代码值类别信息
     * @param params 代码值信息
     * @return
     */
    @Override
    public List<Frame_CodeValue> getCodeValue(Map<String, Object> params) {
        return codeValueDao.getCodeValue(params);
    }

    @Override
    public List<Frame_CodeValue> getCodesToValue(Map<String, Object> params) {
        return codeValueDao.getCodesToValue(params);
    }

    @Override
    public int getCount(Map<String, Object> params) {
        return codeValueDao.getCount(params);
    }

    /**
     * 新增代码值参数
     * @param codeValue 代码值参数信息
     */
    @Override
    public void insertCodeValue(Frame_CodeValue codeValue) {
        codeValueDao.insertCodeValue(codeValue);
    }

    /**
     * 更新代码值参数
     * @param frameCodeValue
     * 代码值参数信息
     */
    @Override
    public void updateCodeValue(Frame_CodeValue frameCodeValue) {
        codeValueDao.updateCodeValue(frameCodeValue);
    }

    /**
     * 批量删除代码值参数
     * @param rowGuids 代码值ID信息
     */
    @Override
    public void deleteCodeValue(String[] rowGuids) {
        codeValueDao.deleteCodeValue(rowGuids);
    }
    
	@Override
	public List<Frame_CodeValue> getCodeByValue(String code) {
		// TODO Auto-generated method stub
		return codeValueDao.getCodeByValue(code);
	}

	@Override
	public List<Frame_CodeValue> getCodeValueByName(String name) {
		// TODO Auto-generated method stub
		return codeValueDao.getCodeValueByName(name);
	}
	
	/**
	 * 根据对应的代码项和对应的值获取对应text
	 * <p>Title: getCodeByNameAndValue</p>  
	 * <p>Description: </p>
	 * @author hero  
	 * @param name
	 * @param code
	 * @return
	 */
	@Override
	public String getCodeByNameAndValue(String name, String code) {
		if ("".equals(code) || code == null) {
			return null;
		}
		List<Frame_Codes> codes = codeService.getCodesByName(name);
    	if (codes != null && codes.size() > 1) {
			throw new MyException("未找到对应代码项");
		}
    	List<Frame_CodeValue> codeValue = getCodeValueByName(codes.get(0).getRowGuid());
		if (codeValue.size() == 0) {
			throw new MyException("该代码项不存在任何值");
		}
    	for (int i = 0; i < codeValue.size(); i++) {
    		System.out.println(code);
    		System.out.println(codeValue.get(i).getItemValue());
			if(code.equals(codeValue.get(i).getItemValue())){
				return codeValue.get(i).getItemText();
			}
		}
    	return "未找到对应text";
	}
	
	/**
	 * 获取对应代码项的值并封装给map
	 * <p>Title: getCodeToMap</p>  
	 * <p>Description: </p>
	 * @author hero  
	 * @return
	 */
	@Override
	public Map<String, String> getCodeToMap(String name){
		if ("".equals(name) || name == null) {
			return null;
		}
		List<Frame_Codes> codes = codeService.getCodesByName(name);
    	if (codes != null && codes.size() > 1) {
			throw new MyException("未找到对应代码项");
		}
    	List<Frame_CodeValue> codeValue = getCodeValueByName(codes.get(0).getRowGuid());
		if (codeValue.size() == 0) {
			throw new MyException("该代码项不存在任何值");
		}
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < codeValue.size(); i++) {
			map.put(codeValue.get(i).getItemText(),codeValue.get(i).getItemValue());
		}
		return map;
	}

	/**
	 * 获取子节点个数
	 * @param codeGuid
	 * @param codeLevel
	 * @return
	 */
	public int getSubCount(String codeGuid,String codeLevel)
	{
		return codeValueDao.getSubCount(codeGuid,codeLevel);
	}

	@Override
	public List<Frame_CodeValue> getCodesValueByGuid(String codeGuid, String codeLevel) {
		return codeValueDao.getCodesValueByGuid(codeGuid,codeLevel);
	}

	@Override
	public Frame_CodeValue getDetailByGuid(String rowGuid) {
		return codeValueDao.getDetailByGuid(rowGuid);
	}

	@Override
	public List<Frame_CodeValue> getCodeValueByLevel(String codeGuid, String codeLevel) {
		return codeValueDao.getCodeValueByLevel(codeGuid,codeLevel);
	}

	@Override
	public List<Frame_CodeValue> getCodeInfoByName(String itemText) {
		return codeValueDao.getCodeInfoByName(itemText);
	}

	@Override
	public List<Frame_CodeValue> getSonByFather(String fatherItemText, String sonItemText) {
		return codeValueDao.getSonByFather(fatherItemText,sonItemText);
	}
}
