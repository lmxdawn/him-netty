package com.lmxdawn.him.common.utils;

import com.lmxdawn.api.admin.enums.ResultEnum;
import com.lmxdawn.api.admin.vo.ResultVO;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果的操作类
 */
public class ResultVOUtils {

    /**
     * 成功时返回
     * @param data 返回的data对象
     * @return {@link ResultVO}
     */
    public static ResultVO success(Object data) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMessage("success");
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 成功时返回
     * @return {@link ResultVO}
     */
    public static ResultVO success() {
        Map data = new HashMap();
        return success(data);
    }

    /**
     * 错误时返回
     * @param code 错误码
     * @param message 错误信息
     * @return {@link ResultVO}
     */
    public static ResultVO error(Integer code, String message) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        Map data = new HashMap();
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 错误时返回
     * @param resultEnum 错误枚举类
     * @return {@link ResultVO}
     */
    public static ResultVO error(ResultEnum resultEnum) {
        return error(resultEnum.getCode(), resultEnum.getMessage());
    }

    /**
     * 错误时返回
     * @param resultEnum 错误枚举类
     * @param message 错误的信息
     * @return {@link ResultVO}
     */
    public static ResultVO error(ResultEnum resultEnum, String message) {
        return error(resultEnum.getCode(), message);
    }

}
