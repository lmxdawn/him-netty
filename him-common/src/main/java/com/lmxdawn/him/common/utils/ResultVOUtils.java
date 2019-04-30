package com.lmxdawn.him.common.utils;

import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.res.BaseResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果的操作类
 */
public class ResultVOUtils {

    /**
     * 成功时返回
     * @param data 返回的data对象
     * @return {@link BaseResponse}
     */
    public static BaseResponse success(Object data) {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.setCode(0);
        baseResponse.setMessage("success");
        baseResponse.setData(data);
        return baseResponse;
    }

    /**
     * 成功时返回
     * @return {@link BaseResponse}
     */
    public static BaseResponse success() {
        Map data = new HashMap();
        return success(data);
    }

    /**
     * 错误时返回
     * @param code 错误码
     * @param message 错误信息
     * @return {@link BaseResponse}
     */
    public static BaseResponse error(Integer code, String message) {
        BaseResponse<Object> baseResponse = new BaseResponse<>();
        baseResponse.setCode(code);
        baseResponse.setMessage(message);
        Map data = new HashMap();
        baseResponse.setData(data);
        return baseResponse;
    }

    /**
     * 错误时返回
     * @param resultEnum 错误枚举类
     * @return {@link BaseResponse}
     */
    public static BaseResponse error(ResultEnum resultEnum) {
        return error(resultEnum.getCode(), resultEnum.getMessage());
    }

    /**
     * 错误时返回
     * @param resultEnum 错误枚举类
     * @param message 错误的信息
     * @return {@link BaseResponse}
     */
    public static BaseResponse error(ResultEnum resultEnum, String message) {
        return error(resultEnum.getCode(), message);
    }

}
