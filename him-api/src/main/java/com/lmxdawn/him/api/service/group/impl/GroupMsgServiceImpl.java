package com.lmxdawn.him.api.service.group.impl;

import com.lmxdawn.him.api.dao.group.GroupMsgDao;
import com.lmxdawn.him.api.service.group.GroupMsgService;
import com.lmxdawn.him.api.service.group.GroupUserService;
import com.lmxdawn.him.api.utils.PageUtils;
import com.lmxdawn.him.common.entity.group.GroupMsg;
import com.lmxdawn.him.common.entity.group.GroupUser;
import com.lmxdawn.him.common.enums.ResultEnum;
import com.lmxdawn.him.common.utils.ResultVOUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class GroupMsgServiceImpl implements GroupMsgService {

    @Resource
    private GroupMsgDao groupMsgDao;

    @Resource
    private GroupUserService groupUserService;

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

    @Override
    public boolean addpMsg(Long uid, Long groupId, Integer msgType, String msgContent) {
        String lastMsgContent = msgContent;
        GroupMsg groupMsg = new GroupMsg();
        groupMsg.setGroupId(groupId);
        groupMsg.setSenderUid(uid);
        groupMsg.setMsgType(msgType);
        groupMsg.setMsgContent(msgContent);
        switch (msgType) {
            case 1:
                lastMsgContent = "[图片消息]";
                break;
            case 2:
                lastMsgContent = "[文件消息]";
                break;
            case 3:
                lastMsgContent = "[语言消息]";
                break;
            case 4:
                lastMsgContent = "[视频消息]";
                break;
        }

        boolean b = insertGroupMsg(groupMsg);
        if (!b) {
            return false;
        }

        GroupUser groupUser = new GroupUser();
        groupUser.setGroupId(groupId);
        groupUser.setLastMsgContent(lastMsgContent);
        groupUser.setLastMsgTime(new Date());
        groupUser.setUnMsgCount(1);
        boolean b1 = groupUserService.updateGroupUserByGroupId(groupUser);
        return true;
    }
}
