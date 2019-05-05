package com.lmxdawn.him.api.vo.res;

import com.lmxdawn.him.common.entity.group.Group;
import lombok.Data;

import java.util.Date;


/**
 * 用户朋友列表信息
 */
@Data
public class GroupUserListResVO {
    
    /**
     * 自增ID
     */
    private Long id;
    
    /**
     * 群ID
     */
    private Long groupId;

    /**
     * 用户id
     */
    private Long uid;
    /**
     * 群内名称
     */
    private String remark;
    /**
     * 最后一次确认的消息ID
     */
    private Long lastAckMsgId;
    /**
     * 最后一次的消息内容
     */
    private String lastMsgContent;
    /**
     * 最后一次的消息的时间
     */
    private Date lastMsgTime;
    /**
     * 未读消息数量
     */
    private Integer unMsgCount;
    /**
     * 等级（0：普通成员，1：管理员，2：群主）
     */
    private Integer rank;

    /**
     * 群信息
     */
    private Group group;

}
