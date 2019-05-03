package com.lmxdawn.him.api.service.group;

import com.lmxdawn.him.api.vo.res.GroupMsgListResVO;
import com.lmxdawn.him.common.entity.group.GroupMsg;

import java.util.List;

public interface GroupMsgService {

    List<GroupMsgListResVO> listByLastMsgId(Long groupId, Long lastMsgId);

    boolean insertGroupMsg(GroupMsg groupMsg);

}
