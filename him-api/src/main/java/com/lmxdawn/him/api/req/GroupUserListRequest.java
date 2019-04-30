package com.lmxdawn.him.api.req;

import com.lmxdawn.him.common.req.BaseLimitRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询群里的用户列表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupUserListRequest extends BaseLimitRequest {

    /**
     * 群ID
     */
    private Long groupId;

}
