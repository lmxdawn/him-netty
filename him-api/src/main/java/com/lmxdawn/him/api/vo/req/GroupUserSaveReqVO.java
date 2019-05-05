package com.lmxdawn.him.api.vo.req;

import com.lmxdawn.him.common.vo.req.BaseLimitReqVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 查询群里的用户列表
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupUserSaveReqVO extends BaseLimitReqVO {

    /**
     * 群ID
     */
    @NotNull(message = "参数错误~")
    private Long groupId;

    /**.
     * 说明
     */
    @Length(min = 1, max = 255, message = "参数错误~")
    private String remark;

}
