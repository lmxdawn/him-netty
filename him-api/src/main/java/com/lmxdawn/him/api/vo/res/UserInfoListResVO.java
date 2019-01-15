package com.lmxdawn.him.api.vo.res;

import com.lmxdawn.him.common.vo.res.BaseResponseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户列表信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoListResVO extends BaseResponseVO {

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

}
