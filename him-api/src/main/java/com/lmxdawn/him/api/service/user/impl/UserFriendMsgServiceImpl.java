package com.lmxdawn.him.api.service.user.impl;

import com.lmxdawn.him.api.dao.user.UserFriendMsgDao;
import com.lmxdawn.him.api.service.user.UserFriendMsgService;
import com.lmxdawn.him.common.entity.user.UserFriendMsg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserFriendMsgServiceImpl implements UserFriendMsgService {

    @Resource
    private UserFriendMsgDao userFriendMsgDao;

    @Override
    public List<UserFriendMsg> listByUidAndToUid(Long uid, Long toUid, Integer offset, Integer limit) {
        return userFriendMsgDao.listByUidAndToUid(uid, toUid, offset, limit);
    }

    @Override
    public boolean insertUserFriendMsg(UserFriendMsg userFriendMsg) {
        userFriendMsg.setCreateTime(new Date());
        return userFriendMsgDao.insertUserFriendMsg(userFriendMsg);
    }
}
