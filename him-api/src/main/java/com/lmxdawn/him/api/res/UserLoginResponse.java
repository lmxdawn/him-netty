package com.lmxdawn.him.api.res;

import com.lmxdawn.him.common.res.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户登录后返回的
 */
@Data
public class UserLoginResponse {
    
    private Long uid;
    
    private String token;
    
}
