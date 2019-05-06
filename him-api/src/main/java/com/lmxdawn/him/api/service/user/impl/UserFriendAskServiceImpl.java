package com.lmxdawn.him.api.service.user.impl;

import com.lmxdawn.him.api.dao.user.UserFriendAskDao;
import com.lmxdawn.him.api.service.user.UserFriendAskService;
import com.lmxdawn.him.api.utils.PageUtils;
import com.lmxdawn.him.common.entity.user.UserFriendAsk;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserFriendAskServiceImpl implements UserFriendAskService {

    @Resource
    private UserFriendAskDao userFriendAskDao;

    @Override
    public List<UserFriendAsk> listByUid(Long uid, Integer page, Integer limit) {
        Integer offset = PageUtils.createOffset(page, limit);
        return userFriendAskDao.listByUid(uid, offset, limit);
    }
    
    @Override
    public UserFriendAsk findById(Long id) {
        return userFriendAskDao.findById(id);
    }
    
    @Override
    public boolean insertUserFriendAsk(UserFriendAsk userFriendAsk) {
        userFriendAsk.setCreateTime(new Date());
        userFriendAsk.setModifiedTime(new Date());
        return userFriendAskDao.insertUserFriendAsk(userFriendAsk);
    }

    @Override
    public boolean updateUserFriendAsk(UserFriendAsk userFriendAsk) {
        userFriendAsk.setModifiedTime(new Date());
        return userFriendAskDao.updateUserFriendAsk(userFriendAsk);
    }

    @Override
    public boolean deleteById(Long id) {
        return userFriendAskDao.deleteById(id);
    }
}
