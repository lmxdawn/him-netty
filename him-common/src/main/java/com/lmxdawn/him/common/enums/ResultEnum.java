package com.lmxdawn.him.common.enums;

import lombok.Getter;

/**
 * 返回结果的枚举类
 */
@Getter
public enum ResultEnum {
    
    NOT_NETWORK(-1, "系统繁忙，请稍后再试~"),
    SUCCESS(0, "success"),
    LOGIN_VERIFY_FALL(1, "登录失效~"),
    PARAM_VERIFY_FALL(2, "参数验证错误~"),
    AUTH_FAILED(3, "权限验证失败~"),
    DATA_NOT(4, "没有相关数据~"),
    DATA_CHANGE(5, "数据没有任何更改~"),
    DATA_REPEAT(6, "数据已存在~"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
