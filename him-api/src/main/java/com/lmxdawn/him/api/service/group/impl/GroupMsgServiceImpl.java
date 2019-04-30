package com.lmxdawn.him.api.service.group.impl;

import com.lmxdawn.him.api.dao.group.GroupMsgDao;
import com.lmxdawn.him.api.service.group.GroupMsgService;
import com.lmxdawn.him.api.res.GroupMsgListResponse;
import com.lmxdawn.him.common.entity.group.GroupMsg;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMsgServiceImpl implements GroupMsgService {

    @Resource
    private GroupMsgDao groupMsgDao;

    @Override
    public List<GroupMsgListResponse> listByLastMsgId(Long groupId, Long lastMsgId) {

        List<GroupMsgListResponse> groupMsgListResVOList = groupMsgDao.listByLastMsgId(groupId, lastMsgId).stream()
                .map(v -> {
                    GroupMsgListResponse groupMsgListResponse = new GroupMsgListResponse();
                    BeanUtils.copyProperties(v, groupMsgListResponse);
                    return groupMsgListResponse;
                })
                .collect(Collectors.toList());

        return groupMsgListResVOList;
    }

    @Override
    public boolean insertGroupMsg(GroupMsg groupMsg) {
        groupMsg.setCreateTime(new Date());
        groupMsg.setModifiedTime(new Date());
        return groupMsgDao.insertGroupMsg(groupMsg);
    }
}
