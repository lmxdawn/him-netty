package com.lmxdawn.him.api.dao.user;

import com.lmxdawn.him.common.entity.user.UserFriendMsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFriendMsgDao {
    
    /**
     * 根据最后一次获取的消息ID获取离线消息列表
     *
     * @return
     */
    List<UserFriendMsg> listByUidAndToUid(@Param(value = "uid") Long uid,
                                          @Param(value = "toUid") Long toUid,
                                          @Param(value = "offset") Integer offset,
                                          @Param(value = "limit") Integer limit);
    
    /**
     * 插入
     *
     * @return
     */
    boolean insertUserFriendMsg(UserFriendMsg userFriendMsg);
    
}
