package com.lmxdawn.him.api.service.group.impl;

import com.lmxdawn.him.api.dao.group.GroupDao;
import com.lmxdawn.him.api.service.group.GroupService;
import com.lmxdawn.him.common.entity.group.Group;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupDao groupDao;

    @Override
    public Group findByGroupId(Long groupId) {
        return groupDao.findByGroupId(groupId);
    }

    @Override
    public List<Group> listByGroupIdIn(List<Long> groupIds) {
        return groupDao.listByGroupIdIn(groupIds);
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
    public boolean deleteByGroupId(Long groupId) {
        return groupDao.deleteByGroupId(groupId);
    }
}
