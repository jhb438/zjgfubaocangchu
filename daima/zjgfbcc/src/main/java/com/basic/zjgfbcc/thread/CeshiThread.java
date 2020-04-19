package com.basic.zjgfbcc.thread;

import java.util.List;

/**
 * 开启线程
* <p>Title: InsertOaUsersThread</p>  
* <p>Description: </p>  
* @author hero
 */
public class CeshiThread extends Thread{
//	List<Frame_User> userlist;
	
//	Frame_UserService userService = (Frame_UserService) SpringContextUtils.getBean("userService");
	
	public CeshiThread() {
//		this.userlist = userlist;
	}

	public void run(){
		System.out.println("task start ------");
		//更新oa用户
		
		System.out.println("task end ------");
	}
}
