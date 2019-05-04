package com.lmxdawn.him.api.vo.res;

import com.lmxdawn.him.common.entity.user.UserProfile;
import lombok.Data;

import java.util.Date;

/**
 * 用户额外信息
 */
@Data
public class UserProfileInfoResVO {
    
    /**
     * 好友请求的数量
     */
    private Integer friendAskCount;
    /**
     * 好友数量
     */
    private Integer friendCount;

}
