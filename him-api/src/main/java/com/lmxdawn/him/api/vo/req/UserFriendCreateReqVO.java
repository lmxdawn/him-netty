package com.lmxdawn.him.api.vo.req;

import com.lmxdawn.him.common.vo.req.BaseRequestVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加好友
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserFriendCreateReqVO extends BaseRequestVO {

    /**
     * 朋友的ID
     */
    private Long friendUid;

    /**
     * 朋友的备注
     */
    private String remark;

}
