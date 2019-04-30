package com.lmxdawn.him.api.req;

import com.lmxdawn.him.common.req.BaseLimitRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取用户的群列表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserGroupListRequest extends BaseLimitRequest {

    /**
     * 用户的ID
     */
    private Long uid;

}
