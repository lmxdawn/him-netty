package com.lmxdawn.him.api.vo.res;

import lombok.Data;

/**
 * 用户列表信息
 */
@Data
public class UserInfoListResVO {

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
