package com.lmxdawn.him.api.vo.req;

import com.lmxdawn.him.common.vo.req.BaseLimitRequestVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询群里的用户列表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupUserListReqVO extends BaseLimitRequestVO {

    /**
     * 群ID
     */
    private Long groupId;

}
