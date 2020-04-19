package com.basic.zjgfbcc.common.WebSocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.basic.zjgfbcc.entity.*;
import com.basic.zjgfbcc.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.common.enumresource.messageEnum;
import com.basic.zjgfbcc.common.utils.SpringContextUtils;
import com.basic.zjgfbcc.controller.BaseController;


@ServerEndpoint("/websocket/{sid}/{workGuid}")
//@Component
public class WebSocketServer {
	
	/**
	 * 重中之重
	 */
	 private static RedisService redisService;
	 @Autowired
     public void setRedisService(RedisService redisService) {
		 WebSocketServer.redisService= redisService;
	 }
	


	//定义一个全局的记录器，通过LoggerFactory获取
    public static Logger log = LoggerFactory.getLogger(BaseController.class);
	
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid="";
    
    private String workGuid="";
   
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid,@PathParam("workGuid") String workGuid) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
        this.sid=sid;
        this.workGuid = workGuid;
//    	连接成功以后缓存房间号（工作guid）至redis
//        if (redisService.exists("chatRom"+workGuid)) {
//        	String sidArr = (String) redisService.get("chatRom"+workGuid);
//        	JSONObject obj = JSONObject.parseObject(sidArr);
//        	//取出arr以后，往里面塞sid
//			obj.put(sid, "");
//			redisService.set("chatRom"+workGuid, obj.toJSONString());
//			
//		}else{
//			JSONObject obj = new JSONObject();
//			obj.put(sid, "");
//			redisService.set("chatRom"+workGuid, obj.toJSONString());
//		}
        
        try {
        	 sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
//        //连接关闭则清除缓存
//        if (redisService.exists("chatRom"+this.workGuid)) {
//        	String sidArr = (String) redisService.get("chatRom"+this.workGuid);
//        	JSONObject obj = JSONObject.parseObject(sidArr);
//        	if (obj.containsKey(this.sid)) {
//				obj.remove(this.sid);
//				redisService.set("chatRom"+workGuid, obj.toJSONString());
//			}
//		}
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
    	if ("ping".equals(message)) {
    		//群发消息
            for (WebSocketServer item : webSocketSet) {
                try {
                    item.sendMessage("pong");
                    System.out.println("pong");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}else{
			log.info("收到来自窗口"+sid+"的信息:"+message);
			//对该消息进行过滤
			
			
			
	        //群发消息
	        for (WebSocketServer item : webSocketSet) {
	        	System.out.println(item.sid+"  >>>>>>>"+sid);
	        	if (item.sid.equals(sid)) {
	        		try {
	        			JSONObject jsonObject = new JSONObject();
	        	    	jsonObject.put("emit", "chatMessage");
	        	    	
	        	    	JSONObject obj = JSONObject.parseObject(message);
	        	    	System.out.println(obj.toJSONString());
	        	    	JSONArray arr = obj.getJSONArray("memberList");
	        	    	long timestamp = System.currentTimeMillis();
	        	    	for (int i = 0; i < arr.size(); i++) {
							JSONObject o = arr.getJSONObject(i);
							o.put("timestamp", timestamp);
							o.put("content", obj.getJSONObject("mine").getString("content"));
							//发送给前端
							jsonObject.put("data", o);
							item.sendInfo(jsonObject.toJSONString(), o.getString("userGuid"));
						}
	        	    	//发送之前存储记录


						
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
				}
	        }
		}
    }

	/**
	 * 
	 * @param session
	 * @param error
	 */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
	
    /**
	 * 实现服务器主动推送
	 */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    
//    public void sendChatMessage(String message) throws IOException {
//    	JSONObject jsonObject = new JSONObject();
//    	jsonObject.put("emit", "chatMessage");
//    	jsonObject.put("data", "aaa");
//        try {
//			this.session.getBasicRemote().sendObject(jsonObject.toJSONString());
//		} catch (EncodeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
//    }

    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
    	log.info("推送消息到窗口"+sid+"，推送内容:"+message);
        for (WebSocketServer item : webSocketSet) {
            try {
            	//这里可以设定只推送给这个sid的，为null则全部推送
            	if(sid==null) {
            		item.sendMessage(message);
            	}else if(item.sid.equals(sid)){
            		item.sendMessage(message);
            	}
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
