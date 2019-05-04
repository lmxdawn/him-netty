package com.lmxdawn.him.api.service.user;

import com.lmxdawn.him.common.entity.user.UserFriendMsg;

import java.util.List;

public interface UserFriendMsgService {

    List<UserFriendMsg> listByUid(Long uid, Integer offset, Integer limit);

    boolean insertUserFriendMsg(UserFriendMsg userFriendMsg);

}
