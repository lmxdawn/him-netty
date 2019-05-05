package com.lmxdawn.him.api.utils;

import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

/**
 * 群用户相关
 */
public class GroupUserUtils {

  /**
   * 创建群的验证码
   * @return
   */
  public static String createCheckCode(Long groupId) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("gid", groupId);
    return JwtUtils.createToken(claims, 7 * 86400L); // 7天后过期
  }

  /**
   * 验证
   * @param groupId
   * @param code
   * @return
   */
  public static boolean checkCode(Long groupId, String code) {

    if (null == code || "".equals(code)) {
      return false;
    }

    try {
      // 验证 token
      Claims claims = JwtUtils.parse(code);
      if (claims == null) {
        return false;
      }
      Long jwtUid = Long.valueOf(claims.get("gid").toString());
      if (groupId.compareTo(jwtUid) != 0) {
        return false;
      }
      
    } catch (Exception e) {
      return false;
    }

    return true;
  }
  
}
