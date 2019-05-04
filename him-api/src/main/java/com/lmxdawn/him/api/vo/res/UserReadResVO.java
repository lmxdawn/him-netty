package com.lmxdawn.him.api.vo.res;

import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 */
@Data
public class UserReadResVO {

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
    /**
     * 个性签名
     */
    private String remark;

}
