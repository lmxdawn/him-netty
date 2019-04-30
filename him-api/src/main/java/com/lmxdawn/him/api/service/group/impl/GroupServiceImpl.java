package com.lmxdawn.him.api.service.group.impl;

import com.lmxdawn.him.api.dao.group.GroupDao;
import com.lmxdawn.him.api.service.group.GroupService;
import com.lmxdawn.him.api.res.GroupInfoListResponse;
import com.lmxdawn.him.common.entity.group.Group;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    @Resource
    private GroupDao groupDao;

    @Override
    public Group findByGroupId(Long groupId) {
        return groupDao.findByGroupId(groupId);
    }

    @Override
    public List<GroupInfoListResponse> listByGroupIdIn(List<Long> groupIds) {

        List<GroupInfoListResponse> groupInfoListResVOList = groupDao.listByGroupIdIn(groupIds).stream()
                .map(v -> {
                    GroupInfoListResponse groupInfoListResponse = new GroupInfoListResponse();
                    BeanUtils.copyProperties(v, groupInfoListResponse);
                    return groupInfoListResponse;
                })
                .collect(Collectors.toList());

        return groupInfoListResVOList;
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
