package com.lmxdawn.him.api.vo.req;

import com.lmxdawn.him.common.vo.req.BaseReqVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 删除好友
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserFriendMsgClearMsgCountReqVO extends BaseReqVO {
    
    /**
     * 消息接收方
     */
    @NotNull(message = "参数错误~")
    @Min(value = 1, message = "参数错误~")
    private Long receiverUid;

}
