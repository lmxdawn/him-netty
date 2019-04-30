package com.lmxdawn.him.api.res;

import lombok.Data;

/**
 * 用户朋友列表信息
 */
@Data
public class UserFriendListInfoResponse {

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
