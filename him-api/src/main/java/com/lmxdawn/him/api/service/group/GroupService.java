package com.lmxdawn.him.api.service.group;

import com.lmxdawn.him.common.entity.group.Group;

import java.util.List;
import java.util.Map;

public interface GroupService {

    Group findByGroupId(Long groupId);

    Map<Long, Group> listByGroupIdIn(List<Long> groupIds);

    boolean insertGroup(Group group);

    boolean updateGroup(Group group);

    boolean incMemberNumByGroupId(Long groupId, Integer memberNum);

    boolean decMemberNumByGroupId(Long groupId);

    boolean deleteByGroupId(Long groupId);

}
