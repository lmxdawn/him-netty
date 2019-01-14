package com.lmxdawn.him.common.vo;

import lombok.Data;

/**
 * 返回结果类
 * @param <T>
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;
}
