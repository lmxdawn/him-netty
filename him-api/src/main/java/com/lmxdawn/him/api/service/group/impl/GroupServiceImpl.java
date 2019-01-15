package com.lmxdawn.him.api.service.group.impl;

import com.lmxdawn.him.api.dao.group.GroupDao;
import com.lmxdawn.him.api.service.group.GroupService;
import com.lmxdawn.him.api.vo.res.GroupInfoListResVO;
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
    public List<GroupInfoListResVO> listByGroupIdIn(List<Long> groupIds) {

        List<GroupInfoListResVO> groupInfoListResVOList = groupDao.listByGroupIdIn(groupIds).stream()
                .map(v -> {
                    GroupInfoListResVO groupInfoListResVO = new GroupInfoListResVO();
                    BeanUtils.copyProperties(v, groupInfoListResVO);
                    return groupInfoListResVO;
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
