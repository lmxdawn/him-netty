package com.lmxdawn.him.api.utils;

import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户朋友相关
 */
public class UserFriendUtils {

  /**
   * 创建群的验证码
   * @return
   */
  public static String createCheckCode(Long uid) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("uid", uid);
    return JwtUtils.createToken(claims, null); // 不过期
  }

  /**
   * 验证
   * @param code
   * @return
   */
  public static Long checkCode(String code) {

    if (null == code || "".equals(code)) {
      return null;
    }

    try {
      // 验证 token
      Claims claims = JwtUtils.parse(code);
      if (claims == null) {
        return null;
      }
      return Long.valueOf(claims.get("uid").toString());
      
    } catch (Exception e) {
      return null;
    }
  }
  
}
