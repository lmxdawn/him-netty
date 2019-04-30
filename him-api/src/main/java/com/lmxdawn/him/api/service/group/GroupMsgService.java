package com.lmxdawn.him.api.service.group;

import com.lmxdawn.him.api.res.GroupMsgListResponse;
import com.lmxdawn.him.common.entity.group.GroupMsg;

import java.util.List;

public interface GroupMsgService {

    List<GroupMsgListResponse> listByLastMsgId(Long groupId, Long lastMsgId);

    boolean insertGroupMsg(GroupMsg groupMsg);

}
