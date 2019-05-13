package com.lmxdawn.him.api.service.group.impl;

import com.lmxdawn.him.api.constant.WSResTypeConstant;
import com.lmxdawn.him.api.dao.group.GroupMsgDao;
import com.lmxdawn.him.api.constant.WSMsgTypeConstant;
import com.lmxdawn.him.api.service.group.GroupMsgService;
import com.lmxdawn.him.api.service.group.GroupUserService;
import com.lmxdawn.him.api.service.user.UserService;
import com.lmxdawn.him.api.utils.PageUtils;
import com.lmxdawn.him.api.utils.WSBaseReqUtils;
import com.lmxdawn.him.api.vo.req.WSBaseReqVO;
import com.lmxdawn.him.api.ws.WSServer;
import com.lmxdawn.him.common.entity.group.GroupMsg;
import com.lmxdawn.him.common.entity.group.GroupUser;
import com.lmxdawn.him.common.entity.user.User;
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
    
    @Resource
    private UserService userService;
    
    @Resource
    private WSServer wsServer;

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
    public boolean sendGroupMsg(Long uid, Long groupId, Integer type, Integer msgType, String msgContent) {
        String lastMsgContent = msgContent;
        GroupMsg groupMsg = new GroupMsg();
        groupMsg.setGroupId(groupId);
        groupMsg.setSenderUid(uid);
        groupMsg.setMsgType(msgType);
        groupMsg.setMsgContent(msgContent);
        switch (msgType) {
            case WSMsgTypeConstant.TEXT:
                break;
            case WSMsgTypeConstant.IMAGE:
                lastMsgContent = "[图片消息]";
                break;
            case WSMsgTypeConstant.FILE:
                lastMsgContent = "[文件消息]";
                break;
            case WSMsgTypeConstant.VOICE:
                lastMsgContent = "[语言消息]";
                break;
            case WSMsgTypeConstant.VIDEO:
                lastMsgContent = "[视频消息]";
                break;
            default:
                // 位置类型
                return false;
        }

        boolean b = insertGroupMsg(groupMsg);
        if (!b) {
            return false;
        }

        // 保存离线消息
        GroupUser groupUser = new GroupUser();
        groupUser.setGroupId(groupId);
        groupUser.setLastMsgContent(lastMsgContent);
        groupUser.setLastMsgTime(new Date());
        groupUser.setUnMsgCount(1);
        boolean b1 = groupUserService.updateGroupUserByGroupId(groupUser);
        
        // 发送在线消息
        // 查询用户信息
        User user = userService.findByUid(uid);
        Long sUid = user.getUid();
        String name = user.getName();
        String avatar = user.getAvatar();
        String remark = user.getRemark();
        WSBaseReqVO wsBaseReqVO = WSBaseReqUtils.create(type, groupId, msgType, msgContent, sUid, name, avatar, remark);
    
        // 查找群里的所有用户信息
        List<GroupUser> groupUsers = groupUserService.listByGroupId(groupId, 1, 500);
    
        groupUsers.forEach(v -> {
            // 排除自己
            if (!uid.equals(v.getUid())) {
                wsServer.sendMsg(v.getUid(), wsBaseReqVO);
            }
        });
        
        return true;
    }
}
