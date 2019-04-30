package com.lmxdawn.him.api.res;

import lombok.Data;

/**
 * 群列表信息
 */
@Data
public class GroupInfoListResponse {

    /**
     * 群ID
     */
    private Long groupId;
    /**
     * 群昵称
     */
    private String name;
    /**
     * 成员数量
     */
    private Integer memberNum;

}
