package com.lmxdawn.him.common.res;

import lombok.Data;

/**
 * 返回结果类
 * @param <T>
 */
@Data
public class BaseResponse<T> {

    private Integer code;

    private String message;

    private T data;
}
