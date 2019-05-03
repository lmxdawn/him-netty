package com.lmxdawn.him.common.vo.res;

import lombok.Data;

/**
 * 返回结果类
 * @param <T>
 */
@Data
public class BaseResVO<T> {

    private Integer code;

    private String message;

    private T data;
}
