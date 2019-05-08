package com.lmxdawn.him.api.enums;

import lombok.Getter;

/**
 * 请求类型的枚举（0: 心跳，1: 好友消息，2: 群消息，3: 好友请求消息，4: 好友同意消息，5: 加入群消息）
 */
@Getter
public enum WSReqTypeEnum {
    
    PING(0),
    FRIEND(1),
    GROUP(2),
    FRIEND_ASK(3),
    FRIEND_ACK(4),
    JOIN_GROUP(5),
    ;
    
    private Integer type;
    
    WSReqTypeEnum(Integer type) {
        this.type = type;
    }
    
}
