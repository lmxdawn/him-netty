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
public class UserFriendMsgSaveReqVO extends BaseReqVO {
    
    /**
     * 消息接收方
     */
    @NotNull(message = "参数错误~")
    @Min(value = 1, message = "参数错误~")
    private Long receiverUid;
    /**
     * 消息类型（0：普通文字消息，1：图片消息，2：文件消息，3：语音消息，4：视频消息）
     */
    @NotNull(message = "参数错误~")
    @Min(value = 0, message = "参数错误~")
    @Max(value = 4, message = "参数错误~")
    private Integer msgType;
    /**
     * 消息内容
     */
    @NotNull(message = "参数错误~")
    @Length(min = 1, max = 255, message = "参数错误~")
    private String msgContent;

}
