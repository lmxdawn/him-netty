package com.lmxdawn.him.api.vo.res;

import com.lmxdawn.him.common.entity.user.UserProfile;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 */
@Data
public class UserInfoResVO {

    /**
     * 用户id
     */
    private Long uid;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 个性签名
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    
    private UserProfileInfoResVO profile;

}
