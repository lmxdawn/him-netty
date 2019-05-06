package com.lmxdawn.him.api.vo.req;

import com.lmxdawn.him.common.vo.req.BaseLimitReqVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * 查询群里的用户列表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupSaveReqVO extends BaseLimitReqVO {

    /**
     * 群ID
     */
    private Long groupId;

    /**
     * 群昵称
     */
    @NotEmpty(message = "参数错误~")
    @Length(min = 1, max = 255, message = "参数错误~")
    private String name;

    /**.
     * 群头像
     */
    @Length(min = 0, max = 255, message = "参数错误~")
    private String avatar;

    /**.
     * 说明
     */
    @Length(min = 0, max = 255, message = "参数错误~")
    private String remark;

}
