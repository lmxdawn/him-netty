package com.lmxdawn.him.api.dao.group;

import com.lmxdawn.him.common.entity.group.GroupMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface GroupMsgDao {

    /**
     * 根据最后一次获取的消息ID获取离线消息列表
     *
     * @return
     */
    List<GroupMsg> listByGroupIdAndCreateTime(@Param(value = "groupId") Long groupId,
                                              @Param(value = "createTime") Date createTime,
                                              @Param("offset") Integer offset,
                                              @Param("limit") Integer limit);

    /**
     * 插入
     *
     * @return
     */
    boolean insertGroupMsg(GroupMsg groupMsg);

}
