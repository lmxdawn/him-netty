package com.lmxdawn.him.api.service.user.impl;

import com.lmxdawn.him.api.dao.user.UserFriendDao;
import com.lmxdawn.him.api.service.user.UserFriendService;
import com.lmxdawn.him.api.vo.req.UserFriendListReqVO;
import com.lmxdawn.him.api.vo.res.UserFriendListInfoResVO;
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
    public List<UserFriendListInfoResVO> listByUid(UserFriendListReqVO userFriendListReqVO) {
        userFriendListReqVO.setOffset();
        List<UserFriendListInfoResVO> userFriendListInfoResVOList = userFriendDao.listByUid(userFriendListReqVO).stream()
                .map(v -> {
                    UserFriendListInfoResVO userFriendListInfoResVO = new UserFriendListInfoResVO();
                    BeanUtils.copyProperties(v, userFriendListInfoResVO);
                    return userFriendListInfoResVO;
                })
                .collect(Collectors.toList());
        return userFriendListInfoResVOList;
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
    public boolean deleteByUserFriend(UserFriend userFriend) {
        return deleteByUserFriend(userFriend);
    }
}
