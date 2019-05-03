package com.lmxdawn.him.api.utils;

import org.springframework.util.DigestUtils;

/**
 * 密码相关的工具类
 */
public class PasswordUtils {

    public static String md52md5(String pwd) {
        return DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(pwd.getBytes()).getBytes());
    }

}
