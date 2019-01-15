package com.lmxdawn.him.api.vo.res;

import com.lmxdawn.him.common.vo.res.BaseResponseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 群列表信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupInfoListResVO extends BaseResponseVO {

    /**
     * 群ID
     */
    private Long groupId;
    /**
     * 群昵称
     */
    private String name;
    /**
     * 成员数量
     */
    private Integer memberNum;

}
