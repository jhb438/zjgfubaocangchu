package com.basic.zjgfbcc.common.JJWT;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import com.basic.zjgfbcc.service.RedisService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
  * JwtHelper
  * @ClassName: JwtHelper 
  * @Description: JwtHelper
  * @author keeny
  * @date 2018年10月8日 下午1:41:49 
  *
 */
public class JwtHelper {
    final   private static String clientId="098f6bcd4621d373cade4e832627b4f6";
    final   private static String  base64Secret="MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
    final   private static String name="restapiuser";
    //final   private static long expiresSecond=1000*60*60*12;
    /**
     * 解析jwt
     * @Title: parseJWT 
     * @Description: 解析jwt
     * @param @param jsonWebToken
     * @param @return    设定文件 
     * @return Claims    返回类型 
     * @throws
     */
    public static Claims parseJWT(String jsonWebToken){
        try
        {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    /**
     * 构建jwt
     * @Title: createJWT 
     * @Description: 构建jwt
     * @param @param uuid
     * @param @param phone
     * @param @param time
     * @param @return    设定文件 
     * @return String    返回类型 
     * @throws
     */
    public static String createJWT(String uuid,String phone,long time )
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret );
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("uuid", uuid)
                .claim("phone", phone)
                .setIssuer(clientId)
                .setAudience(name)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (time >= 0) {
            long expMillis = nowMillis + time;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }


    /**
      * 解析jwt
     * @Title: checkToken 
     * @Description: 解析jwt
     * @param @param jsonWebToken
     * @param @param redisService
     * @param @return    设定文件 
     * @return boolean    返回类型 
     * @throws
     */
    public static boolean checkToken(String jsonWebToken,RedisService redisService){
        try
        {
            jsonWebToken=jsonWebToken.substring(5);
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();

            String uuid = claims.get("uuid").toString();
            if (redisService.exists(uuid)) {
              return  true;
            } else {
                return  false;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return false;
        }
    }

    /**
     * 依据头信息解析uuid
     * @param request
     * @param redisService
     * @return
     */
    public static String  analysisToken(HttpServletRequest request, RedisService redisService){
        try
        {
            final String authHeader = request.getHeader("X-Auth-Token");
            String jsonWebToken=authHeader.substring(5);
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();

            String uuid = claims.get("uuid").toString();
            if (redisService.exists(uuid)) {
                return  uuid;
            } else {
                return  null;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * 依据Header头 获取Token信息中的参数
     * 例如：uuid   phone 等
     * @param request
     * @param falg
     * @return
     */
    public static String  getTokenParameterByHeader(HttpServletRequest request,String flag){
        try
        {
            final String authHeader = request.getHeader("X-Auth-Token");
            if(authHeader==null){
                return  null ;
            }
            String jsonWebToken=authHeader.substring(7);
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            if(claims!=null){
               return claims.get(flag).toString();
            }
            return  null;
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * 依据Header头 获取Token信息中的参数
     * 例如：uuid   phone 等
     * @param authHeader
     * @param flag
     * @return
     */
    public static String  getTokenParameterByHeader(String  authHeader, String flag){
        try
        {
            if(authHeader==null){
                return  null ;
            }
            String jsonWebToken=authHeader.substring(5);
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            if(claims!=null){
                return claims.get(flag).toString();
            }
            return  null;
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }


}
