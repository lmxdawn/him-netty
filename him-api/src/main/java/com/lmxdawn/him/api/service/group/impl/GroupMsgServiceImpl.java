package com.lmxdawn.him.api.service.group.impl;

import com.lmxdawn.him.api.dao.group.GroupMsgDao;
import com.lmxdawn.him.api.service.group.GroupMsgService;
import com.lmxdawn.him.api.utils.PageUtils;
import com.lmxdawn.him.common.entity.group.GroupMsg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GroupMsgServiceImpl implements GroupMsgService {

    @Resource
    private GroupMsgDao groupMsgDao;

    @Override
    public List<GroupMsg> listByGroupIdAndCreateTime(Long groupId, Date createTime, Integer page, Integer limit) {
        Integer offset = PageUtils.createOffset(page, limit);
        return groupMsgDao.listByGroupIdAndCreateTime(groupId, createTime, offset, limit);
    }

    @Override
    public boolean insertGroupMsg(GroupMsg groupMsg) {
        groupMsg.setCreateTime(new Date());
        groupMsg.setModifiedTime(new Date());
        return groupMsgDao.insertGroupMsg(groupMsg);
    }
}
