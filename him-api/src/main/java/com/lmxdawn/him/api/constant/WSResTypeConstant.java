package com.lmxdawn.him.api.constant;
        
        import lombok.Getter;

/**
 * 返回类型的常量（-2: 登录异常, -1: 异地登录, 0: 心跳，1: 好友消息，2: 群消息，3: 好友请求消息，4: 好友同意消息，5: 加入群消息）
 */
@Getter
public class WSResTypeConstant {
    
    public static final int LOGIN_OUT = -2;
    public static final int WS_OUT = -1;
    public static final int PING = 0;
    public static final int FRIEND = 1;
    public static final int GROUP = 2;
    public static final int FRIEND_ASK = 3;
    public static final int FRIEND_ACK = 4;
    public static final int JOIN_GROUP = 5;
}

