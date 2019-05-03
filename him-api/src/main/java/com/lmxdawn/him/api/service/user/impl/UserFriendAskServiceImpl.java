package com.lmxdawn.him.api.service.user.impl;

import com.lmxdawn.him.api.dao.user.UserFriendAskDao;
import com.lmxdawn.him.api.service.user.UserFriendAskService;
import com.lmxdawn.him.api.vo.req.UserFriendAskListReqVO;
import com.lmxdawn.him.api.vo.res.UserFriendAskListResVO;
import com.lmxdawn.him.common.entity.user.UserFriendAsk;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFriendAskServiceImpl implements UserFriendAskService {

    @Resource
    private UserFriendAskDao userFriendAskDao;

    @Override
    public List<UserFriendAskListResVO> listByUid(UserFriendAskListReqVO userFriendAskListReqVO) {
        List<UserFriendAsk> userFriendAsks = userFriendAskDao.listByUid(userFriendAskListReqVO);
        List<UserFriendAskListResVO> userFriendAskListResVOS = userFriendAsks.stream().map(v -> {
            UserFriendAskListResVO userFriendAskListResVO = new UserFriendAskListResVO();
            BeanUtils.copyProperties(v, userFriendAskListResVO);
            return userFriendAskListResVO;
        }).collect(Collectors.toList());
        return userFriendAskListResVOS;
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
