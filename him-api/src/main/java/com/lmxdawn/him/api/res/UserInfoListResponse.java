package com.lmxdawn.him.api.res;

import lombok.Data;

/**
 * 用户列表信息
 */
@Data
public class UserInfoListResponse {

    /**
     * 用户id
     */
    private Long uid;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 用户头像
     */
    private String avatar;

}
