package com.lmxdawn.him.api.vo.res;

import lombok.Data;

import java.util.Date;

/**
 * 查询群里的用户列表
 */
@Data
public class GroupMsgListResVO {

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
     * 消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）
     */
    private Integer msgType;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date modifiedTime;

    /**
     * 用户信息
     */
    private UserInfoListResVO user;

}
