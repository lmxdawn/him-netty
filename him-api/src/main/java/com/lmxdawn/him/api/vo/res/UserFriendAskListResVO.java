package com.lmxdawn.him.api.vo.res;

import lombok.Data;

import java.util.Date;

/**
 * 用户朋友列表信息
 */
@Data
public class UserFriendAskListResVO {
    
    /**
     * 自增ID
     */
    private Long id;
    
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 朋友的用户id
     */
    private Long friendUid;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态（0：未确认过，1：已确认, 2: 已拒绝）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

    // 用户信息
    private UserInfoListResVO user;

}
