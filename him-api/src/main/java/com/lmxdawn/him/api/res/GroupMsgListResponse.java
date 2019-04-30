package com.lmxdawn.him.api.res;

import lombok.Data;

import java.util.Date;

/**
 * 群列表信息
 */
@Data
public class GroupMsgListResponse {

    /**
     * 消息ID
     */
    private Long msgId;
    /**
     * 群ID
     */
    private Long groupId;
    /**
     * 发送消息的用户ID
     */
    private Long senderUid;
    /**
     * 消息类型 ( 0 : 文本消息 )
     */
    private Long msgType;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 创建时间
     */
    private Date createTime;

}
