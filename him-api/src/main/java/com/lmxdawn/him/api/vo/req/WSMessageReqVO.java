package com.lmxdawn.him.api.vo.req;

import lombok.Data;

/**
 * websocket 消息实体
 */
@Data
public class WSMessageReqVO {

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
