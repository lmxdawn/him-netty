package com.lmxdawn.him.api.service.user.impl;

import com.lmxdawn.him.api.dao.user.UserFriendDao;
import com.lmxdawn.him.api.service.user.UserFriendService;
import com.lmxdawn.him.api.req.UserFriendListRequest;
import com.lmxdawn.him.api.res.UserFriendListInfoResponse;
import com.lmxdawn.him.common.entity.user.UserFriend;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFriendServiceImpl implements UserFriendService {

    @Resource
    private UserFriendDao userFriendDao;

    @Override
    public List<UserFriendListInfoResponse> listByUid(UserFriendListRequest userFriendListRequest) {
        userFriendListRequest.setOffset();
        List<UserFriendListInfoResponse> userFriendListInfoResponseList = userFriendDao.listByUid(userFriendListRequest).stream()
                .map(v -> {
                    UserFriendListInfoResponse userFriendListInfoResponse = new UserFriendListInfoResponse();
                    BeanUtils.copyProperties(v, userFriendListInfoResponse);
                    return userFriendListInfoResponse;
                })
                .collect(Collectors.toList());
        return userFriendListInfoResponseList;
    }

    @Override
    public UserFriend findByUidAndFriendUid(Long uid, Long friendUid) {
        return userFriendDao.findByUidAndFriendUid(uid, friendUid);
    }

    @Override
    public boolean insertUserFriend(UserFriend userFriend) {
        if (userFriend.getUid().equals(userFriend.getFriendUid())) {
            return false;
        }
        userFriend.setCreateTime(new Date());
        userFriend.setModifiedTime(new Date());
        return userFriendDao.insertUserFriend(userFriend);
    }

    @Override
    public boolean updateUserFriend(UserFriend userFriend) {
        userFriend.setModifiedTime(new Date());
        return userFriendDao.updateUserFriend(userFriend);
    }

    @Override
    public boolean deleteByUidAndFriendUid(Long uid, Long friendUid) {
        return userFriendDao.deleteByUidAndFriendUid(uid, friendUid);
    }
}
