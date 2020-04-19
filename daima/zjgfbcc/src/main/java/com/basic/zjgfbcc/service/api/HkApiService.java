package com.basic.zjgfbcc.service.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.basic.zjgfbcc.controller.api.BaseApiController;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

/**
 * http://221.224.212.36:81 警翼接口
 * @author hero
 *
 */
@Service("HkApiService")
public class HkApiService extends BaseApiController{

	/**
	 * 请根据技术支持提供的实际的平台IP/端口和API网关中的合作方信息更换static静态块中的三个参数.
	 * [1 host]
	 * 		host格式为IP：Port，如10.0.0.1:443
	 * 		当使用https协议调用接口时，IP是平台（nginx）IP，Port是https协议的端口；
	 *     当使用http协议调用接口时，IP是artemis服务的IP，Port是artemis服务的端口（默认9016）。
	 * [2 appKey和appSecret]
	 * 		请按照技术支持提供的合作方Key和合作方Secret修改
	 * 	    appKey：合作方Key
	 * 	    appSecret：合作方Secret
	 * 调用前看清接口传入的是什么，是传入json就用doPostStringArtemis方法，是表单提交就用doPostFromArtemis方法
	 *
	 */
	static {
		ArtemisConfig.host = hkHsot; // 平台/nginx的IP和端口（https端口默认为443）
		ArtemisConfig.appKey = hkKey; // 合作方Key
		ArtemisConfig.appSecret = hkSecret;// 合作方Secret
	}
	

	/**
	 * API网关的后端服务上下文为：/artemis
	 */
	private static final String ARTEMIS_PATH = "/artemis";
	
