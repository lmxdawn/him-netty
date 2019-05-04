package com.lmxdawn.him.api.vo.req;

import com.lmxdawn.him.common.vo.req.BaseReqVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 发起好友请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserFriendAskSaveReqVO extends BaseReqVO {

    /**
     * 朋友的ID
     */
    @NotNull(message = "参数错误！")
    private Long friendUid;

    /**
     * 备注信息
     */
    private String remark;

}
