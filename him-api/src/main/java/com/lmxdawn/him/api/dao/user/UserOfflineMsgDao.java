package com.lmxdawn.him.api.dao.user;

import com.lmxdawn.him.common.entity.user.UserOfflineMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserOfflineMsgDao {

    /**
     * 根据最后一次获取的消息ID获取离线消息列表
     * @return
     */
    List<UserOfflineMsg> listByLastMsgId(@Param(value = "receiverUid") Long receiverUid,@Param(value = "lastMsgId") Long lastMsgId);

    /**
     * 插入
     * @return
     */
    boolean insertUserOfflineMsg(UserOfflineMsg userOfflineMsg);

}
