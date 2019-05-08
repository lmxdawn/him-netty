package com.lmxdawn.him.api.enums;

import lombok.Getter;

/**
 * 消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）
 */
@Getter
public enum WSMsgTypeEnum {
    
    TEXT(0),
    IMAGE(0),
    FILE(0),
    VOICE(0),
    VIDEO(0),
    ;
    
    private Integer type;
    
    WSMsgTypeEnum(Integer type) {
        this.type = type;
    }
    
}
