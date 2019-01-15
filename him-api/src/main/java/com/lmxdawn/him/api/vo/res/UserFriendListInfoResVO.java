package com.lmxdawn.him.api.vo.res;

import com.lmxdawn.him.common.vo.res.BaseResponseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户朋友列表信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserFriendListInfoResVO extends BaseResponseVO {

    /**
     * 备注
     */
    private String remark;

}
