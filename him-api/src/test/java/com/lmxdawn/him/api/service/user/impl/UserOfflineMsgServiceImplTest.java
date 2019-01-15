package com.lmxdawn.him.api.service.user.impl;

import com.lmxdawn.him.api.BaseApplicationTest;
import com.lmxdawn.him.api.service.user.UserOfflineMsgService;
import com.lmxdawn.him.common.entity.user.UserOfflineMsg;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

public class UserOfflineMsgServiceImplTest extends BaseApplicationTest {

    @Resource
    private UserOfflineMsgService userOfflineMsgService;

    @Test
    public void listByLastMsgId() {

        Long receiverUid = 1L;
        Long lastMsgId = 0L;
        List<UserOfflineMsg> userOfflineMsgList = userOfflineMsgService.listByLastMsgId(receiverUid, lastMsgId);
        assertTrue(userOfflineMsgList.size() > 0);
    }

    @Test
    public void insertUserOfflineMsg() {

        UserOfflineMsg userOfflineMsg = new UserOfflineMsg();
        userOfflineMsg.setReceiverUid(1L);
        userOfflineMsg.setSenderUid(2L);
        userOfflineMsg.setMsgType(0);
        userOfflineMsg.setMsgContent("ssssss");

        boolean b = userOfflineMsgService.insertUserOfflineMsg(userOfflineMsg);

        assertNotNull(userOfflineMsg.getMsgId());

    }
}
