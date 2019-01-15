package com.lmxdawn.him.api.service.group;

import com.lmxdawn.him.api.vo.res.GroupInfoListResVO;
import com.lmxdawn.him.api.vo.res.UserInfoListResVO;
import com.lmxdawn.him.common.entity.group.Group;
import com.lmxdawn.him.common.entity.user.User;

import java.util.List;

public interface GroupService {

    Group findByGroupId(Long groupId);

    List<GroupInfoListResVO> listByGroupIdIn(List<Long> groupIds);

    boolean insertGroup(Group group);

    boolean updateGroup(Group group);

    boolean deleteByGroupId(Long groupId);

}
