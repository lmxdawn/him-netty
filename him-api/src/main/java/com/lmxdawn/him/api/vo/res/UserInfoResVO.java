package com.lmxdawn.him.api.vo.res;

import com.lmxdawn.him.common.vo.res.BaseResponseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoResVO extends BaseResponseVO {

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
     * 创建时间
     */
    private Date createTime;

}
