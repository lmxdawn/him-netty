package com.lmxdawn.him.api.req;

import com.lmxdawn.him.common.req.BaseLimitRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询朋友列表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserFriendListRequest extends BaseLimitRequest {

    /**
     * 用户的ID
     */
    private Long uid;

}
