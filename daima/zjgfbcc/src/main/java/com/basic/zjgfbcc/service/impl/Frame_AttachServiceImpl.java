package com.basic.zjgfbcc.service.impl;

import com.basic.zjgfbcc.dao.mysql.Frame_AttachDao;
import com.basic.zjgfbcc.entity.Frame_Attach;
import com.basic.zjgfbcc.service.Frame_AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("frameAttachService")
public class Frame_AttachServiceImpl implements Frame_AttachService {
    @Autowired
    private Frame_AttachDao frame_attachDao;
    @Override
    public Frame_Attach selectFrameAttachById(Integer rowId) {
        return frame_attachDao.selectFrameAttachById(rowId);
    }

    @Override
    public Frame_Attach selectFrameAttachByName(String name) {
        return frame_attachDao.selectFrameAttachByName(name);
    }

    @Override
    public List<Frame_Attach> selectFrameAttachList(Map<String, Object> params) {
        return frame_attachDao.selectFrameAttachList(params);
    }

    @Override
    public void insertFrameAttach(Frame_Attach frameAttach) {
       frame_attachDao.insertFrameAttach(frameAttach);
    }

    @Override
    public void updateFrameAttach(Frame_Attach frameAttach) {
      frame_attachDao.updateFrameAttach(frameAttach);
    }

    @Override
    public void deleteFrameAttachByIds(String[] guids) {
        frame_attachDao.deleteFrameAttachById(guids);
    }

    @Override
    public int getCount(Map<String, Object> params) {
        return frame_attachDao.getCount(params);
    }

	@Override
	public void updateAttach(String attachGuid, String[] rowGuid) {
		// TODO Auto-generated method stub
		frame_attachDao.updateAttach(attachGuid,rowGuid);
	}

	@Override
	public Frame_Attach getByFormGuid(String guid) {
		// TODO Auto-generated method stub
		return frame_attachDao.getByFormGuid(guid);
	}

	@Override
	public List<Frame_Attach> getAttachList(String guid) {
		// TODO Auto-generated method stub
		return frame_attachDao.getAttachList(guid);
	}

}
