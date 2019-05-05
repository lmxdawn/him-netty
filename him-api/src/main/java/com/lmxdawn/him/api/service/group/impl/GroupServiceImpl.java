package com.lmxdawn.him.api.service.group.impl;

import com.lmxdawn.him.api.dao.group.GroupDao;
import com.lmxdawn.him.api.service.group.GroupService;
import com.lmxdawn.him.common.entity.group.Group;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupDao groupDao;

    @Override
    public Group findByGroupId(Long groupId) {
        return groupDao.findByGroupId(groupId);
    }

    @Override
    public Map<Long, Group> listByGroupIdIn(List<Long> groupIds) {
        Map<Long, Group> groupMaps = new HashMap<>();
        List<Group> groupList = groupDao.listByGroupIdIn(groupIds);
        groupList.forEach(v -> {
            groupMaps.put(v.getGroupId(), v);
        });
        return groupMaps;
    }

    @Override
    public boolean insertGroup(Group group) {
        group.setCreateTime(new Date());
        group.setModifiedTime(new Date());
        return groupDao.insertGroup(group);
    }

    @Override
    public boolean updateGroup(Group group) {
        group.setModifiedTime(new Date());
        return groupDao.updateGroup(group);
    }

    @Override
    public boolean incMemberNumByGroupId(Long groupId, Integer memberNum) {
        return groupDao.incMemberNumByGroupId(groupId, memberNum);
    }

    @Override
    public boolean decMemberNumByGroupId(Long groupId) {
        return groupDao.decMemberNumByGroupId(groupId);
    }

    @Override
    public boolean deleteByGroupId(Long groupId) {
        return groupDao.deleteByGroupId(groupId);
    }
}
