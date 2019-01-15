package com.lmxdawn.him.api.service.user.impl;

import com.lmxdawn.him.api.dao.user.UserOfflineMsgDao;
import com.lmxdawn.him.api.service.user.UserOfflineMsgService;
import com.lmxdawn.him.common.entity.user.UserOfflineMsg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserOfflineMsgServiceImpl implements UserOfflineMsgService {

    @Resource
    private UserOfflineMsgDao userOfflineMsgDao;

    @Override
    public List<UserOfflineMsg> listByLastMsgId(Long receiverUid, Long lastMsgId) {
        return userOfflineMsgDao.listByLastMsgId(receiverUid, lastMsgId);
    }

    @Override
    public boolean insertUserOfflineMsg(UserOfflineMsg userOfflineMsg) {
        userOfflineMsg.setCreateTime(new Date());
        return userOfflineMsgDao.insertUserOfflineMsg(userOfflineMsg);
    }
}
