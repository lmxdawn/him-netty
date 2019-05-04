package com.lmxdawn.him.api.vo.res;

import lombok.Data;

import java.util.Date;

/**
 * 用户朋友列表信息
 */
@Data
public class UserFriendListInfoResVO {

    /**
     * 用户
     */
    private UserInfoListResVO user;
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 未读消息数量
     */
    private Integer unMsgCount;
    
    /**
     * 最后一次接收的消息
     */
    private String lastMsgContent;
    
    private Date modifiedTime;

}
