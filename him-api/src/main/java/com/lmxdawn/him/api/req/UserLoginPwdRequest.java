package com.lmxdawn.him.api.req;

import com.lmxdawn.him.common.req.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 密码登录
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginPwdRequest extends BaseRequest {
    
    @NotNull(message = "用户ID不能为空")
    private Long uid;
    
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String pwd;
    
}
