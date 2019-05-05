package com.lmxdawn.him.api.service.group.impl;

import com.lmxdawn.him.api.dao.group.GroupUserDao;
import com.lmxdawn.him.api.service.group.GroupUserService;
import com.lmxdawn.him.api.utils.PageUtils;
import com.lmxdawn.him.common.entity.group.GroupUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GroupUserServiceImpl implements GroupUserService {

    @Resource
    private GroupUserDao groupUserDao;

    @Override
    public GroupUser findByGroupIdAndUid(Long groupId, Long uid) {
        return groupUserDao.findByGroupIdAndUid(groupId, uid);
    }

    @Override
    public List<GroupUser> listByGroupId(Long groupId, Integer page, Integer limit) {
        Integer offset = PageUtils.createOffset(page, limit);
        return groupUserDao.listByGroupId(groupId, offset, limit);
    }

    @Override
    public List<GroupUser> listByUid(Long uid, Integer page, Integer limit) {
        Integer offset = PageUtils.createOffset(page, limit);
        return groupUserDao.listByUid(uid, offset, limit);
    }

    @Override
    public boolean insertGroupUser(GroupUser groupUser) {
        groupUser.setCreateTime(new Date());
        groupUser.setModifiedTime(new Date());
        return groupUserDao.insertGroupUser(groupUser);
    }

    @Override
    public boolean updateGroupUserByGroupIdAndUid(GroupUser groupUser) {
        groupUser.setModifiedTime(new Date());
        return groupUserDao.updateGroupUserByGroupIdAndUid(groupUser);
    }
    
    @Override
    public boolean clearUnMsgCountByGroupIdAndUid(GroupUser groupUser) {
        groupUser.setModifiedTime(new Date());
        return groupUserDao.clearUnMsgCountByGroupIdAndUid(groupUser);
    }
    
    @Override
    public boolean updateGroupUserByGroupId(GroupUser groupUser) {
        groupUser.setModifiedTime(new Date());
        return groupUserDao.updateGroupUserByGroupId(groupUser);
    }

    @Override
    public boolean deleteByGroupIdAndUid(Long groupId, Long uid) {
        return groupUserDao.deleteByGroupIdAndUid(groupId, uid);
    }

    @Override
    public boolean deleteByGroupId(Long groupId) {
        return groupUserDao.deleteByGroupId(groupId);
    }
}
