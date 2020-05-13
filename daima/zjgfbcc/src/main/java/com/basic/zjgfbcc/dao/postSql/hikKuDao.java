package com.basic.zjgfbcc.dao.postSql;

import java.util.List;

import com.basic.zjgfbcc.entity.FbDoorevents;

public interface hikKuDao {
	
	List<FbDoorevents> getEvents();

	List<FbDoorevents> getEventsByEventId(String eventId);

}
