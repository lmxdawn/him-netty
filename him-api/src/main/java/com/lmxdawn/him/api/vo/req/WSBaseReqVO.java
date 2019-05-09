package com.lmxdawn.him.api.vo.req;

import lombok.Data;

/**
 * websocket 通信的类
 */
@Data
public class WSBaseReqVO {

    /**
     * 类型
     */
    private Integer type;

    /**
     * 消息实体
     */
    private WSMessageReqVO message;

    /**
     * 发送者用户信息
     */
    private WSUserReqVO user;

}
