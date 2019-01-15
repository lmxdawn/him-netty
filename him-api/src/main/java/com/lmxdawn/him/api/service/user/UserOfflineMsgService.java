package com.lmxdawn.him.api.service.user;

import com.lmxdawn.him.common.entity.user.UserOfflineMsg;

import java.util.List;

public interface UserOfflineMsgService {

    List<UserOfflineMsg> listByLastMsgId(Long receiverUid, Long lastMsgId);

    boolean insertUserOfflineMsg(UserOfflineMsg userOfflineMsg);

}