	//分页获取人员列表
	public String personList(){
//		String camerasDataApi = ARTEMIS_PATH +"/api/resource/v1/person/personList";
//		Map<String,String> path = new HashMap<String,String>(2){
//			{
//				put("https://",camerasDataApi);
//			}
//		};
//		Map<String, String> params = new HashMap<>();
//		params.put("pageNo", "1");
//		params.put("pageSize", "1000000");
//		String body=JSONObject.toJSONString(params);
//		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
		
		String result = "{\"code\":\"0\",\"msg\":\"SUCCESS\",\"data\":{\"total\":779,\"pageNo\":1,\"pageSize\":10,\"list\":[{\"personId\":\"b795faa2b05849dfb3bac450f7e8cdc3\",\"personName\":\"Steve Zhou\",\"gender\":1,\"orgPath\":\"张家港孚宝仓储/访客/VCMC\",\"orgIndexCode\":\"00a6a7f3-41b5-403a-abd6-0ad9bce0915f\",\"orgName\":\"VCMC\",\"certificateType\":111,\"certificateNo\":null,\"fingerPrint\":[],\"updateTime\":\"2020-01-14T15:58:05.966+08:00\",\"birthday\":null,\"phoneNo\":\"13764588849\",\"address\":null,\"personPhoto\":null,\"email\":null,\"education\":null,\"nation\":null,\"jobNo\":null},{\"personId\":\"b63ef62a1b12485181f88da1d358de71\",\"personName\":\"汤建浩\",\"gender\":1,\"orgPath\":\"张家港孚宝仓储/罐区运营/IT\",\"orgIndexCode\":\"d0b4cdfe-52ec-4e02-8a1c-558834f4c4a6\",\"orgName\":\"IT\",\"certificateType\":111,\"certificateNo\":null,\"fingerPrint\":[],\"updateTime\":\"2020-01-08T09:34:49.830+08:00\",\"birthday\":null,\"phoneNo\":\"18015699939\",\"address\":null,\"personPhoto\":{\"picUri\":\"/pic?=d21idf1e*1dbi39d-c7419a--427a9a1b7a69aifb1*=ids1*=idp2*=tdpe*m5i16=847166a-78zf7fs=7f09d0\",\"serverIndexCode\":\"20007fdf-33de-4ce5-b922-a13fe89ac9f3\"},\"email\":null,\"education\":null,\"nation\":null,\"jobNo\":null},{\"personId\":\"5ada7a637cda4f4683beb3297a102956\",\"personName\":\"Peter Wang\",\"gender\":1,\"orgPath\":\"张家港孚宝仓储/罐区运营/罐区经理\",\"orgIndexCode\":\"f018f6d7-0891-4a40-9991-6b5586e74088\",\"orgName\":\"罐区经理\",\"certificateType\":111,\"certificateNo\":null,\"fingerPrint\":[],\"updateTime\":\"2020-01-08T09:35:35.277+08:00\",\"birthday\":null,\"phoneNo\":\"13328030088\",\"address\":null,\"personPhoto\":{\"picUri\":\"/pic?=d6=idf3z31dbs06d-c4719a--427a9a1b7a69aifb1*=ids1*=idp2*=pd*m0i1t=8e745136a-66if7f*e4f49d8\",\"serverIndexCode\":\"20007fdf-33de-4ce5-b922-a13fe89ac9f3\"},\"email\":null,\"education\":null,\"nation\":null,\"jobNo\":null},{\"personId\":\"7bc7a558060d4173bd64e61c68828d12\",\"personName\":\"徐晓光\",\"gender\":1,\"orgPath\":\"张家港孚宝仓储/办公室/SHE\",\"orgIndexCode\":\"2b60a5bd-e45a-4a97-b9ec-2a456fe9bbc9\",\"orgName\":\"SHE\",\"certificateType\":111,\"certificateNo\":null,\"fingerPrint\":[],\"updateTime\":\"2020-03-16T15:50:34.925+08:00\",\"birthday\":null,\"phoneNo\":\"18622566904\",\"address\":null,\"personPhoto\":{\"picUri\":\"/pic?=d0=idf0z21dbs52d-c9819a--427a9a1b7a69aifb1*=ids1*=idp8*=pd*m6i1t=8e745836a-81if7f*e2f09d8\",\"serverIndexCode\":\"20007fdf-33de-4ce5-b922-a13fe89ac9f3\"},\"email\":null,\"education\":null,\"nation\":null,\"jobNo\":null},{\"personId\":\"d2bef32f0f6e4028a9053634627221e4\",\"personName\":\"许磊\",\"gender\":1,\"orgPath\":\"张家港孚宝仓储/办公室/SHE\",\"orgIndexCode\":\"2b60a5bd-e45a-4a97-b9ec-2a456fe9bbc9\",\"orgName\":\"SHE\",\"certificateType\":111,\"certificateNo\":null,\"fingerPrint\":[],\"updateTime\":\"2020-03-16T15:50:34.931+08:00\",\"birthday\":null,\"phoneNo\":\"15962378028\",\"address\":null,\"personPhoto\":{\"picUri\":\"/pic?=d0=idf5z61dbs60d-c9719a--427a9a1b7a69aifb1*=ids1*=idp1*=pd*m7i1t=8e745186a-26if7f*e8f09d8\",\"serverIndexCode\":\"20007fdf-33de-4ce5-b922-a13fe89ac9f3\"},\"email\":null,\"education\":null,\"nation\":null,\"jobNo\":null},{\"personId\":\"7ea6cd1e747a47e4b9cf41064e02d6e8\",\"personName\":\"朱建勇\",\"gender\":1,\"orgPath\":\"张家港孚宝仓储/办公室/SHE\",\"orgIndexCode\":\"2b60a5bd-e45a-4a97-b9ec-2a456fe9bbc9\",\"orgName\":\"SHE\",\"certificateType\":111,\"certificateNo\":null,\"fingerPrint\":[],\"updateTime\":\"2020-03-16T15:50:34.938+08:00\",\"birthday\":null,\"phoneNo\":\"13405618363\",\"address\":null,\"personPhoto\":{\"picUri\":\"/pic?9ddf48i9f-e*17f4958a6185m7ep=t0i0d*=*1pdi=*1sdi=*1bfia96a7b1a9a724--a9189c-d60sbd12z9fdi=4=\",\"serverIndexCode\":\"20007fdf-33de-4ce5-b922-a13fe89ac9f3\"},\"email\":null,\"education\":null,\"nation\":null,\"jobNo\":null},{\"personId\":\"94a3e4b1c4824006bb5919efdde94435\",\"personName\":\"张登衡\",\"gender\":1,\"orgPath\":\"张家港孚宝仓储/办公室/SHE\",\"orgIndexCode\":\"2b60a5bd-e45a-4a97-b9ec-2a456fe9bbc9\",\"orgName\":\"SHE\",\"certificateType\":111,\"certificateNo\":null,\"fingerPrint\":[],\"updateTime\":\"2020-03-16T15:50:34.944+08:00\",\"birthday\":null,\"phoneNo\":\"18921998661\",\"address\":null,\"personPhoto\":{\"picUri\":\"/pic?9ddf28i9f-e*67f4368a6185m7ep=t0i1d*=*1pdi=*1sdi=*1bfia96a7b1a9a724--a9182c-d60sbd18z5fdi=9=\",\"serverIndexCode\":\"20007fdf-33de-4ce5-b922-a13fe89ac9f3\"},\"email\":null,\"education\":null,\"nation\":null,\"jobNo\":null},{\"personId\":\"49fbf8c715d64bb9866145e94482692a\",\"personName\":\"吴亚芳\",\"gender\":2,\"orgPath\":\"张家港孚宝仓储/办公室/总经办\",\"orgIndexCode\":\"32dd1f3b-49b1-4fc7-a615-78a955d1314b\",\"orgName\":\"总经办\",\"certificateType\":111,\"certificateNo\":null,\"fingerPrint\":[],\"updateTime\":\"2020-03-16T15:47:54.098+08:00\",\"birthday\":null,\"phoneNo\":\"13773260334\",\"address\":null,\"personPhoto\":{\"picUri\":\"/pic?6ddf87i9f-e*47f4718a6185m7ep=t1i1d*=*1pdi=*1sdi=*1bfia96a7b1a9a724--a9187c-d96sbd14z6fdi=4=\",\"serverIndexCode\":\"20007fdf-33de-4ce5-b922-a13fe89ac9f3\"},\"email\":null,\"education\":null,\"nation\":null,\"jobNo\":null},{\"personId\":\"b6e1463d654347e9aa2006136b910560\",\"personName\":\"王杨\",\"gender\":1,\"orgPath\":\"张家港孚宝仓储/苏城孚宝\",\"orgIndexCode\":\"25b1f0b0-ec4c-462e-b993-acd75f8e04c6\",\"orgName\":\"苏城孚宝\",\"certificateType\":111,\"certificateNo\":null,\"fingerPrint\":[],\"updateTime\":\"2020-01-08T10:16:53.385+08:00\",\"birthday\":null,\"phoneNo\":\"13862226411\",\"address\":null,\"personPhoto\":{\"picUri\":\"/pic?=d4=idf3z91dbs24d-c6819a--427a9a1b7a69aifb1*=ids1*=idp9*=pd*m6i1t=8e745866a-95if7f*e2f09d8\",\"serverIndexCode\":\"20007fdf-33de-4ce5-b922-a13fe89ac9f3\"},\"email\":null,\"education\":null,\"nation\":null,\"jobNo\":null},{\"personId\":\"5ddbdc996a8e47a4820dd9f98f044405\",\"personName\":\"何启麟\",\"gender\":1,\"orgPath\":\"张家港孚宝仓储/苏城孚宝\",\"orgIndexCode\":\"25b1f0b0-ec4c-462e-b993-acd75f8e04c6\",\"orgName\":\"苏城孚宝\",\"certificateType\":111,\"certificateNo\":null,\"fingerPrint\":[],\"updateTime\":\"2020-01-08T10:17:14.682+08:00\",\"birthday\":null,\"phoneNo\":\"13776273678\",\"address\":null,\"personPhoto\":{\"picUri\":\"/pic?=d8=idf1z91dbs02d-c1819a--427a9a1b7a69aifb1*=ids1*=idp7*=pd*m9i1t=8e745896a-33if7f*e6f89d8\",\"serverIndexCode\":\"20007fdf-33de-4ce5-b922-a13fe89ac9f3\"},\"email\":null,\"education\":null,\"nation\":null,\"jobNo\":null}]}}";
		
		
		return result;
	}
	
	
}
