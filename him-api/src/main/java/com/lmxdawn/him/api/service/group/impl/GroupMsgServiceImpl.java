package com.lmxdawn.him.api.service.group.impl;

import com.lmxdawn.him.api.dao.group.GroupMsgDao;
import com.lmxdawn.him.api.service.group.GroupMsgService;
import com.lmxdawn.him.api.vo.res.GroupMsgListResVO;
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
    public List<GroupMsgListResVO> listByLastMsgId(Long groupId, Long lastMsgId) {

        List<GroupMsgListResVO> groupMsgListResVOList = groupMsgDao.listByLastMsgId(groupId, lastMsgId).stream()
                .map(v -> {
                    GroupMsgListResVO groupMsgListResVO = new GroupMsgListResVO();
                    BeanUtils.copyProperties(v, groupMsgListResVO);
                    return groupMsgListResVO;
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
