package com.lmxdawn.him.api.vo.res;

import lombok.Data;

/**
 * 用户朋友列表信息
 */
@Data
public class UserFriendListInfoResVO {

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

}
