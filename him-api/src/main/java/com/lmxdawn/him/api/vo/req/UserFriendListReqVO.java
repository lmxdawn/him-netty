package com.lmxdawn.him.api.vo.req;

import com.lmxdawn.him.common.vo.req.BaseLimitReqVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询朋友列表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserFriendListReqVO extends BaseLimitReqVO {

    /**
     * 用户的ID
     */
    private Long uid;

}
