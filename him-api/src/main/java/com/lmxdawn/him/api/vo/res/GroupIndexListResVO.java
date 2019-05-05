package com.lmxdawn.him.api.vo.res;

import lombok.Data;

/**
 * 查询群里的用户列表
 */
@Data
public class GroupIndexListResVO {

    /**
     * 群ID
     */
    private Long groupId;
    /**
     * 说明
     */
    private Long remark;
    /**
     * 等级（0：普通成员，1：管理员，2：群主）
     */
    private Integer rank;

    /**
     * 用户信息
     */
    private UserInfoListResVO user;

}
