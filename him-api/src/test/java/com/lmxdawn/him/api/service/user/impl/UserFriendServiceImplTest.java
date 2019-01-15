package com.lmxdawn.him.api.service.user.impl;

import com.lmxdawn.him.api.BaseApplicationTest;
import com.lmxdawn.him.api.service.user.UserFriendService;
import com.lmxdawn.him.api.vo.req.UserFriendListReqVO;
import com.lmxdawn.him.api.vo.res.UserFriendListInfoResVO;
import com.lmxdawn.him.common.entity.user.UserFriend;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class UserFriendServiceImplTest extends BaseApplicationTest {

    @Resource
    private UserFriendService userFriendService;

    @Test
    public void listByUid() {

        UserFriendListReqVO userFriendListReqVO = new UserFriendListReqVO();
        userFriendListReqVO.setUid(1L);
        userFriendListReqVO.setOffset();
        List<UserFriendListInfoResVO> userFriendListInfoResVOList = userFriendService.listByUid(userFriendListReqVO);

        System.out.println(userFriendListInfoResVOList);

    }

    @Test
    public void insertUserFriend() {

        UserFriend userFriend = new UserFriend();
        userFriend.setUid(1L);
        userFriend.setFriendUid(3L);
        userFriend.setRemark("ssss");
        boolean b = userFriendService.insertUserFriend(userFriend);
        assertNotNull(userFriend.getId());
    }

    @Test
    public void updateUserFriend() {
    }

    @Test
    public void deleteByUserFriend() {
    }
}
