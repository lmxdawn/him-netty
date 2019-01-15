package com.lmxdawn.him.common.utils;

import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.vo.res.BaseResponseVO;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果的操作类
 */
public class ResultVOUtils {

    /**
     * 成功时返回
     * @param data 返回的data对象
     * @return {@link BaseResponseVO}
     */
    public static BaseResponseVO success(Object data) {
        BaseResponseVO<Object> baseResponseVO = new BaseResponseVO<>();
        baseResponseVO.setCode(0);
        baseResponseVO.setMessage("success");
        baseResponseVO.setData(data);
        return baseResponseVO;
    }

    /**
     * 成功时返回
     * @return {@link BaseResponseVO}
     */
    public static BaseResponseVO success() {
        Map data = new HashMap();
        return success(data);
    }

    /**
     * 错误时返回
     * @param code 错误码
     * @param message 错误信息
     * @return {@link BaseResponseVO}
     */
    public static BaseResponseVO error(Integer code, String message) {
        BaseResponseVO<Object> baseResponseVO = new BaseResponseVO<>();
        baseResponseVO.setCode(code);
        baseResponseVO.setMessage(message);
        Map data = new HashMap();
        baseResponseVO.setData(data);
        return baseResponseVO;
    }

    /**
     * 错误时返回
     * @param resultEnum 错误枚举类
     * @return {@link BaseResponseVO}
     */
    public static BaseResponseVO error(ResultEnum resultEnum) {
        return error(resultEnum.getCode(), resultEnum.getMessage());
    }

    /**
     * 错误时返回
     * @param resultEnum 错误枚举类
     * @param message 错误的信息
     * @return {@link BaseResponseVO}
     */
    public static BaseResponseVO error(ResultEnum resultEnum, String message) {
        return error(resultEnum.getCode(), message);
    }

}
