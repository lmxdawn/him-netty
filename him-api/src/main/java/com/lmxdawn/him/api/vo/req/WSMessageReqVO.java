package com.lmxdawn.him.api.vo.req;

import lombok.Data;

/**
 * websocket 通信的类
 */
@Data
public class WSMessageReqVO {

    /**
     * 类型
     */
    private Integer type;

    /**
     * 发送者ID
     */
    private Long senderId;

    /**
     * 接收者ID
     */
    private Long receiveId;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 消息内容
     */
    private String msgContent;
    
}
