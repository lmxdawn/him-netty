package com.lmxdawn.him.api.utils;

import com.lmxdawn.him.api.dto.UserLoginDTO;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录工具类
 */
public class UserLoginUtils {
  
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
