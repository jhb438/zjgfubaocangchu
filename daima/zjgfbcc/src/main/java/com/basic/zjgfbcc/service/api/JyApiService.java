//package com.basic.zjgfbcc.service.api;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.stereotype.Service;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.basic.zjgfbcc.common.utils.Base64Util;
//import com.basic.zjgfbcc.common.utils.HttpUtil;
//import com.basic.zjgfbcc.controller.api.BaseApiController;
//
///**
// * http://221.224.212.36:81 警翼接口
// * @author hero
// *
// */
//@Service("JyApiService")
//public class JyApiService extends BaseApiController{
//
//	/**
//	 * 组织新增
//	 * @param params
//	 * @return
//	 */
//	public String UnitinfoUp(List<Map<String,String>> params){
//		
//		String url = InterFace_DZZJUrl+"/joinApi/UnitinfoUp.php?authKey="+interface_AuthKey+"&unitcode="+unitcode;
//		logger.info("警翼组织新增接口参数:"+JSONObject.toJSONString(params));
//		String res = "";
//		try {
//			res = HttpUtil.sendHttpPost(url,JSONObject.toJSONString(params));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger.info("警翼组织新增接口返回结果:"+res);
//		return res;
//	}
//	
//	/**
//	 * 警员信息上传
//	 * @param params
//	 * @return
//	 */
//	public String PoliceinfoUp(List<Map<String, String>> params) {
//		
//		String url = InterFace_DZZJUrl+"/joinApi/PoliceinfoUp.php?authKey="+interface_AuthKey+"&unitcode="+unitcode;
//		logger.info("警翼警员信息上传接口参数:"+JSONObject.toJSONString(params));
//		String res = "";
//		try {
//			res = HttpUtil.sendHttpPost(url, JSONObject.toJSONString(params));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger.info("警翼警员信息上传接口返回结果:"+res);
//		return res;
//	}
//	
//	/**
//	 * 系统用户基本信息上传
//	 * @param params
//	 * @return
//	 */
//	public String UserinfoUp(List<Map<String, String>> params) {
//		String url = InterFace_DZZJUrl+"/joinApi/UserinfoUp.php?authKey="+interface_AuthKey+"&unitcode="+unitcode;
//		logger.info("系统用户信息上传接口参数:"+JSONObject.toJSONString(params));
//		String res = "";
//		try {
//			res = HttpUtil.sendHttpPost(url, JSONObject.toJSONString(params));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger.info("系统用户信息上传接口返回结果:"+res);
//		return res;
//	}
//	
//	/**
//	 * 模糊查询部门信息 8081
//	 * @param params
//	 * @return
//	 */
//	public JSONObject departmentFuzzy(Map<String, String> params) {
//		String res = "";
//		try {
//			res = this.login();
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		JSONObject obj = JSONObject.parseObject(res);
//		if(obj.containsKey("result") && obj.getString("result").equals("SUCCESS")){
//			
//			//获取部门信息
//			String para = "";
//			try {
//				para = Base64Util.encryptBASE64(JSONObject.toJSONString(params));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			String url = InterFace_Url+"/api/departmentFuzzy?para="+para;
//			String result = new HttpUtil().sendPostByCookie(url,new HashMap());
//			JSONObject resObj = JSONObject.parseObject(result);
//			if(resObj.containsKey("result") && resObj.getString("result").equals("SUCCESS")){
//				try {
//					String r =Base64Util.decryptBASE64toStr(resObj.getString("data"));
//					JSONObject Obj = JSONObject.parseObject(r);
//					JSONArray arr = Obj.getJSONArray("data");
//					return arr.getJSONObject(0);
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//			
//		return new JSONObject();
//	}
//	
//	
//	
//}
