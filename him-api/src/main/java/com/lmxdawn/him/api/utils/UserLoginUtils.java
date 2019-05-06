package com.lmxdawn.him.api.utils;

import com.lmxdawn.him.api.dto.UserLoginDTO;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录工具类
 */
public class UserLoginUtils {

  /**
   * 创建 SID
   * @param uid
   * @return
   */
  public static String createSid(Long uid, Long ttl) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("uid", uid);
    return JwtUtils.createToken(claims, 86400L); // 一天后过期
  }

  /**
   * 创建 SID 没有过期时间
   * @param uid
   * @return
   */
  public static String createSid(Long uid) {
    return createSid(uid, 0L);
  }

  /**
   * 验证用户是否登录
   * @param request
   * @return
   */
  public static UserLoginDTO check(HttpServletRequest request) {
    
    String sUid = request.getParameter("UID");
    String token = request.getParameter("SID");
    if (sUid == null || sUid.isEmpty() || token == null || token.isEmpty()) {
      Cookie[] cookies = request.getCookies();
      if (cookies == null) {
        return null;
      }
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("UID")) {
          sUid = cookie.getValue();
        } else if (cookie.getName().equals("SID")) {
          token = cookie.getValue();
        }
      }
    }
    
    if (sUid == null || token == null) {
      return null;
    }
    Long uid = null;
    try {
      
      uid = Long.valueOf(sUid);
      // 验证 token
      Claims claims = JwtUtils.parse(token);
      if (claims == null) {
        return null;
      }
      Long jwtUid = Long.valueOf(claims.get("uid").toString());
      if (uid.compareTo(jwtUid) != 0) {
        return null;
      }
      
    } catch (Exception e) {
      return null;
    }
    
    UserLoginDTO userLoginDTO = new UserLoginDTO();
    
    userLoginDTO.setUid(uid);
    return userLoginDTO;
  }
  
}
